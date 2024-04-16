package org.atique.aws.dynamodb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.atique.aws.dynamodb.domain.Bike;
import org.atique.aws.dynamodb.domain.BikeType;
import org.atique.aws.dynamodb.service.BikeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author atiQue
 * @since 23'Feb 2024 at 8:39 PM
 */

@RestController
@RequestMapping(value = "/api/v1/bike")
public class BikeController {

    private final BikeService service;

    public BikeController(BikeService service) {
        this.service = service;
    }

    @Operation(summary = "Create bike document", tags = {"Bike"})
    @ApiResponses(value = {@ApiResponse(content = {@Content(schema = @Schema(implementation = Bike.class))})})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Bike createBike(@RequestBody Bike request) {
        return service.save(request);
    }

    @Operation(summary = "Retrieve bike document by id", tags = {"Bike"})
    @ApiResponses(value = {@ApiResponse(content = {@Content(schema = @Schema(implementation = Bike.class))})})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bike getBike(@PathVariable String id) {
        return service.get(id);
    }

    @Operation(summary = "Search bike document by company, type, maxPrice", tags = {"Bike"})
    @ApiResponses(value = {@ApiResponse(content = {@Content(schema = @Schema(implementation = Bike.class))})})
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bike> searchBike(@RequestParam(required = false) String company,
                                 @RequestParam(required = false) BikeType type,
                                 @RequestParam(required = false) BigDecimal maxPrice) {
        return service.search(company, type, maxPrice);
    }

    @Operation(summary = "Update bike document by id", tags = {"Bike"})
    @ApiResponses(value = {@ApiResponse(content = {@Content(schema = @Schema(implementation = Bike.class))})})
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bike updateBike(@PathVariable String id, @RequestBody Bike request) {
        return service.update(id, request);
    }

    @Operation(summary = "Delete bike document by id", tags = {"Bike", "Document"})
    @ApiResponses(value = {@ApiResponse(content = {@Content(schema = @Schema(implementation = Bike.class))})})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBike(@PathVariable String id) {
        service.delete(id);
    }
}
