package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ModelColorRequest(
        @NotNull(message = "Model ID no puede ser nulo")
        Long modelId,
        @NotNull(message = "Color ID no puede ser nulo")
        Long colorId,
        @NotBlank(message = "El código de color no puede estar vacío")
        String colorCode
) {
}
