package com.cibertec.controller;

import com.cibertec.dto.ProductVariantEmailRequest;
import com.cibertec.dto.ProductVariantRequest;
import com.cibertec.event.producer.NotificationProducer;
import com.cibertec.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-variants")
public class ProductVariantController {
    private final ProductVariantService productVariantService;
    private final NotificationProducer notificationProducer;
    @GetMapping
    public ResponseEntity<?> getAllProductVariants() {
        return ResponseEntity.ok(productVariantService.getAllProductVariants());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductVariantById(@PathVariable Long id) {
        return ResponseEntity.ok(productVariantService.getProductVariantById(id));
    }
    @PostMapping
    public ResponseEntity<?> createProductVariant(@RequestBody @Valid ProductVariantRequest productVariantRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productVariantService.createProductVariant(productVariantRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductVariant(@PathVariable Long id, @RequestBody @Valid ProductVariantRequest productVariantRequest) {
        return ResponseEntity.ok(productVariantService.updateProductVariant(id, productVariantRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductVariant(@PathVariable Long id) {
        productVariantService.deleteProductVariant(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestBody ProductVariantEmailRequest productVariantEmailRequest) {
        notificationProducer.sendProductVariantNotification(productVariantEmailRequest.email(), productVariantEmailRequest.productVariantId());
        return ResponseEntity.ok("Email notification request sent.");
    }
}
