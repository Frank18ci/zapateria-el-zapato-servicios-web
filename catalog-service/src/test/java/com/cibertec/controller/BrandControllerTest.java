package com.cibertec.controller;

import com.cibertec.dto.BrandResponse;
import com.cibertec.exception.ExceptionHandleController;
import com.cibertec.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BrandControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private BrandController controller;

    @Mock
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ExceptionHandleController())
                .build();
    }

    @Test
    @DisplayName("Obtener todas las marcas - Exitoso")
    void obtenerBrands() throws Exception{
        List<BrandResponse> brandResponses = List.of(
                new BrandResponse(1L, "Brand A"),
                new BrandResponse(2L, "Brand B"),
                new BrandResponse(3L, "Brand C")
        );
        when(brandService.getAllBrands()).thenReturn(brandResponses);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/brands")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(brandResponses.size()))
                .andExpect(jsonPath("$[0].id").value(brandResponses.get(0).id()))
                .andExpect(jsonPath("$[0].name").value(brandResponses.get(0).name()))
                .andExpect(jsonPath("$[1].id").value(brandResponses.get(1).id()))
                .andExpect(jsonPath("$[1].name").value(brandResponses.get(1).name()))
                .andExpect(jsonPath("$[2].id").value(brandResponses.get(2).id()))
                .andExpect(jsonPath("$[2].name").value(brandResponses.get(2).name()));
    }
    @Test
    @DisplayName("Obtener una marca por ID - Exitoso")
    void obtenerBrandPorId() throws Exception {
        Long brandId = 1L;
        BrandResponse brandResponse = new BrandResponse(brandId, "Brand A");
        when(brandService.getBrandById(brandId)).thenReturn(brandResponse);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/brands/{id}", brandId)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(brandResponse.id()))
                .andExpect(jsonPath("$.name").value(brandResponse.name()));
    }
    @Test
    @DisplayName("Crear una nueva marca - Exitoso")
    void crearBrand() throws Exception {
        Long brandId = 1L;
        BrandResponse brandResponse = new BrandResponse(brandId, "Brand A");
        when(brandService.createBrand(ArgumentMatchers.any())).thenReturn(brandResponse);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/brands")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"Brand A\"}")
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(brandResponse.id()))
                .andExpect(jsonPath("$.name").value(brandResponse.name()));
    }
    @Test
    @DisplayName("Actualizar una marca existente - Exitoso")
    void actualizarBrand() throws Exception {
        Long brandId = 1L;
        BrandResponse brandResponse = new BrandResponse(brandId, "Brand A Updated");
        when(brandService.updateBrand(ArgumentMatchers.eq(brandId), ArgumentMatchers.any())).thenReturn(brandResponse);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/brands/{id}", brandId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"Brand A Updated\"}")
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(brandResponse.id()))
                .andExpect(jsonPath("$.name").value(brandResponse.name()));
    }
    @Test
    @DisplayName("Eliminar una marca por ID - Exitoso")
    void eliminarBrand() throws Exception {
        Long brandId = 1L;
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/brands/{id}", brandId)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }
}
