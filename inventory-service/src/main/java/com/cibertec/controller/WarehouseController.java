package com.cibertec.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.WarehouseRequest;
import com.cibertec.service.WarehouseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/warehouses")
@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService  warehouseService;

     @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

     @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(id));
    }

      @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid WarehouseRequest warehouseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.createWarehouse(warehouseRequest));
    }

       @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid WarehouseRequest warehouseRequest) {
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouseRequest));
    }

       @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }


}
