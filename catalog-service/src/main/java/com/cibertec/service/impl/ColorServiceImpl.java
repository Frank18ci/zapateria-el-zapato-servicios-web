package com.cibertec.service.impl;

import com.cibertec.dto.ColorRequest;
import com.cibertec.dto.ColorResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.Category;
import com.cibertec.model.Color;
import com.cibertec.repository.ColorRepository;
import com.cibertec.service.ColorService;
import com.cibertec.util.ColorMapper;
import com.cibertec.util.SortDirectionDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page<ColorResponse> getAllColorsPaged(int page, int size, String sortBy, String direction, String name) {
        Sort sort = Sort.by(SortDirectionDefault.getSortDirection(direction), sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Color> colors = colorRepository.findByNameContaining(name, pageable);
        return  colors.map(colorMapper::toDto);
    }
}
