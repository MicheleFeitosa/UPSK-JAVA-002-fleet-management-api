package com.fleetmanagement.Api.controllers;

import com.fleetmanagement.Api.models.TaxisModels;
import com.fleetmanagement.Api.models.TrajectoriesModels;
import com.fleetmanagement.Api.repositories.TaxisRepository;
import com.fleetmanagement.Api.repositories.TrajectoriesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaxisController {

    @Autowired
    TaxisRepository taxisRepository;

    @Autowired
    TrajectoriesRepository trajectoriesRepository;

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
    public ResponseEntity<Page<TaxisModels>> getAllTaxis(Pageable pageable){
       return ResponseEntity.status(HttpStatus.OK).body(taxisRepository.findAll(pageable));
   }

    @Operation(summary = "Get all trajectories from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the trajectories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrajectoriesModels.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Trajectories not found",
                    content = @Content) })

   @GetMapping("/taxis/{taxiId}")
   public ResponseEntity<Page<TrajectoriesModels>> getTrajectoriesByTaxiId(@PathVariable String taxiId, Pageable pageable){
       return ResponseEntity.status(HttpStatus.OK).body(trajectoriesRepository.findTrajectoriesByTaxiId(taxiId, pageable));
   }

    @Operation(summary = "Get all last location from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the last location",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrajectoriesModels.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Last location not found",
                    content = @Content) })

    @GetMapping("/taxis/last-location")
    public ResponseEntity<Page<TrajectoriesModels>> getTaxiLastLocation(Pageable pageable){
       Page<TrajectoriesModels>trajectoryLastLocation = trajectoriesRepository.findLastLocation(pageable);
       return ResponseEntity.ok(trajectoryLastLocation);

    };



}
