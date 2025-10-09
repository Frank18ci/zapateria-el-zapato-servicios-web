package com.cibertec.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record  StockMovementRequest(

     @NotNull(message = "Model ID no puede ser nulo")
     Long variantId,

     @NotBlank(message = "Bin Code es obligatorio")
     String binCode,

    @NotBlank(message = "Movement Type es obligatorio")
     String movementType,
     
    @NotNull(message = "Quantity no puede ser nulo")
     Integer quantity,

    @NotNull(message = "Model ID no puede ser nulo")
     BigDecimal unitCost,  
     
     @NotBlank(message = "Currency Code es obligatorio")
     @Length(max = 3, message = "Currency Code debe tener máximo 3 caracteres")
     String currencyCode,

     @NotBlank(message = "Reason es obligatorio")
     String reason,
 
     @NotBlank(message = "Ref Doc es obligatorio")
     String refDoc,

    @NotNull(message = "Model ID no puede ser nulo")
     Long warehouseId


    
) {}
