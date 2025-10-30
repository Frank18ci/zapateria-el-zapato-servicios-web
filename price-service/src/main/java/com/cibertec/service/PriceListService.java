package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.PriceListRequest;
import com.cibertec.dto.PriceListResponse;
import org.springframework.data.domain.Page;

public interface PriceListService {

    List<PriceListResponse> getAllPriceLists();

    PriceListResponse getPriceListById(Long id);

    PriceListResponse createPriceList(PriceListRequest priceListRequest);

    PriceListResponse updatePriceList(Long id, PriceListRequest priceListRequest);

    void deletePriceList(Long id);

    Page<PriceListResponse> getAllPaged(int page, int size, String sortBy, String direction, String name);
}
