package org.raduromaniuc.birds.service;

import org.raduromaniuc.birds.dto.SightingDto;
import org.raduromaniuc.birds.entity.Sighting;
import org.raduromaniuc.birds.exception.ResourceNotFoundException;
import org.raduromaniuc.birds.mapper.SightingMapper;
import org.raduromaniuc.birds.repository.SightingRepository;
import org.raduromaniuc.birds.specification.SightingSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SightingService {

    private final SightingRepository sightingRepository;
    private final SightingMapper sightingMapper;

    public SightingService(SightingRepository sightingRepository, SightingMapper sightingMapper) {
        this.sightingRepository = sightingRepository;
        this.sightingMapper = sightingMapper;
    }

    public Page<SightingDto> getSightings(Long birdId, String location, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        Specification<Sighting> spec = Specification.where(SightingSpecification.hasBird(birdId))
                .and(SightingSpecification.hasLocation(location))
                .and(SightingSpecification.inTimeInterval(start, end));

        return sightingRepository.findAll(spec, pageable).map(sightingMapper::toDto);
    }

    public SightingDto findSightingById(Long id) {
        return sightingRepository.findById(id).map(sightingMapper::toDto).orElse(null);
    }

    @Transactional
    public SightingDto createSighting(SightingDto sighting) {
        return sightingMapper.toDto(
                sightingRepository.save(sightingMapper.toEntity(sighting))
        );
    }

    @Transactional
    public SightingDto updateSighting(Long sightingId, SightingDto sighting) {
        Optional<Sighting> dbSighting = sightingRepository.findById(sightingId);

        if (dbSighting.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find sighting with id: " + sightingId);
        }

        sighting.setId(sightingId);

        return sightingMapper.toDto(
                sightingRepository.save(sightingMapper.toEntity(sighting))
        );
    }

    public void deleteSighting(Long sightingId) {
        Optional<Sighting> sighting = sightingRepository.findById(sightingId);

        if (sighting.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find sighting with id: " + sightingId);
        }

        sightingRepository.delete(sighting.get());
    }
}
