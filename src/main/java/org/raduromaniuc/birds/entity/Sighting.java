package org.raduromaniuc.birds.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sightings")
public class Sighting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sighting_seq")
    @SequenceGenerator(name = "sighting_seq", sequenceName = "sighting_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bird_id", nullable = false)
    private Bird bird;

    @Column(nullable = false)
    private String location;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public Sighting() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
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
