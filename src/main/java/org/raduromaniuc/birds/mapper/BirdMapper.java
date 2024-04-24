package org.raduromaniuc.birds.mapper;

import org.raduromaniuc.birds.dto.BirdDto;
import org.raduromaniuc.birds.entity.Bird;
import org.springframework.stereotype.Service;

@Service
public class BirdMapper {
    public BirdDto toDto(Bird bird) {
        if (bird == null) {
            return null;
        }

        BirdDto dto = new BirdDto();

        dto.setId(bird.getId());
        dto.setName(bird.getName());
        dto.setColor(bird.getColor());
        dto.setWeight(bird.getWeight());
        dto.setHeight(bird.getHeight());

        return dto;
    }

    public Bird toEntity(BirdDto dto) {
        if (dto == null) {
            return null;
        }

        Bird bird = new Bird();

        bird.setId(dto.getId());
        bird.setName(dto.getName());
        bird.setColor(dto.getColor());
        bird.setWeight(dto.getWeight());
        bird.setHeight(dto.getHeight());

        return bird;
    }
}
