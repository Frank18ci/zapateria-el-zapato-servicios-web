package com.cibertec.event.producer.dto;

import com.cibertec.dto.ProductVariantResponse;
import lombok.Builder;

@Builder
public record ProductVariantResponseK(
        String email,
        ProductVariantResponse productVariantResponse
) {
}
