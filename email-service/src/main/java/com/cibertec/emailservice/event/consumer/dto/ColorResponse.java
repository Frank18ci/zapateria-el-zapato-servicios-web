package com.cibertec.emailservice.event.consumer.dto;

import lombok.Builder;

@Builder
public record ColorResponse(
        Long id,
        String name,
        String hex
) {
}
