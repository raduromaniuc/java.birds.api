package org.raduromaniuc.birds.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "birds")
public class Bird {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bird_seq")
    @SequenceGenerator(name = "bird_seq", sequenceName = "bird_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    @OneToMany(mappedBy = "bird")
    private Set<Sighting> sightings = new HashSet<>();

    public Bird() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Set<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(Set<Sighting> sightings) {
        this.sightings = sightings;
    }
}
