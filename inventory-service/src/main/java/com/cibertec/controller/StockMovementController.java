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

import com.cibertec.dto.StockMovementRequest;
import com.cibertec.service.StockMovementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(stockMovementService.getAllStockMovements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(stockMovementService.getStockMovementById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid StockMovementRequest movementRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockMovementService.createStockMovement(movementRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid StockMovementRequest stockMovementRequest) {
        return ResponseEntity.ok(stockMovementService.updateStockMovement(id, stockMovementRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stockMovementService.deleteStockMovement(id);
        return ResponseEntity.noContent().build();
    }

}
