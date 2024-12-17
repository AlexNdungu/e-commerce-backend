package com.example.e_commerce_backend.Product;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, MultipartFile imageFile) throws IOException;
}
