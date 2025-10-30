package com.cibertec.emailservice.event.consumer.dto;

import lombok.Builder;

@Builder
public record ProductVariantResponseK(
        String email,
        ProductVariantResponse productVariantResponse
) {
}