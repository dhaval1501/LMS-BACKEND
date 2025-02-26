package com.lms.service.Impl;

import com.lms.service.ImageMatcherService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageMatcherServiceImpl implements ImageMatcherService {

    private final String pythonServerUrl = "http://localhost:5001/filter-images"; // Make sure this is correct

    @Override
    public byte[] filterImages(MultipartFile referenceImage) throws IOException {
        // Convert MultipartFile to byte array
        byte[] referenceImageBytes = referenceImage.getBytes();

        // Call Python service and get the filtered image as a byte array (blob)
        byte[] matchedImageBlob = callPythonFilterService(referenceImage);

        return matchedImageBlob;
    }

    private byte[] callPythonFilterService(MultipartFile referenceImage) throws IOException {
        // Create RestTemplate to send HTTP request
        RestTemplate restTemplate = new RestTemplate();

        // Prepare headers (this will be multipart form-data)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);  // Set multipart content type

        // Prepare body for multipart request
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("reference_image", referenceImage.getResource()); // Add the file as part of the form

        // Wrap the body and headers into an HttpEntity
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        // Send POST request to Python server and get response
        ResponseEntity<byte[]> response = restTemplate.exchange(
                pythonServerUrl,
                HttpMethod.POST,
                entity,
                byte[].class
        );

        // Return the image blob from the response
        return response.getBody();
    }
}