package com.khramykh.store.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartAddDto implements Serializable {
    private Long id;
    private String name;
    private BigDecimal price;

}
