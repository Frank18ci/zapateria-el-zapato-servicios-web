package com.cibertec.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.cibertec.dto.PriceRequest;
import com.cibertec.dto.PriceResponse;
import com.cibertec.model.Price;

@Mapper(componentModel = "spring" , uses={PriceListMapper.class})
public interface PriceMapper {

     @Mapping (target = "id", ignore = true) // Ignorar el mapeo del campo 'id' si es generado automáticamente
      Price toEntity(PriceRequest priceRequest); 

      PriceResponse toDto(Price price);

      List<PriceResponse> toDtoList(List<Price> prices);
}
