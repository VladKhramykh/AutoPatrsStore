package com.khramykh.store.service.dto;

import com.khramykh.store.domain.car.CarMark;
import com.khramykh.store.domain.car.CarType;
import com.khramykh.store.domain.orgs.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainDto {
    private HashSet<CarType> carTypes = new HashSet<>();
    private HashSet<CarMark> carMarks = new HashSet<>();
    private Company company;
}
