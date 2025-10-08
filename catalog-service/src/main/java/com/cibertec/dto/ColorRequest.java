package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ColorRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,
        @NotBlank(message = "El código hexadecimal no puede estar vacío")
        String hex
) {
}
