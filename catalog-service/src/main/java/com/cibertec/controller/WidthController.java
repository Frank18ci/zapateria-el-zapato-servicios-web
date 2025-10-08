package com.cibertec.controller;

import com.cibertec.dto.WidthRequest;
import com.cibertec.service.WidthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/widths")
public class WidthController {
    private final WidthService widthService;
    @GetMapping
    public ResponseEntity<?> getAllWidths() {
        return ResponseEntity.ok(widthService.getAllWidths());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getWidthById(@PathVariable Long id) {
        return ResponseEntity.ok(widthService.getWidthById(id));
    }
    @PostMapping
    public ResponseEntity<?> createWidth(@RequestBody @Valid WidthRequest widthRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(widthService.createWidth(widthRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateWidth(@PathVariable Long id, @RequestBody @Valid WidthRequest widthRequest) {
        return ResponseEntity.ok(widthService.updateWidth(id, widthRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWidth(@PathVariable Long id) {
        widthService.deleteWidth(id);
        return ResponseEntity.noContent().build();
    }
}
