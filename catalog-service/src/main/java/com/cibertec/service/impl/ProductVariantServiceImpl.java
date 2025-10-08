package com.cibertec.service.impl;

import com.cibertec.dto.ProductVariantRequest;
import com.cibertec.dto.ProductVariantResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.ProductVariant;
import com.cibertec.repository.ProductVariantRepository;
import com.cibertec.service.ProductVariantService;
import com.cibertec.util.ProductVariantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;
    @Override
    public List<ProductVariantResponse> getAllProductVariants() {
        return productVariantMapper.toDtoList(productVariantRepository.findAll());
    }

    @Override
    public ProductVariantResponse getProductVariantById(Long id) {
        return productVariantMapper.toDto(productVariantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("ProductVariant not found with id " + id)
        ));
    }

    @Override
    public ProductVariantResponse createProductVariant(ProductVariantRequest productVariantRequest) {
        return productVariantMapper.toDto(productVariantRepository.save(productVariantMapper.toEntity(productVariantRequest)));
    }

    @Override
    public ProductVariantResponse updateProductVariant(Long id, ProductVariantRequest productVariantRequest) {
        ProductVariant productVariantFound = productVariantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("ProductVariant not found with id " + id)
        );
        productVariantFound.setModelColor(productVariantMapper.toEntity(productVariantRequest).getModelColor());
        productVariantFound.setSize(productVariantMapper.toEntity(productVariantRequest).getSize());
        productVariantFound.setWidth(productVariantMapper.toEntity(productVariantRequest).getWidth());
        productVariantFound.setSkuCode(productVariantRequest.skuCode());
        productVariantFound.setStatus(productVariantRequest.status());
        return productVariantMapper.toDto(productVariantRepository.save(productVariantFound));
    }

    @Override
    public void deleteProductVariant(Long id) {
        ProductVariant productVariantFound = productVariantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("ProductVariant not found with id " + id)
        );
        productVariantRepository.delete(productVariantFound);
    }
}
