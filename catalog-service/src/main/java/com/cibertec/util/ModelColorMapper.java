package com.cibertec.util;

import com.cibertec.dto.ModelColorRequest;
import com.cibertec.dto.ModelColorResponse;
import com.cibertec.model.ModelColor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ShoeModelMapper.class, ColorMapper.class})
public interface ModelColorMapper {
    @Mapping(source = "modelId", target = "model.id")
    @Mapping(source = "colorId", target = "color.id")
    ModelColor toEntity(ModelColorRequest request);
    ModelColorResponse toDto(ModelColor modelColor);
    List<ModelColorResponse> toDtoList(List<ModelColor> modelColors);
}
