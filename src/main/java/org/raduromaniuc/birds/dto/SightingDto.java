package org.raduromaniuc.birds.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class SightingDto {

    @Schema(description = "Sighting ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    private Long birdId;
    private String location;
    private LocalDateTime dateTime;

    public SightingDto() {}

    public SightingDto(Long id, Long birdId, String location, LocalDateTime dateTime) {
        this.id = id;
        this.birdId = birdId;
        this.location = location;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBirdId() {
        return birdId;
    }

    public void setBirdId(Long birdId) {
        this.birdId = birdId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
