package com.cibertec.dto;

import lombok.Builder;

@Builder
public record WidthResponse(
        Long id,
        String code,
        String description
) {
}
