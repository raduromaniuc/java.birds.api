package org.raduromaniuc.birds.factory;

import org.raduromaniuc.birds.entity.Bird;
import org.springframework.stereotype.Service;

@Service
public class BirdFactory {

    public Bird createDefaultBird() {
        return createBird("test-bird", "test-color", 0.333, 0.777);
    }

    public Bird createBird(String name, String color, Double weight, Double height) {
        Bird bird = new Bird();

        bird.setName(name);
        bird.setColor(color);
        bird.setWeight(weight);
        bird.setHeight(height);

        return bird;
    }
}
