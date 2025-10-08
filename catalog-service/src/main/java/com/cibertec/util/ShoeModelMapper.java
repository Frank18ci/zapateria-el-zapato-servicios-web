package com.cibertec.util;

import com.cibertec.dto.ShoeModelRequest;
import com.cibertec.dto.ShoeModelResponse;
import com.cibertec.model.ShoeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses =  {CategoryMapper.class, BrandMapper.class})
public interface ShoeModelMapper {
    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(source = "categoryId", target = "category.id")
    ShoeModel toEntity(ShoeModelRequest shoeModelRequest);
    ShoeModelResponse toDto(ShoeModel shoeModel);
    List<ShoeModelResponse> toDtoList(List<ShoeModel> shoeModels);
}
