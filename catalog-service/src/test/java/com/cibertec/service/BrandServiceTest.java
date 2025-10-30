package com.cibertec.service;

import com.cibertec.dto.BrandRequest;
import com.cibertec.dto.BrandResponse;
import com.cibertec.model.Brand;
import com.cibertec.model.ShoeModel;
import com.cibertec.repository.BrandRepository;
import com.cibertec.service.impl.BrandServiceImpl;
import com.cibertec.util.BrandMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    @DisplayName("Obtener todas las marcas - Exitoso")
    void obtenerBrands() {
        List<BrandResponse> brandResponses = List.of(
                new BrandResponse(1L, "Brand A"),
                new BrandResponse(2L, "Brand B"),
                new BrandResponse(3L, "Brand C")
        );
        List<Brand> brands = List.of(
                new Brand(1L, "Brand A", List.of(ShoeModel.builder().build())),
                new Brand(2L, "Brand B", List.of(ShoeModel.builder().build())),
                new Brand(3L, "Brand C", List.of(ShoeModel.builder().build()))
        );
        when(brandRepository.findAll()).thenReturn(brands);
        when(brandMapper.toDtoList(brands)).thenReturn(brandResponses);
        List<BrandResponse> result = brandService.getAllBrands();
        assertAll(
                () -> assertEquals(brandResponses.size(), result.size()),
                () -> assertEquals(brandResponses.getFirst().id(), result.getFirst().id()),
                () -> assertEquals(brandResponses.getFirst().name(), result.getFirst().name()),
                () -> assertEquals(brandResponses.get(1).id(), result.get(1).id()),
                () -> assertEquals(brandResponses.get(1).name(), result.get(1).name())
        );
    }
    @Test
    @DisplayName("Obtener una marca por ID - Exitoso")
    void obtenerBrandPorId() {
        Brand brand = new Brand(1L, "Brand A", List.of());
        BrandResponse brandResponse = new BrandResponse(1L, "Brand A");
        when(brandRepository.findById(1L)).thenReturn(java.util.Optional.of(brand));
        when(brandMapper.toDto(brand)).thenReturn(brandResponse);
        BrandResponse result = brandService.getBrandById(1L);
        assertAll(
                () -> assertEquals(brandResponse.id(), result.id()),
                () -> assertEquals(brandResponse.name(), result.name())
        );
    }
    @Test
    @DisplayName("Crear una marca - Exitoso")
    void crearBrand() {
        BrandRequest brandRequest = new BrandRequest("Brand A");
        Brand brandToSave = new Brand(null, "Brand A", List.of());
        Brand savedBrand = new Brand(1L, "Brand A", List.of());
        BrandResponse brandResponse = new BrandResponse(1L, "Brand A");
        when(brandMapper.toEntity(ArgumentMatchers.any(BrandRequest.class))).thenReturn(brandToSave);
        when(brandRepository.save(ArgumentMatchers.any(Brand.class))).thenReturn(savedBrand);
        when(brandMapper.toDto(ArgumentMatchers.any(Brand.class))).thenReturn(brandResponse);
        BrandResponse result = brandService.createBrand(brandRequest);
        assertAll(
                () -> assertEquals(brandResponse.id(), result.id()),
                () -> assertEquals(brandResponse.name(), result.name())
        );
    }
    @Test
    @DisplayName("Actualizar una marca - Exitoso")
    void actualizarBrand() {
        Long brandId = 1L;
        BrandRequest brandRequest = new BrandRequest("Brand A Updated");
        Brand existingBrand = new Brand(brandId, "Brand A", List.of());
        Brand updatedBrand = new Brand(brandId, "Brand A Updated", List.of());
        BrandResponse brandResponse = new BrandResponse(brandId, "Brand A Updated");
        when(brandRepository.findById(brandId)).thenReturn(java.util.Optional.of(existingBrand));
        when(brandRepository.save(ArgumentMatchers.any(Brand.class))).thenReturn(updatedBrand);
        when(brandMapper.toDto(ArgumentMatchers.any(Brand.class))).thenReturn(brandResponse);
        BrandResponse result = brandService.updateBrand(brandId, brandRequest);
        assertAll(
                () -> assertEquals(brandResponse.id(), result.id()),
                () -> assertEquals(brandResponse.name(), result.name())
        );
    }
    @Test
    @DisplayName("Eliminar una marca - Exitoso")
    void eliminarBrand() {
        Long brandId = 1L;
        Brand existingBrand = new Brand(brandId, "Brand A", List.of());
        when(brandRepository.findById(brandId)).thenReturn(java.util.Optional.of(existingBrand));
        brandService.deleteBrand(brandId);
        verify(brandRepository).delete(existingBrand);
    }
}
