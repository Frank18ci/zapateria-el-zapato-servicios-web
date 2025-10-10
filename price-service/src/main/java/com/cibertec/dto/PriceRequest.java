package com.cibertec.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PriceRequest(
        @NotNull(message = "Unit price es obligatorio")
        BigDecimal unitPrice,
        @NotNull(message = "Variant ID es obligatorio")
        Long variantId,
        @NotNull(message = "Price list ID es obligatorio")
        Long priceListId
) {
}
