package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.CategoryRequest;
import com.cibertec.dto.CategoryResponse;
import org.springframework.data.domain.Page;

public interface CategoryService {
    
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
    void deleteCategory(Long id);

    Page<CategoryResponse> getAllCategoriesPaged(int page, int size, String sortBy, String direction, String name);
}
