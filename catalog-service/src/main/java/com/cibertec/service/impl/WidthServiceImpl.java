package com.cibertec.service.impl;

import com.cibertec.dto.WidthRequest;
import com.cibertec.dto.WidthResponse;
import com.cibertec.exception.ResourceNotFound;
import com.cibertec.model.Width;
import com.cibertec.repository.WidthRepository;
import com.cibertec.service.WidthService;
import com.cibertec.util.WidthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WidthServiceImpl implements WidthService {
    private final WidthRepository widthRepository;
    private final WidthMapper widthMapper;
    @Override
    public List<WidthResponse> getAllWidths() {
        return widthMapper.toDtoList(widthRepository.findAll());
    }

    @Override
    public WidthResponse getWidthById(Long id) {
        return widthMapper.toDto(widthRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Width not found with id: " + id)
        ));
    }

    @Override
    public WidthResponse createWidth(WidthRequest widthRequest) {
        return widthMapper.toDto(widthRepository.save(widthMapper.toEntity(widthRequest)));
    }

    @Override
    public WidthResponse updateWidth(Long id, WidthRequest widthRequest) {
        Width widthFound = widthRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Width not found with id: " + id)
        );
        widthFound.setCode(widthRequest.code());
        widthFound.setDescription(widthRequest.description());
        return widthMapper.toDto(widthRepository.save(widthFound));
    }

    @Override
    public void deleteWidth(Long id) {
        Width widthFound = widthRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Width not found with id: " + id)
        );
        widthRepository.delete(widthFound);
    }
}
