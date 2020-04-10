package com.khramykh.store.repository;

import com.khramykh.store.domain.parts.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepo extends JpaRepository<Part, Long> {
}
