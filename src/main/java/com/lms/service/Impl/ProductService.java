package com.lms.service.Impl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lms.model.Product;
import com.lms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void processCSV(MultipartFile file) throws IOException, WriterException {
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

        // Skip the header line
        br.readLine();

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String name = data[0].trim();
            String description = data[1].trim();
            double price = Double.parseDouble(data[2].trim());
            double tax = Double.parseDouble(data[3].trim());
            double total = Double.parseDouble(data[4].trim());
            int stockQuantity = Integer.parseInt(data[5].trim());
            double purchasePrice = Double.parseDouble(data[6].trim());
            String barcode = generateBarcode();

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setTax(tax);
            product.setTotal(total);
            product.setStockQuantity(stockQuantity);
            product.setPurchasePrice(purchasePrice);
            product.setBarcodeNumber(barcode);
            product.setBarcode(generateBarcode(barcode, 200, 50));
            productRepository.save(product);
        }
        br.close();
    }


//      try {
//        byte[] barcodeBytes = barcodeServiceImpl.generateBarcode(text, 200, 50); // Adjust width and height as needed
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(barcodeBytes);
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }
    private String generateBarcode() {
        // Generate random barcode (you can implement your own logic)
        Random random = new Random();
        StringBuilder barcode = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            barcode.append(random.nextInt(10));
        }
        return barcode.toString();
    }


    public static byte[] generateBarcode(String barcodeText, int width, int height) throws WriterException, IOException {
        // Set barcode parameters
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0); // Set margin to 0
        BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, width, height, hints);

        // Convert bitMatrix to byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        return outputStream.toByteArray();
    }
}
