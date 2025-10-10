package com.cibertec.dto;

import java.time.LocalDate;

import com.cibertec.model.Price;

import lombok.Builder;

@Builder
public record PriceListResponse(
        Long id,
        String name,
        String currencyCode,
        LocalDate validFrom,
        LocalDate validTo
) {
}
