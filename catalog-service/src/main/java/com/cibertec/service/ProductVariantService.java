package com.cibertec.service;

import com.cibertec.dto.ProductVariantRequest;
import com.cibertec.dto.ProductVariantResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariantResponse> getAllProductVariants();
    ProductVariantResponse getProductVariantById(Long id);
    ProductVariantResponse createProductVariant(ProductVariantRequest productVariantRequest);
    ProductVariantResponse updateProductVariant(Long id, ProductVariantRequest productVariantRequest);
    void deleteProductVariant(Long id);

    Page<ProductVariantResponse> getAllPaged(int page, int size, String sortBy, String direction, String skuCode);
}
