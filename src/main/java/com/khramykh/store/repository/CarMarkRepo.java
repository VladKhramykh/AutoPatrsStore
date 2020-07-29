package com.khramykh.store.repository;

import com.khramykh.store.domain.car.CarMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarMarkRepo extends JpaRepository<CarMark, Long> {
    CarMark findByName(String name);
}
