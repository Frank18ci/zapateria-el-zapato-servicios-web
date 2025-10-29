package com.cibertec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.dto.CategoryRequest;
import com.cibertec.dto.CategoryResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.Category;
import com.cibertec.repository.CategoryRepository;
import com.cibertec.service.CategoryService;
import com.cibertec.util.CategoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }
    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category not found with id: " + id)
        ));
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toEntity(categoryRequest);
        if(categoryRequest.parentId() == null || categoryRequest.parentId() == 0){
            category.setParent(null);
        }
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category categoryFound = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category not found with id: " + id)
        );
        categoryFound.setName(categoryRequest.name());
        if(categoryRequest.parentId() == null || categoryRequest.parentId() == 0) {
            categoryFound.setParent(null);
        }
        if(categoryRequest.parentId() != null && categoryRequest.parentId() != 0){
            categoryFound.setParent(categoryMapper.toEntity(categoryRequest).getParent());
        }
        return categoryMapper.toDto(categoryRepository.save(categoryFound));
    }

    @Override
    public void deleteCategory(Long id) {
        Category categoryFound = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Category not found with id: " + id)
        );
        categoryRepository.delete(categoryFound);
    }
}
