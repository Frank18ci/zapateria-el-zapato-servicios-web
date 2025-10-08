package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotBlank(message = "Name es obligatorio")
        String name,
        Long parentId
) {
}
