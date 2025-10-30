package com.cibertec.client;

import com.cibertec.client.dto.ProductVariantResponse;
import com.cibertec.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", configuration = FeignClientConfig.class)
public interface ProductVariantClient {
    @GetMapping("/product-variants/{id}")
    ProductVariantResponse getProductVariantById(@PathVariable Long id);
}
