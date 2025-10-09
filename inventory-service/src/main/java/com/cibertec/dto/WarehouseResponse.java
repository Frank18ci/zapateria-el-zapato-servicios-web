package com.cibertec.dto;

import lombok.Builder;

@Builder
public record WarehouseResponse(

    Long id,

    String code,

    String name,
    
    String address
){}
