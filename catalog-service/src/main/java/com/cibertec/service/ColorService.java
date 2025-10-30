package com.cibertec.service;

import com.cibertec.dto.ColorRequest;
import com.cibertec.dto.ColorResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColorService {
    List<ColorResponse> getAllColors();
    ColorResponse getColorById(Long id);
    ColorResponse createColor(ColorRequest colorRequest);
    ColorResponse updateColor(Long id, ColorRequest colorRequest);
    void deleteColor(Long id);

    Page<ColorResponse> getAllColorsPaged(int page, int size, String sortBy, String direction, String name);
}
