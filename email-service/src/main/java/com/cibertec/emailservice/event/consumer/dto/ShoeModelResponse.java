package com.cibertec.emailservice.event.consumer.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShoeModelResponse(
        Long id,
        BrandResponse brand,
        CategoryResponse category,
        String genderCode,
        String code,
        String name,
        String description,
        Integer releaseYear,
        LocalDateTime discontinuedAt
) {
}
