package com.cibertec.emailservice.event.consumer.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SizeResponse(
        Long id,
        String scaleCode,
        String code,
        BigDecimal numericValue,
        BigDecimal mmLength
) {
}
