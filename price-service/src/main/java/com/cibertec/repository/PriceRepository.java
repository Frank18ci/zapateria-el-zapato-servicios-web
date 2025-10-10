package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.Price;

public interface PriceRepository  extends JpaRepository<Price, Long> {
    
}
