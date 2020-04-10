package com.khramykh.store.repository;

import com.khramykh.store.domain.parts.PartType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartTypeRepo extends JpaRepository<PartType, Long> {
}
