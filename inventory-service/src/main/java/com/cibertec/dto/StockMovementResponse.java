package com.cibertec.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record StockMovementResponse(

     Long id,

    Long variantId,

    String binCode,

    String movementType,

    Integer quantity,

    BigDecimal unitCost,

    String currencyCode,

    LocalDateTime createdAt,

    String reason,

    String refDoc,

    WarehouseResponse warehouse



) {}
