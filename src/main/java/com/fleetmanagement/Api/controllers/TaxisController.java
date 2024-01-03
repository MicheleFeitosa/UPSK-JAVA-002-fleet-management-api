package com.fleetmanagement.Api.controllers;

import com.fleetmanagement.Api.models.TaxisModels;
import com.fleetmanagement.Api.repositories.TaxisRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaxisController {

    @Autowired
    TaxisRepository taxisRepository;

    @Operation(summary = "Get all taxis from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the taxi",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaxisModels.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Taxi not found",
                    content = @Content) })

   @GetMapping("/taxis")
    public ResponseEntity<List<TaxisModels>> getAllTaxis(){
       return ResponseEntity.status(HttpStatus.OK).body(taxisRepository.findAll());
   }

}
