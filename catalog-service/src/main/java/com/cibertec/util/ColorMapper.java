package com.cibertec.util;

import com.cibertec.dto.ColorRequest;
import com.cibertec.dto.ColorResponse;
import com.cibertec.model.Color;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    Color toEntity(ColorRequest colorRequest);
    ColorResponse toDto(Color color);
    List<ColorResponse> toDtoList(List<Color> colors);
}
