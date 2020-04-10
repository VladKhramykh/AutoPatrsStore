package com.khramykh.store.repository;

import com.khramykh.store.domain.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {
}
