package com.cibertec.service;

import java.util.List;

import com.cibertec.dto.StockMovementRequest;
import com.cibertec.dto.StockMovementResponse;

public interface StockMovementService {


    List<StockMovementResponse> getAllStockMovements();

    StockMovementResponse getStockMovementById(Long id);

    StockMovementResponse createStockMovement(StockMovementRequest stockMovementRequest);

    StockMovementResponse updateStockMovement(Long id, StockMovementRequest stockMovementRequest);

    void deleteStockMovement(Long id);
}
