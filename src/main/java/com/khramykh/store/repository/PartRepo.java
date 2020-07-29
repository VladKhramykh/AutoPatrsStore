package com.khramykh.store.repository;

import com.khramykh.store.domain.car.Car;
import com.khramykh.store.domain.parts.Part;
import com.khramykh.store.domain.parts.PartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartRepo extends JpaRepository<Part, Long> {
    List<Part> findByCarsAndPartType(Car car, PartType partType);

    @Query(value = "select count(p) from Part p")
    int getCount();

    @Query(value = "select p from Part p  where p.name like %:name% and p.partType.id = :part_type and :car_id = any(select c.id from p.cars c)")
    List<Part> searchPartsBySearchForm(
            @Param("name") String name,
            @Param("part_type") Long partTypeId,
            @Param("car_id") Long carId
    );
}
