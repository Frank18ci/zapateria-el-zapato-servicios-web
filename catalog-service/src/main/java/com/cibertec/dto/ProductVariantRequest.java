package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductVariantRequest(
        @NotNull(message = "ModelColorId no puede ser nulo")
        Long modelColorId,
        @NotNull(message = "ShoeModelId no puede ser nulo")
        Long sizeId,
        @NotNull(message = "WidthId no puede ser nulo")
        Long widthId,
        @NotBlank(message = "SKU no puede estar vacio")
        String skuCode,
        @NotBlank(message = "Status no puede estar vacio")
        String status
) {
}
