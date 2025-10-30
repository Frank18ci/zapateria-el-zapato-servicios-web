package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.WarehouseRequest;
import com.cibertec.dto.WarehouseResponse;
import org.springframework.data.domain.Page;

public interface WarehouseService {

    List<WarehouseResponse> getAllWarehouses();

    WarehouseResponse getWarehouseById(Long id);

    WarehouseResponse createWarehouse(WarehouseRequest warehouseRequest);

    WarehouseResponse updateWarehouse(Long id, WarehouseRequest warehouseRequest);

    void deleteWarehouse(Long id);

    Page<WarehouseResponse> getAllPaged(int page, int size, String sortBy, String direction, String name);
}
