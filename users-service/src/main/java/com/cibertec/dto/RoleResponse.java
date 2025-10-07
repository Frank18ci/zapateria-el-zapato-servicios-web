package com.cibertec.dto;

import lombok.Builder;

@Builder
public record RoleResponse(
        Long id,
        String code,
        String name,
        String description
) {
}
