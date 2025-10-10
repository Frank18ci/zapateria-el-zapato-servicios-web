package com.cibertec.dto;

import java.math.BigDecimal;

import com.cibertec.model.PriceList;

import lombok.Builder;

@Builder
public record PriceResponse(

    Long id,

    BigDecimal unitPrice,

    Long variantId,
    
    PriceList priceList

){}
