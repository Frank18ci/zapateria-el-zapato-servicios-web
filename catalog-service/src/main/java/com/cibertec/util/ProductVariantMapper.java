package com.cibertec.util;

import com.cibertec.dto.ProductVariantRequest;
import com.cibertec.dto.ProductVariantResponse;
import com.cibertec.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ModelColorMapper.class, SizeMapper.class, WidthMapper.class})
public interface ProductVariantMapper {
    @Mapping(source = "modelColorId", target = "modelColor.id")
    @Mapping(source = "sizeId", target = "size.id")
    @Mapping(source = "widthId", target = "width.id")
    ProductVariant toEntity(ProductVariantRequest request);
    ProductVariantResponse toDto(ProductVariant productVariant);
    List<ProductVariantResponse> toDtoList(List<ProductVariant> productVariants);
}
