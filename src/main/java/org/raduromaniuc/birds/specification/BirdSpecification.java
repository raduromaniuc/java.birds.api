package org.raduromaniuc.birds.specification;

import org.raduromaniuc.birds.entity.Bird;
import org.springframework.data.jpa.domain.Specification;

public class BirdSpecification {

    public static Specification<Bird> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    public static Specification<Bird> hasColor(String color) {
        return (root, query, criteriaBuilder) -> {
            if (color == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("color"), color);
        };
    }
}
