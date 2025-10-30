package com.cibertec.service.impl;

import java.util.List;

import com.cibertec.util.SortDirectionDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cibertec.dto.BrandRequest;
import com.cibertec.dto.BrandResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.Brand;
import com.cibertec.repository.BrandRepository;
import com.cibertec.service.BrandService;
import com.cibertec.util.BrandMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    
    @Override
    public List<BrandResponse> getAllBrands() {
        return brandMapper.toDtoList(brandRepository.findAll());
    }

    @Override
    public BrandResponse getBrandById(Long id) {
        return brandMapper.toDto(brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Brand not found with id: " + id)
        ));
    }

    @Override
    public BrandResponse createBrand(BrandRequest brandRequest) {
        return brandMapper.toDto(brandRepository.save(brandMapper.toEntity(brandRequest)));
    }

    @Override
    public BrandResponse updateBrand(Long id, BrandRequest brandRequest) {
        Brand brandFound = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Brand not found with id: " + id)
        );
        brandFound.setName(brandRequest.name());
        return brandMapper.toDto(brandRepository.save(brandFound));
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brandFound = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Brand not found with id: " + id)
        );
        brandRepository.delete(brandFound);
    }

    @Override
    public Page<BrandResponse> getAllBrandsPaged(int page, int size, String sortBy, String direction, String name) {
        Sort sort = Sort.by(SortDirectionDefault.getSortDirection(direction), sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Brand> brands = brandRepository.findByNameContaining(name, pageable);
        return  brands.map(brandMapper::toDto);
    }
}
