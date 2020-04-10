package com.khramykh.store.domain.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_types")
public class CarType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String typeName;
}
