package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record WidthRequest(
        @NotBlank(message = "Code es obligatorio")
        String code,
        @NotBlank(message = "Description es obligatorio")
        String description
) {
}
