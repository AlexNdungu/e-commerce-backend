package com.example.e_commerce_backend.Product;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto) throws IOException;

    List<ProductDto> getAllProducts();
}
