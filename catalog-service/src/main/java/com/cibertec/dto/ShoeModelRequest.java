package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShoeModelRequest(
        @NotNull(message = "Brand ID es obligatorio")
        Long brandId,
        @NotNull(message = "Category ID es obligatorio")
        Long categoryId,
        @NotBlank(message = "Gender Code es obligatorio")
        String genderCode,
        @NotBlank(message = "Code es obligatorio")
        String code,
        @NotBlank(message = "Name es obligatorio")
        String name,
        String description,
        Integer releaseYear,
        LocalDateTime discontinuedAt
) {
}
