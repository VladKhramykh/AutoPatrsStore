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
@Table(name = "car_marks")
public class CarMark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.ForPart.class)
    private Long id;
    @JsonView(Views.ForPart.class)
    private String name;
    private byte[] logo;

}
