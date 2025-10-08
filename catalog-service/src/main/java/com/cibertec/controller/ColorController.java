package com.cibertec.controller;

import com.cibertec.dto.ColorRequest;
import com.cibertec.service.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
public class ColorController {
    private final ColorService colorService;
    @GetMapping
    public ResponseEntity<?> getAllColors() {
        return ResponseEntity.ok(colorService.getAllColors());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getColorById(@PathVariable Long id) {
        return ResponseEntity.ok(colorService.getColorById(id));
    }
    @PostMapping
    public ResponseEntity<?> createColor(@RequestBody @Valid ColorRequest colorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(colorService.createColor(colorRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateColor(@PathVariable Long id, @RequestBody @Valid ColorRequest colorRequest) {
        return ResponseEntity.ok(colorService.updateColor(id, colorRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
        return ResponseEntity.noContent().build();
    }
}
