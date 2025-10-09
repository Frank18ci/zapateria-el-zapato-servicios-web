package com.cibertec.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.dto.StockMovementRequest;
import com.cibertec.dto.StockMovementResponse;
import com.cibertec.model.StockMovement;

@Mapper(componentModel = "spring" , uses={WarehouseMapper.class})
public interface  StockMovementMapper {
     
    @Mapping(source="warehouseId" , target="warehouse.id")

    @Mapping(target = "id", ignore = true) // Ignorar el mapeo del campo 'id' si es generado automáticamente
    StockMovement toEntity(StockMovementRequest movementRequest); 
    
    StockMovementResponse toDto(StockMovement stockMovement); // Implementar correctamente según el DTO real

    List<StockMovementResponse> toDtoList(List<StockMovement> stockMovements);

    


}
