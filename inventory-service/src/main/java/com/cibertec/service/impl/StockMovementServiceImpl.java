package com.cibertec.service.impl;

import java.util.List;

import com.cibertec.client.ProductVariantClient;
import org.springframework.stereotype.Service;

import com.cibertec.dto.StockMovementRequest;
import com.cibertec.dto.StockMovementResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.StockMovement;
import com.cibertec.repository.StockMovementRepository;
import com.cibertec.service.StockMovementService;
import com.cibertec.util.StockMovementMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;

    private final ProductVariantClient productVariantClient;

    @Override
    public List<StockMovementResponse> getAllStockMovements() {
        return stockMovementMapper.toDtoList(stockMovementRepository.findAll());
    }

    @Override
    public StockMovementResponse getStockMovementById(Long id) {
        return stockMovementMapper.toDto(stockMovementRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("StockMovement not found with id: " + id)
        ));
    }

    @Override
    public StockMovementResponse createStockMovement(StockMovementRequest request) {
        productVariantClient.getProductVariantById(request.variantId());
        return stockMovementMapper.toDto(stockMovementRepository.save(stockMovementMapper.toEntity(request)));
    }


    @Override
    public StockMovementResponse updateStockMovement(Long id, StockMovementRequest request) {
        StockMovement stockMovementFound = stockMovementRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("StockMovement not found with id: " + id)
        );

        productVariantClient.getProductVariantById(request.variantId());

        stockMovementFound.setVariantId(request.variantId());
        stockMovementFound.setBinCode(request.binCode());
        stockMovementFound.setMovementType(request.movementType());
        stockMovementFound.setQuantity(request.quantity());
        stockMovementFound.setUnitCost(request.unitCost());
        stockMovementFound.setCurrencyCode(request.currencyCode());
        stockMovementFound.setReason(request.reason());
        stockMovementFound.setRefDoc(request.refDoc());
        stockMovementFound.setWarehouse(stockMovementMapper.toEntity(request).getWarehouse());
        return stockMovementMapper.toDto(stockMovementRepository.save(stockMovementFound));

    }

    @Override
    public void deleteStockMovement(Long id) {
        StockMovement stockMovementFound = stockMovementRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("StockMovement not found with id: " + id)
        );
        stockMovementRepository.delete(stockMovementFound);
    }
}
