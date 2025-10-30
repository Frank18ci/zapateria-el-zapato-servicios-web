package com.cibertec.repository;

import com.cibertec.model.ProductVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Page<ProductVariant> findBySkuCodeContaining(String skuCode, Pageable pageable);
}
