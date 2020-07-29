package com.khramykh.store.service.dto;

import com.khramykh.store.domain.car.CarMark;
import com.khramykh.store.domain.car.CarType;
import com.khramykh.store.domain.parts.PartType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFormDto {
    private String name;
    private  Long partTypeId;
    private Long carId;
    private BigDecimal fromPrice;
    private BigDecimal toPrice;
}
