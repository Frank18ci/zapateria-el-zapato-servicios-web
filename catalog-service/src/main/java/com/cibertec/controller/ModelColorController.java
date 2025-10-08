package com.cibertec.controller;

import com.cibertec.dto.ModelColorRequest;
import com.cibertec.service.ModelColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/model-colors")
public class ModelColorController {
    private final ModelColorService modelColorService;
    @GetMapping
    public ResponseEntity<?> getAllModelColors() {
        return ResponseEntity.ok(modelColorService.getAllModelColors());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getModelColorById(@PathVariable Long id) {
        return ResponseEntity.ok(modelColorService.getModelColor(id));
    }
    @PostMapping
    public ResponseEntity<?> createModelColor(@RequestBody @Valid ModelColorRequest modelColorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelColorService.createModelColor(modelColorRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateModelColor(@PathVariable Long id, @RequestBody @Valid ModelColorRequest modelColorRequest) {
        return ResponseEntity.ok(modelColorService.updateModelColor(id, modelColorRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModelColor(@PathVariable Long id) {
        modelColorService.deleteModelColor(id);
        return ResponseEntity.noContent().build();
    }
}
