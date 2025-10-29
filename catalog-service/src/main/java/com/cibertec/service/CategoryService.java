package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.CategoryRequest;
import com.cibertec.dto.CategoryResponse;

public interface CategoryService {
    
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
    void deleteCategory(Long id);
}
