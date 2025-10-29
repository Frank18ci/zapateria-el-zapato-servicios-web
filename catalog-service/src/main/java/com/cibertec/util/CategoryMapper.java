package com.cibertec.util;

import com.cibertec.dto.CategoryRequest;
import com.cibertec.dto.CategoryResponse;
import com.cibertec.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    
    @Mapping(source = "parentId", target = "parent.id")
    Category toEntity(CategoryRequest request);

    CategoryResponse toDto(Category category);
    
    List<CategoryResponse> toDtoList(List<Category> categories);
}
