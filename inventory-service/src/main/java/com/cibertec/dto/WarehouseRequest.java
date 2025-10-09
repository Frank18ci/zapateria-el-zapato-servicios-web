package com.cibertec.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record WarehouseRequest(
     

     @NotBlank(message = "Code es obligatorio")
     String code,
     
    @NotBlank(message = "Name es obligatorio")
     String name,

     @NotBlank(message = "Address es obligatorio")
     String address
    

) {}
