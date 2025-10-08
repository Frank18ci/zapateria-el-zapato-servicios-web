package com.cibertec.controller;

import com.cibertec.dto.BrandRequest;
import com.cibertec.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid BrandRequest brandRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.createBrand(brandRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid BrandRequest brandRequest) {
        return ResponseEntity.ok(brandService.updateBrand(id, brandRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
