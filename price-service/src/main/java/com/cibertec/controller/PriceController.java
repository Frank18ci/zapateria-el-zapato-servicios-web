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

import com.cibertec.dto.PriceRequest;
import com.cibertec.service.PriceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("prices")
public class PriceController {
    
     private final PriceService priceService; 
     

     @GetMapping
    public ResponseEntity<?> getAllPrices() {
        return ResponseEntity.ok(priceService.getAllPrices());
    }

        @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(priceService.getPriceById(id));
    }
    
     @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PriceRequest priceRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(priceService.createPrice(priceRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid PriceRequest priceRequest) {
        return ResponseEntity.ok(priceService.updatePrice(id, priceRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        priceService.deletePrice(id);
        return ResponseEntity.noContent().build();
    }
}
