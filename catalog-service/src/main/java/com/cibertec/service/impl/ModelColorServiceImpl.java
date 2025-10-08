package com.cibertec.service.impl;

import com.cibertec.dto.ModelColorRequest;
import com.cibertec.dto.ModelColorResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.ModelColor;
import com.cibertec.repository.ModelColorRepository;
import com.cibertec.service.ModelColorService;
import com.cibertec.util.ModelColorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelColorServiceImpl implements ModelColorService {
    private final ModelColorRepository modelColorRepository;
    private final ModelColorMapper modelColorMapper;
    @Override
    public List<ModelColorResponse> getAllModelColors() {
        return modelColorMapper.toDtoList(modelColorRepository.findAll());
    }

    @Override
    public ModelColorResponse getModelColor(Long id) {
        return modelColorMapper.toDto(modelColorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("ModelColor not found with id " + id)
        ));
    }

    @Override
    public ModelColorResponse createModelColor(ModelColorRequest modelColorRequest) {
        return modelColorMapper.toDto(modelColorRepository.save(modelColorMapper.toEntity(modelColorRequest)));
    }

    @Override
    public ModelColorResponse updateModelColor(Long id, ModelColorRequest modelColorRequest) {
        ModelColor modelColorFound = modelColorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("ModelColor not found with id " + id)
        );
        modelColorFound.setModel(modelColorMapper.toEntity(modelColorRequest).getModel());
        modelColorFound.setColor(modelColorMapper.toEntity(modelColorRequest).getColor());
        modelColorFound.setColorCode(modelColorRequest.colorCode());
        return modelColorMapper.toDto(modelColorRepository.save(modelColorFound));
    }

    @Override
    public void deleteModelColor(Long id) {
        ModelColor modelColorFound = modelColorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("ModelColor not found with id " + id)
        );
        modelColorRepository.delete(modelColorFound);
    }
}
