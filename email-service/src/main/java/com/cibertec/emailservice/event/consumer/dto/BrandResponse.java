package com.cibertec.emailservice.event.consumer.dto;

import lombok.Builder;

@Builder
public record BrandResponse(
    Long id,
    String name
) {
}
