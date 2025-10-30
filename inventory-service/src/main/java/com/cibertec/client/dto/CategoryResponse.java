package com.cibertec.client.dto;


import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id,
        String name,
        CategoryResponse parent
) {
}
