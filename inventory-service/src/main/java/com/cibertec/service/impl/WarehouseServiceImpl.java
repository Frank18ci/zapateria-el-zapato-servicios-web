package com.cibertec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.dto.WarehouseRequest;
import com.cibertec.dto.WarehouseResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.Warehouse;
import com.cibertec.repository.WarehouseRepository;
import com.cibertec.service.WarehouseService;
import com.cibertec.util.WarehouseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl  implements WarehouseService{
    
    private final WarehouseRepository warehouseRepository;
   private final WarehouseMapper warehouseMapper;



   @Override
    public List<WarehouseResponse> getAllWarehouses() {
        return warehouseMapper.toDtoList(warehouseRepository.findAll());
    }

    @Override
    public WarehouseResponse getWarehouseById(Long id) {
        return warehouseMapper.toDto(warehouseRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("Warehouse not found with id: " + id)
        ));
    }

    @Override
    public WarehouseResponse createWarehouse(WarehouseRequest warehouseRequest) {
        return warehouseMapper.toDto(warehouseRepository.save(warehouseMapper.toEntity(warehouseRequest)));
    }

    @Override
    public WarehouseResponse updateWarehouse(Long id, WarehouseRequest warehouseRequest) {
         Warehouse warehouseFound  = warehouseRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("Warehouse not found with id: " + id)
        );
        warehouseFound.setCode(warehouseRequest.code());
        warehouseFound.setName(warehouseRequest.name());
        warehouseFound.setAddress(warehouseRequest.address());
        return warehouseMapper.toDto(warehouseRepository.save(warehouseFound));
    }

    @Override
    public void deleteWarehouse(Long id) {
         // implementación básica
            Warehouse warehouseFound = warehouseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Warehouse not found with id: " + id)
        );
        warehouseRepository.delete(warehouseFound);
         
    }
    
}
