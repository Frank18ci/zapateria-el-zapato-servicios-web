package com.cibertec.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.dto.PriceListRequest;
import com.cibertec.service.PriceListService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/price-lists")
@RequiredArgsConstructor
public class PriceListController {

    private final PriceListService priceListService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(priceListService.getAllPriceLists());
    }

    @GetMapping("/page")
    public ResponseEntity<?> findAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "") String name
    ) {
        return ResponseEntity.ok(priceListService.getAllPaged(page, size, sortBy, direction, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(priceListService.getPriceListById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PriceListRequest priceListRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(priceListService.createPriceList(priceListRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid PriceListRequest priceListRequest) {
        return ResponseEntity.ok(priceListService.updatePriceList(id, priceListRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        priceListService.deletePriceList(id);
        return ResponseEntity.noContent().build();
    }


}
