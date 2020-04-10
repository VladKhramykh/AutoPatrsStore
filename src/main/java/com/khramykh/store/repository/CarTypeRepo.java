package com.khramykh.store.repository;

import com.khramykh.store.domain.car.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepo extends JpaRepository<CarType, Long> {
}
