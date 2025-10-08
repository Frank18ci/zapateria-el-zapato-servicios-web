package com.cibertec.util;

import com.cibertec.dto.WidthRequest;
import com.cibertec.dto.WidthResponse;
import com.cibertec.model.Width;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WidthMapper {
    Width toEntity(WidthRequest widthRequest);
    WidthResponse toDto(Width width);
    List<WidthResponse> toDtoList(List<Width> widths);
}
