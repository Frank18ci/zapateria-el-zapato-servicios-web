package com.cibertec.service;

import com.cibertec.dto.WidthRequest;
import com.cibertec.dto.WidthResponse;

import java.util.List;

public interface WidthService {
    List<WidthResponse> getAllWidths();
    WidthResponse getWidthById(Long id);
    WidthResponse createWidth(WidthRequest widthRequest);
    WidthResponse updateWidth(Long id, WidthRequest widthRequest);
    void deleteWidth(Long id);
}
