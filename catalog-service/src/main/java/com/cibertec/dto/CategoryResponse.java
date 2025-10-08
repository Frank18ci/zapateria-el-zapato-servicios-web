package com.cibertec.dto;


import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id,
        String name,
        CategoryResponse parent
) {
}
