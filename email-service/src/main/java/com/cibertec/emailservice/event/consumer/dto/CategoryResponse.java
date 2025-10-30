package com.cibertec.emailservice.event.consumer.dto;


import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id,
        String name,
        CategoryResponse parent
) {
}
