package com.cibertec.service.impl;

import com.cibertec.dto.ShoeModelRequest;
import com.cibertec.dto.ShoeModelResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.ShoeModel;
import com.cibertec.repository.ShoeModelRepository;
import com.cibertec.service.ShoeModelService;
import com.cibertec.util.ShoeModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoeModelServiceImpl implements ShoeModelService {
    private final ShoeModelRepository shoeModelRepository;
    private final ShoeModelMapper shoeModelMapper;
    @Override
    public List<ShoeModelResponse> getAllShoeModels() {
        return shoeModelMapper.toDtoList(shoeModelRepository.findAll());
    }

    @Override
    public ShoeModelResponse getShoeModelById(Long id) {
        return shoeModelMapper.toDto(shoeModelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Shoe model not found with id " + id)
        ));
    }

    @Override
    public ShoeModelResponse createShoeModel(ShoeModelRequest shoeModelRequest) {
        return shoeModelMapper.toDto(shoeModelRepository.save(shoeModelMapper.toEntity(shoeModelRequest)));
    }

    @Override
    public ShoeModelResponse updateShoeModel(Long id, ShoeModelRequest shoeModelRequest) {
        ShoeModel shoeModelFound = shoeModelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Shoe model not found with id " + id)
        );
        shoeModelFound.setBrand(shoeModelMapper.toEntity(shoeModelRequest).getBrand());
        shoeModelFound.setCategory(shoeModelMapper.toEntity(shoeModelRequest).getCategory());
        shoeModelFound.setGenderCode(shoeModelRequest.genderCode());
        shoeModelFound.setCode(shoeModelRequest.code());
        shoeModelFound.setName(shoeModelRequest.name());
        shoeModelFound.setDescription(shoeModelRequest.description());
        shoeModelFound.setReleaseYear(shoeModelRequest.releaseYear());
        shoeModelFound.setDiscontinuedAt(shoeModelRequest.discontinuedAt());
        return shoeModelMapper.toDto(shoeModelRepository.save(shoeModelFound));
    }

    @Override
    public void deleteShoeModel(Long id) {
        ShoeModel shoeModelFound = shoeModelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Shoe model not found with id " + id)
        );
        shoeModelRepository.delete(shoeModelFound);
    }
}
