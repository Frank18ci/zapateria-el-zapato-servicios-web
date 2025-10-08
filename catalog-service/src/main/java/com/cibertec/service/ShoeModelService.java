package com.cibertec.service;

import com.cibertec.dto.ShoeModelRequest;
import com.cibertec.dto.ShoeModelResponse;

import java.util.List;

public interface ShoeModelService {
    List<ShoeModelResponse> getAllShoeModels();
    ShoeModelResponse getShoeModelById(Long id);
    ShoeModelResponse createShoeModel(ShoeModelRequest shoeModelRequest);
    ShoeModelResponse updateShoeModel(Long id, ShoeModelRequest shoeModelRequest);
    void deleteShoeModel(Long id);
}
