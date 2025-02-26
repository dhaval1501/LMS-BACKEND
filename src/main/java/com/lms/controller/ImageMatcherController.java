package com.lms.controller;

import com.lms.service.ImageMatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/images")
public class ImageMatcherController {

    private final ImageMatcherService imageMatcherService;

    @Autowired
    public ImageMatcherController(ImageMatcherService imageMatcherService) {
        this.imageMatcherService = imageMatcherService;
    }

    // Endpoint to handle image matching and return Blob image data
    @PostMapping("/filter")
    public ResponseEntity<byte[]> filterImages(@RequestParam("reference_image") MultipartFile referenceImage) {
        try {
            byte[] matchedImageBlob = imageMatcherService.filterImages(referenceImage);

            // Save the ZIP file received from the Python server
            Path tempFilePath = Paths.get("downloaded_matched_images.zip");
            Files.write(tempFilePath, matchedImageBlob);

            // Prepare response with the downloaded ZIP file
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", "matched_images.zip");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            byte[] fileContent = Files.readAllBytes(tempFilePath);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);

        } catch (IOException e) {
            return ResponseEntity.status(500).body(("Error processing image: " + e.getMessage()).getBytes());
        }
    }
}
