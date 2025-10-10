package com.cibertec.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PriceListRequest(

              @NotBlank(message = "Name es obligatorio")
              String name,

              @NotBlank(message = "Currency code es obligatorio")
              String currencyCode,

              @NotNull(message = "Valid from es obligatorio")
              LocalDate validFrom,

              @NotNull(message = "Valid to es obligatorio")
              LocalDate validTo

) {}
