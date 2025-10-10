package com.cibertec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.dto.PriceListRequest;
import com.cibertec.dto.PriceListResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.PriceList;
import com.cibertec.repository.PriceListRepository;
import com.cibertec.service.PriceListService;
import com.cibertec.util.PriceListMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepository priceListRepository;

    private final PriceListMapper priceListMapper;

    
   @Override
    public List<PriceListResponse> getAllPriceLists() {
        return priceListMapper.toDtoList(priceListRepository.findAll());
    }

    @Override
    public PriceListResponse getPriceListById(Long id) {
        return priceListMapper.toDto(priceListRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("PriceList not found with id: " + id)
        ));
    }
    
    @Override
    public PriceListResponse createPriceList(PriceListRequest priceListRequest) {
        return priceListMapper.toDto(priceListRepository.save(priceListMapper.toEntity(priceListRequest)));
    }

      @Override
    public PriceListResponse updatePriceList(Long id, PriceListRequest priceListRequest) {
        PriceList priceListFound = priceListRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("PriceList not found with id: " + id)
        );
        priceListFound.setName(priceListRequest.name());
        priceListFound.setCurrencyCode(priceListRequest.currencyCode());
        priceListFound.setValidFrom(priceListRequest.validFrom());
        priceListFound.setValidTo(priceListRequest.validTo());
        return priceListMapper.toDto(priceListRepository.save(priceListFound));
    }

      @Override
    public void deletePriceList(Long id) {
        PriceList priceListFound = priceListRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("PriceList not found with id: " + id)
        );
        priceListRepository.delete(priceListFound);
    }
}
