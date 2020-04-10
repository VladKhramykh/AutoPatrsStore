package com.khramykh.store.domain.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mark")
    private CarMark carMark;
    @ManyToOne
    @JoinColumn(name = "type")
    private CarType carType;
    private int countOfDoors;
    private int year;
}
