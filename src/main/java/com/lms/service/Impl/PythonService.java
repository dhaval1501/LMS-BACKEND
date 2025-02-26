package com.lms.service.Impl;
import com.lms.service.PythonInterface;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import py4j.GatewayServer;
import py4j.ClientServer;

import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;

@Service
public class PythonService {

    private volatile PythonInterface pythonMethods;
    private volatile ClientServer gateway;
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY = 1000; // 1 second

    @PostConstruct
    private void init() {
        connectToPythonServer();
    }

    private void connectToPythonServer() {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                // Create a new gateway
                gateway =  gateway = new ClientServer.ClientServerBuilder()
                        .javaPort(25333)
                        .pythonPort(25334)
                        .entryPoint(null)
                        .enableMemoryManagement(true)
                        .build();
                gateway.startServer();

                // Get the Python endpoint
                pythonMethods = (PythonInterface) gateway.getPythonServerEntryPoint(new Class[] { PythonInterface.class });

                // Test the connection
//                pythonMethods.process_string("test");

                System.out.println("Successfully connected to Python server");
                return;

            } catch (Exception e) {
                retries++;
                System.err.println("Attempt " + retries + " failed to connect to Python server: " + e.getMessage());

                if (gateway != null) {
                    try {
                        gateway.shutdown();
                    } catch (Exception ignored) {}
                }

                if (retries < MAX_RETRIES) {
                    try {
                        Thread.sleep(RETRY_DELAY);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        throw new RuntimeException("Failed to connect to Python server after " + MAX_RETRIES + " attempts");
    }

    @PreDestroy
    private void cleanup() {
        if (gateway != null) {
            try {
                gateway.shutdown();
            } catch (Exception e) {
                System.err.println("Error shutting down gateway: " + e.getMessage());
            }
        }
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public String processString(String input) {
        if (pythonMethods == null) {
            throw new RuntimeException("Python service not initialized");
        }
        try {
            return pythonMethods.process_string(input);
        } catch (Exception e) {
            // If we get a connection error, try to reconnect
            try {
                connectToPythonServer();
                return pythonMethods.process_string(input);
            } catch (Exception reconnectError) {
                throw new RuntimeException("Failed to process string with Python: " + e.getMessage());
            }
        }
    }
}