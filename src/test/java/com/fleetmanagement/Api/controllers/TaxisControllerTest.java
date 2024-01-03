package com.fleetmanagement.Api.controllers;

import com.fleetmanagement.Api.models.TaxisModels;
import com.fleetmanagement.Api.repositories.TaxisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@WebMvcTest(TaxisController.class)
public class TaxisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaxisRepository taxisRepository;

    @Test
    public void testGetAllTaxis() throws Exception {
        // Mockando os dados que você espera obter do repositório
        TaxisModels taxi1 = new TaxisModels(/* preencha com dados apropriados */);
        TaxisModels taxi2 = new TaxisModels(/* preencha com dados apropriados */);
        List<TaxisModels> taxisList = Arrays.asList(taxi1, taxi2);

        // Mockando o comportamento do repositório
        when(taxisRepository.findAll()).thenReturn(taxisList);

        // Realizando a requisição GET para /taxis
        mockMvc.perform(MockMvcRequestBuilders.get("/taxis")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        // Adicione mais verificações conforme necessário
        );
    }
}
