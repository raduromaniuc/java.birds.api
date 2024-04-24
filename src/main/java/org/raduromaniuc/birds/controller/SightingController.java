package org.raduromaniuc.birds.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.raduromaniuc.birds.constants.ApiUrl;
import org.raduromaniuc.birds.dto.SightingDto;
import org.raduromaniuc.birds.entity.Sighting;
import org.raduromaniuc.birds.service.SightingService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController //todo move to sightingDto
@RequestMapping(ApiUrl.SIGHTINGS_API_URL)
public class SightingController {

    private final SightingService sightingService;

    public SightingController(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @GetMapping
    @Operation(summary = "Get a page of sightings by a combination of birdId, location, start, end.")
    @ApiResponse(responseCode = "200", description = "Sightings found.",
            content = {@Content(mediaType = "application/json")})
    public Page<SightingDto> getSightings(
            @RequestParam(required = false) Long birdId,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end,
            @ParameterObject Pageable pageable) {
        return sightingService.getSightings(birdId, location, start, end, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a sighting by id.")
    @ApiResponse(responseCode = "200", description = "Sighting found.",
            content = {@Content(mediaType = "application/json")})
    public SightingDto getSightingById(@PathVariable Long id) {
        return sightingService.findSightingById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new sighting.")
    @ApiResponse(responseCode = "200", description = "Sighting created.",
            content = {@Content(mediaType = "application/json")})
    public SightingDto createSighting(@RequestBody SightingDto sighting) {
        return sightingService.createSighting(sighting);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing sighting.")
    @ApiResponse(responseCode = "200", description = "Sighting successfully updated.",
            content = {@Content(mediaType = "application/json")})
    public SightingDto updateSighting(@PathVariable Long id, @RequestBody SightingDto sighting) {
        return sightingService.updateSighting(id, sighting);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing sighting.")
    @ApiResponse(responseCode = "200", description = "Sighting successfully deleted.",
            content = {@Content(mediaType = "application/json")})
    public void deleteSighting(@PathVariable Long id) {
        sightingService.deleteSighting(id);
    }
}
