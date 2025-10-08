package com.cibertec.util;

import com.cibertec.dto.BrandRequest;
import com.cibertec.dto.BrandResponse;
import com.cibertec.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toEntity(BrandRequest brandRequest);
    BrandResponse toDto(Brand brand);
    List<BrandResponse> toDtoList(List<Brand> brands);
}
