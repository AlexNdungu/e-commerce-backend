package com.example.e_commerce_backend.Product.Impl;

import com.example.e_commerce_backend.Category.CategoryMapper;
import com.example.e_commerce_backend.Product.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) throws IOException {

        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::mapToProductDto)  // Using method reference
                .collect(Collectors.toList());
    }
}
