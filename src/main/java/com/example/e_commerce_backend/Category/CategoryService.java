package com.example.e_commerce_backend.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long categoryID);

    List<CategoryDto> getAllCategories();
}

