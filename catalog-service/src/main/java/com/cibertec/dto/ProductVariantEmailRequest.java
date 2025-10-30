package com.cibertec.dto;

import lombok.Builder;

@Builder
public record ProductVariantEmailRequest(
        String email,
        Long productVariantId
) {
}
