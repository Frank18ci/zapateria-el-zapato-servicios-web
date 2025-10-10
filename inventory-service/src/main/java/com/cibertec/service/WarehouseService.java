package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.WarehouseRequest;
import com.cibertec.dto.WarehouseResponse;

public interface WarehouseService {

    List<WarehouseResponse> getAllWarehouses();

    WarehouseResponse getWarehouseById(Long id);

    WarehouseResponse createWarehouse(WarehouseRequest warehouseRequest);

    WarehouseResponse updateWarehouse(Long id, WarehouseRequest warehouseRequest);

    void deleteWarehouse(Long id);
}
