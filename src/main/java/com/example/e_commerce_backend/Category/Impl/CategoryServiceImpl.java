package com.example.e_commerce_backend.Category.Impl;

import com.example.e_commerce_backend.Category.*;
import com.example.e_commerce_backend.Exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryID) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category of id " + categoryID + " not found!"));
        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDto)  // Using method reference
                .collect(Collectors.toList());
    }


}
