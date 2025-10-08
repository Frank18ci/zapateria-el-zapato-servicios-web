package com.cibertec.controller;

import com.cibertec.dto.ShoeModelRequest;
import com.cibertec.service.ShoeModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shoe-models")
public class ShoeModelController {
    private final ShoeModelService shoeModelService;
    @GetMapping
    public ResponseEntity<?> getAllShoeModels() {
        return ResponseEntity.ok(shoeModelService.getAllShoeModels());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getShoeModelById(@PathVariable Long id) {
        return ResponseEntity.ok(shoeModelService.getShoeModelById(id));
    }
    @PostMapping
    public ResponseEntity<?> createShoeModel(@RequestBody @Valid ShoeModelRequest shoeModelRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shoeModelService.createShoeModel(shoeModelRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateShoeModel(@PathVariable Long id, @RequestBody @Valid ShoeModelRequest shoeModelRequest) {
        return ResponseEntity.ok(shoeModelService.updateShoeModel(id, shoeModelRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShoeModel(@PathVariable Long id) {
        shoeModelService.deleteShoeModel(id);
        return ResponseEntity.noContent().build();
    }
}
