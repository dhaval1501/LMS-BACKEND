package com.lms.controller;


import com.lms.service.Impl.BarcodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class BarcodeController {

    private BarcodeServiceImpl barcodeServiceImpl;

    @Autowired
    public BarcodeController(BarcodeServiceImpl barcodeServiceImpl){
        this.barcodeServiceImpl = barcodeServiceImpl;

    }

    @GetMapping("/barcode/{text}")
    public ResponseEntity<byte[]> getBarcode(@PathVariable String text) {

        try {
            byte[] barcodeBytes = barcodeServiceImpl.generateBarcode(text, 200, 50); // Adjust width and height as needed
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(barcodeBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}

