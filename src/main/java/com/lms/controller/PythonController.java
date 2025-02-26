package com.lms.controller;

import com.lms.service.Impl.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class PythonController {

    @Autowired
    private PythonService pythonService;

    @PostMapping("/process")
    public ResponseEntity<String> processString(@RequestBody String input) {
        try {
            String result = pythonService.processString(input);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error processing request: " + e.getMessage());
        }
    }
}