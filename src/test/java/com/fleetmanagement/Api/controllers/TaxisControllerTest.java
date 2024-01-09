package com.fleetmanagement.Api.controllers;

import com.fleetmanagement.Api.models.TaxisModels;
import com.fleetmanagement.Api.models.TrajectoriesModels;
import com.fleetmanagement.Api.repositories.TaxisRepository;
import com.fleetmanagement.Api.repositories.TrajectoriesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(TaxisController.class)
public class TaxisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxisRepository taxisRepository;

    @MockBean
    private TrajectoriesRepository trajectoriesRepository;

    @Test
    public void testGetAllTaxis() throws Exception {
        TaxisModels taxi1 = new TaxisModels();
        taxi1.setId(1);
        taxi1.setPlate("56789");
        Page<TaxisModels> taxisPage = new PageImpl<>(List.of(taxi1));


        when(taxisRepository.findAll(any(Pageable.class))).thenReturn(taxisPage);


        mockMvc.perform(MockMvcRequestBuilders.get("/taxis")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap())
                .andExpect(MockMvcResultMatchers.content().json(" {content=[{\"id\":1,\"plate\":\"56789\"}], pageable=INSTANCE, last=true, totalElements=1, totalPages=1, size=1, number=0, sort={empty=true, sorted=false, unsorted=true}, numberOfElements=1, first=true, empty=false}"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(1));
    }

    @Test // configuração do mock
    public void getTrajectoriesByTaxiId() throws Exception{
        TrajectoriesModels trajectory = new TrajectoriesModels();
        TaxisModels taxi1 = new TaxisModels();
        taxi1.setPlate("56789");
        taxi1.setId(2);
        trajectory.setId(1);
        trajectory.setTaxi(taxi1);
        trajectory.setDate(LocalDateTime.parse("2008-02-03T19:00:21"));
        trajectory.setLatitude(116.29102);
        trajectory.setLongitude(39.88669);
        Page<TrajectoriesModels> trajectoriesPage = new PageImpl<>(List.of(trajectory));

        when(trajectoriesRepository.findTrajectoriesByTaxiId(anyString() ,any(Pageable.class))).thenReturn(trajectoriesPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/taxis/7249") // simula chamada do postman
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // espere o status http 200 = ok
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap())
                .andExpect(MockMvcResultMatchers.content().json("{\"content\":[{\"id\":1,\"taxi\":{\"id\":2,\"" +
                        "plate\":\"56789\"},\"date\":\"2008-02-03T19:00:21\",\"latitude\":116.29102,\"longitude\":39.886" +
                        "69}],\"pageable\":\"INSTANCE\",\"totalElements\":1,\"totalPages\":1,\"last\":true,\"size\":1,\"" +
                        "number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"numberOfElements\":1," +
                        "\"first\":true,\"empty\":false}"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(1));









    }
}

