package org.raduromaniuc.birds.specification;

import org.raduromaniuc.birds.entity.Sighting;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class SightingSpecification {

    public static Specification<Sighting> hasBird(Long birdId) {
        return (root, query, criteriaBuilder) -> {
            if (birdId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("bird").get("id"), birdId);
        };
    }

    public static Specification<Sighting> hasLocation(String location) {
        return (root, query, criteriaBuilder) -> {
            if (location == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("location"), location);
        };
    }

    public static Specification<Sighting> inTimeInterval(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) -> {
            if (start == null || end == null) {
                return null;
            }

            return criteriaBuilder.between(root.get("dateTime"), start, end);
        };
    }
}