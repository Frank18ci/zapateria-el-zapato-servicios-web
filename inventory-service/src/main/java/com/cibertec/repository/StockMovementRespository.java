package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.StockMovement;

@Repository
public interface  StockMovementRespository  extends JpaRepository<StockMovement, Long> {
    
    
}
