package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.BrandRequest;
import com.cibertec.dto.BrandResponse;

public interface BrandService {

    List<BrandResponse> getAllBrands();

    BrandResponse getBrandById(Long id);

    BrandResponse createBrand(BrandRequest brandRequest);

    BrandResponse updateBrand(Long id, BrandRequest brandRequest);
    
    void deleteBrand(Long id);
}
