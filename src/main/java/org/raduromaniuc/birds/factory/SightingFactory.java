package org.raduromaniuc.birds.factory;

import org.raduromaniuc.birds.entity.Bird;
import org.raduromaniuc.birds.entity.Sighting;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SightingFactory {

    public Sighting createSighting(Bird bird, String location, LocalDateTime dateTime) {
        Sighting sighting = new Sighting();

        sighting.setBird(bird);
        sighting.setLocation(location);
        sighting.setDateTime(dateTime);

        return sighting;
    }
}
