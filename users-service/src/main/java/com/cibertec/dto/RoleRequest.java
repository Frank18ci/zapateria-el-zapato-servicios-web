package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RoleRequest(
        @NotBlank(message = "Code no debe ser nulo")
        String code,
        @NotBlank(message = "Name no debe ser nulo")
        String name,
        @NotBlank(message = "Description no debe ser nulo")
        String description
) {
}
