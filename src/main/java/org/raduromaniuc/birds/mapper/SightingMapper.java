package org.raduromaniuc.birds.mapper;

import org.raduromaniuc.birds.dto.SightingDto;
import org.raduromaniuc.birds.entity.Sighting;
import org.raduromaniuc.birds.exception.ResourceNotFoundException;
import org.raduromaniuc.birds.repository.BirdRepository;
import org.springframework.stereotype.Service;

@Service
public class SightingMapper {

    private final BirdRepository birdRepo;

    public SightingMapper(BirdRepository birdRepo) {
        this.birdRepo = birdRepo;
    }

    public  SightingDto toDto(Sighting sighting) {
        if (sighting == null) {
            return null;
        }

        return new SightingDto(
                sighting.getId(),
                sighting.getBird().getId(),
                sighting.getLocation(),
                sighting.getDateTime()
        );
    }

    public Sighting toEntity(SightingDto sightingDto) {
        if (sightingDto == null) {
            return null;
        }

        Sighting sighting = new Sighting();
        sighting.setId(sightingDto.getId());
        sighting.setBird(birdRepo.findById(sightingDto.getBirdId())
                .orElseThrow(() -> new ResourceNotFoundException("Could not find bird with id " + sightingDto.getBirdId())));
        sighting.setLocation(sightingDto.getLocation());
        sighting.setDateTime(sightingDto.getDateTime());

        return sighting;
    }
}
