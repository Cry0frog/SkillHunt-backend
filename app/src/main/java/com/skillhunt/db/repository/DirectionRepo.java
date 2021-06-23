package com.skillhunt.db.repository;

import com.skillhunt.db.direction.DirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepo extends JpaRepository<DirectionEntity, Long> {
}
