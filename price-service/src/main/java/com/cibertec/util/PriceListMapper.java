package com.cibertec.util;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.model.PriceList;
import com.cibertec.dto.PriceListRequest;
import com.cibertec.dto.PriceListResponse;
import java.util.List;


@Mapper(componentModel = "spring" )
public interface PriceListMapper {
    
    @Mapping (target = "id", ignore = true) 
    PriceList toEntity(PriceListRequest priceListRequest);

    PriceListResponse toDto(PriceList priceList);

    List<PriceListResponse> toDtoList(List<PriceList> priceLists);

    
}
