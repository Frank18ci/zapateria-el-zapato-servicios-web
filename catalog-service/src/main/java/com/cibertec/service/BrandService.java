package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.BrandRequest;
import com.cibertec.dto.BrandResponse;
import org.springframework.data.domain.Page;

public interface BrandService {

    List<BrandResponse> getAllBrands();

    BrandResponse getBrandById(Long id);

    BrandResponse createBrand(BrandRequest brandRequest);

    BrandResponse updateBrand(Long id, BrandRequest brandRequest);
    
    void deleteBrand(Long id);

    Page<BrandResponse> getAllBrandsPaged(int page, int size, String sortBy, String direction, String name);
}
