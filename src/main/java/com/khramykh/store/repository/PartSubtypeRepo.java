package com.khramykh.store.repository;

import com.khramykh.store.domain.parts.PartSubtype;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartSubtypeRepo extends JpaRepository<PartSubtype, Long> {
}
