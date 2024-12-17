package com.example.e_commerce_backend.Product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    // Build add product api
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestPart ProductDto productDto, @RequestPart MultipartFile imageFile){
        try{
            ProductDto savedProduct = productService.createProduct(productDto, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
