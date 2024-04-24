package org.raduromaniuc.birds.repository;

import org.raduromaniuc.birds.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Long>, JpaSpecificationExecutor<Bird> {
}
