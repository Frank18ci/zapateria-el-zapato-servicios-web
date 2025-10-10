package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.PriceRequest;
import com.cibertec.dto.PriceResponse;

public interface PriceService {
    
  List<PriceResponse> getAllPrices();

  PriceResponse getPriceById(Long id);

  PriceResponse createPrice(PriceRequest priceRequest);

  PriceResponse updatePrice(Long id, PriceRequest priceRequest);

  void deletePrice(Long id);

}
