package com.cibertec.service.impl;

import com.cibertec.dto.ColorRequest;
import com.cibertec.dto.ColorResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.Color;
import com.cibertec.repository.ColorRepository;
import com.cibertec.service.ColorService;
import com.cibertec.util.ColorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;
    @Override
    public List<ColorResponse> getAllColors() {
        return colorMapper.toDtoList(colorRepository.findAll());
    }

    @Override
    public ColorResponse getColorById(Long id) {
        return colorMapper.toDto(colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Color not found with id: " + id)
        ));
    }

    @Override
    public ColorResponse createColor(ColorRequest colorRequest) {
        return colorMapper.toDto(colorRepository.save(colorMapper.toEntity(colorRequest)));
    }

    @Override
    public ColorResponse updateColor(Long id, ColorRequest colorRequest) {
        Color colorFound = colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Color not found with id: " + id)
        );
        colorFound.setName(colorRequest.name());
        colorFound.setHex(colorRequest.hex());
        return colorMapper.toDto(colorRepository.save(colorFound));
    }

    @Override
    public void deleteColor(Long id) {
        Color colorFound = colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Color not found with id: " + id)
        );
        colorRepository.delete(colorFound);
    }
}
