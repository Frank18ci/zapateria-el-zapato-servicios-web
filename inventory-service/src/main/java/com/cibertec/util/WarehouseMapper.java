package com.cibertec.util;

import java.util.List;

import org.mapstruct.Mapper;

import com.cibertec.dto.WarehouseRequest;
import com.cibertec.dto.WarehouseResponse;
import com.cibertec.model.Warehouse;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    Warehouse toEntity(WarehouseRequest warehouseRequest);
    WarehouseResponse toDto(Warehouse warehouse);
    List<WarehouseResponse> toDtoList(List<Warehouse> warehouses);
}
