package com.cibertec.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.dto.WarehouseRequest;
import com.cibertec.dto.WarehouseResponse;
import com.cibertec.model.Warehouse;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    @Mapping(target = "id", ignore = true) // Ignorar el mapeo del campo 'id' si es generado automáticamente
    Warehouse toEntity(WarehouseRequest warehouseRequest); // Implementar correctamente según la entidad real

    WarehouseResponse toDto(Warehouse warehouse); // Implementar correctamente según el DTO real
    
     List<WarehouseResponse> toDtoList(List<Warehouse> warehouses);
}
