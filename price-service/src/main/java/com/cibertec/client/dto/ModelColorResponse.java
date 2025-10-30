package com.cibertec.client.dto;

import lombok.Builder;

@Builder
public record ModelColorResponse(
        Long id,
        ShoeModelResponse model,
        ColorResponse color,
        String colorCode
) {
}
