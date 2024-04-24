package org.raduromaniuc.birds.service;

import org.raduromaniuc.birds.dto.BirdDto;
import org.raduromaniuc.birds.entity.Bird;
import org.raduromaniuc.birds.exception.ResourceNotFoundException;
import org.raduromaniuc.birds.mapper.BirdMapper;
import org.raduromaniuc.birds.repository.BirdRepository;
import org.raduromaniuc.birds.specification.BirdSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BirdService {

    private final BirdRepository birdRepository;
    private final BirdMapper birdMapper;

    public BirdService(BirdRepository birdRepository, BirdMapper birdMapper) {
        this.birdRepository = birdRepository;
        this.birdMapper = birdMapper;
    }

    public Page<BirdDto> getBirds(String name, String color, Pageable pageable) {
        Specification<Bird> spec = Specification.where(BirdSpecification.hasName(name))
                .and(BirdSpecification.hasColor(color));

        return birdRepository.findAll(spec, pageable)
                .map(birdMapper::toDto);
    }

    public BirdDto findBirdById(Long birdId) {
        return birdRepository.findById(birdId).map(birdMapper::toDto).orElseThrow(
                () -> new ResourceNotFoundException("Couldn't find bird with id " + birdId)
        );
    }

    public BirdDto updateBird(Long birdId, BirdDto bird) {

        Optional<Bird> dbBird = birdRepository.findById(birdId);

        if (dbBird.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find bird with id " + birdId);
        }

        bird.setId(birdId);

        return birdMapper.toDto(
                birdRepository.save(birdMapper.toEntity(bird))
        );
    }

    public BirdDto createBird(BirdDto bird) {
        return birdMapper.toDto(
                birdRepository.save(birdMapper.toEntity(bird))
        );
    }

    public void deleteBird(Long birdId) {
        Optional<Bird> bird = birdRepository.findById(birdId);

        if (bird.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find bird with id " + birdId);
        }

        birdRepository.delete(bird.get());
    }
}
