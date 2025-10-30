package com.cibertec.client.dto;

import lombok.Builder;

@Builder
public record BrandResponse(
    Long id,
    String name
) {
}
