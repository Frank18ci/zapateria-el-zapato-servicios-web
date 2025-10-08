package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record BrandRequest(
        @NotBlank(message = "Name es obligatorio")
        String name
) {
}
