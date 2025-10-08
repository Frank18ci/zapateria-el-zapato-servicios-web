package com.cibertec.controller;

import com.cibertec.dto.SizeRequest;
import com.cibertec.service.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sizes")
public class SizeController {
    private final SizeService sizeService;
    @GetMapping
    public ResponseEntity<?> getAllSizes() {
        return ResponseEntity.ok(sizeService.getAllSizes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSizeById(@PathVariable Long id) {
        return ResponseEntity.ok(sizeService.getSizeById(id));
    }
    @PostMapping
    public ResponseEntity<?> createSize(@RequestBody @Valid SizeRequest sizeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sizeService.createSize(sizeRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSize(@PathVariable Long id, @RequestBody @Valid SizeRequest sizeRequest) {
        return ResponseEntity.ok(sizeService.updateSize(id, sizeRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Long id) {
        sizeService.deleteSize(id);
        return ResponseEntity.noContent().build();
    }
}
