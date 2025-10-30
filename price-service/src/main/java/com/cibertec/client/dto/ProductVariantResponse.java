package com.cibertec.client.dto;

import lombok.Builder;

@Builder
public record ProductVariantResponse(
        Long id,
        ModelColorResponse modelColor,
        SizeResponse size,
        WidthResponse width,
        String skuCode,
        String status
) {
}
