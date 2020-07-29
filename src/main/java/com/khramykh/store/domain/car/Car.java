package com.khramykh.store.domain.car;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.util.Views;
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
    @JsonView(Views.ForPart.class)
    private Long id;
    @JsonView(Views.ForPart.class)
    private String model;
    @ManyToOne
    @JoinColumn(name = "mark")
    @JsonView(Views.ForPart.class)
    private CarMark carMark;
    @ManyToOne
    @JoinColumn(name = "type")
    private CarType carType;
    private String transmissionType;
    private int countOfDoors;
    private int engineVolume;
    private String engineType;
    private int year;

}
