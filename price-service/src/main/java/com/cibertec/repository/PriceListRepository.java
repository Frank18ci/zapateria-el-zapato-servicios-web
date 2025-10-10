package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.PriceList;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {
    
}
