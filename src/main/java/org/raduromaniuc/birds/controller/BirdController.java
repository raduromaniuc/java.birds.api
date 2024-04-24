package org.raduromaniuc.birds.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.raduromaniuc.birds.constants.ApiUrl;
import org.raduromaniuc.birds.dto.BirdDto;
import org.raduromaniuc.birds.entity.Bird;
import org.raduromaniuc.birds.service.BirdService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrl.BIRDS_API_URL)
public class BirdController {

    private final BirdService birdService;

    public BirdController(BirdService birdService) {
        this.birdService = birdService;
    }

    @GetMapping
    @Operation(summary = "Get a page of birds by name and/or colour.")
    @ApiResponse(responseCode = "200", description = "Birds found.",
            content = {@Content(mediaType = "application/json")})
    public Page<BirdDto> getBirds(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "color", required = false) String color,
            @ParameterObject Pageable pageable) {
        return birdService.getBirds(name, color, pageable);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a bird by id.")
    @ApiResponse(responseCode = "200", description = "Bird found.",
            content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "204", description = "Bird not found.")
    public BirdDto getBirdById(@PathVariable Long id) {
        return birdService.findBirdById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new bird.")
    @ApiResponse(responseCode = "200", description = "Bird successfully created.",
            content = {@Content(mediaType = "application/json")})
    public BirdDto createBird(@RequestBody BirdDto bird) {
        return birdService.createBird(bird);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing bird.")
    @ApiResponse(responseCode = "200", description = "Bird successfully updated.",
            content = {@Content(mediaType = "application/json")})
    public BirdDto updateBird(@PathVariable Long id, @RequestBody BirdDto bird) {
        return birdService.updateBird(id, bird);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing bird.")
    @ApiResponse(responseCode = "200", description = "Bird successfully deleted.",
            content = {@Content(mediaType = "application/json")})
    public void deleteBird(@PathVariable Long id) {
        birdService.deleteBird(id);
    }
}
