package com.cibertec.service;

import com.cibertec.dto.BrandRequest;
import com.cibertec.dto.BrandResponse;

import java.util.List;

public interface BrandService {
    List<BrandResponse> getAllBrands();
    BrandResponse getBrandById(Long id);
    BrandResponse createBrand(BrandRequest brandRequest);
    BrandResponse updateBrand(Long id, BrandRequest brandRequest);
    void deleteBrand(Long id);
}
