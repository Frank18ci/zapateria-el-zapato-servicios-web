package com.cibertec.service;

import com.cibertec.dto.ModelColorRequest;
import com.cibertec.dto.ModelColorResponse;

import java.util.List;

public interface ModelColorService {
    List<ModelColorResponse> getAllModelColors();
    ModelColorResponse getModelColor(Long id);
    ModelColorResponse createModelColor(ModelColorRequest modelColorRequest);
    ModelColorResponse updateModelColor(Long id, ModelColorRequest modelColorRequest);
    void deleteModelColor(Long id);
}
