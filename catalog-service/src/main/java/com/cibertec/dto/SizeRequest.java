package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SizeRequest(
        @NotBlank(message = "Scale code es obligatorio")
        String scaleCode,
        @NotBlank(message = "Code es obligatorio")
        String code,
        @NotNull(message = "Numeric value es obligatorio")
        BigDecimal numericValue,
        @NotNull(message = "Cm length es obligatorio")
        BigDecimal mmLength
) {
}
