package com.example.e_commerce_backend.Product.Impl;

import com.example.e_commerce_backend.Product.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, MultipartFile imageFile) throws IOException {

        Product product = ProductMapper.mapToProduct(productDto);
        product.setImage(imageFile.getBytes());
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }
}
