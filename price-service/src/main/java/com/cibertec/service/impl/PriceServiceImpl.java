package com.cibertec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cibertec.dto.PriceRequest;
import com.cibertec.dto.PriceResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.Price;
import com.cibertec.repository.PriceRepository;
import com.cibertec.service.PriceService;
import com.cibertec.util.PriceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl  implements  PriceService{

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public List<PriceResponse> getAllPrices() {
        return priceMapper.toDtoList(priceRepository.findAll());
    }
     
     @Override
    public PriceResponse getPriceById(Long id) {
        return priceMapper.toDto(priceRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("Price not found with id: " + id)
        ));
    }

     @Override
    public PriceResponse createPrice(PriceRequest priceRequest) {
        return priceMapper.toDto(priceRepository.save(priceMapper.toEntity(priceRequest)));
    }

      @Override
    public PriceResponse updatePrice(Long id, PriceRequest priceRequest) {
        Price priceFound = priceRepository.findById(id).orElseThrow(
            () -> new ResourceNotFound("Price not found with id: " + id)
        );

        priceFound.setUnitPrice(priceRequest.unitPrice());
        priceFound.setVariantId(priceRequest.variantId());
        return priceMapper.toDto(priceRepository.save(priceFound));
    }

     @Override
    public void deletePrice(Long id) {
            Price priceFound = priceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Price not found with id: " + id)
        );
        priceRepository.delete(priceFound);
         
    }
    
}
