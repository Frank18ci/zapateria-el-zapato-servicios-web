package com.cibertec.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.dto.StockMovementRequest;
import com.cibertec.dto.StockMovementResponse;
import com.cibertec.model.StockMovement;

@Mapper(componentModel = "spring", uses = {WarehouseMapper.class})
public interface StockMovementMapper {
    @Mapping(source = "warehouseId", target = "warehouse.id")
    StockMovement toEntity(StockMovementRequest movementRequest);
    StockMovementResponse toDto(StockMovement stockMovement);
    List<StockMovementResponse> toDtoList(List<StockMovement> stockMovements);
}
