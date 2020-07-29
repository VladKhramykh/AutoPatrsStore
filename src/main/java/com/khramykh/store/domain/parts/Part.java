package com.khramykh.store.domain.parts;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.domain.car.Car;
import com.khramykh.store.domain.orgs.Warehouse;
import com.khramykh.store.util.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.ForPartType.class, Views.ForPart.class})
    private Long id;
    @JsonView(Views.ForPart.class)
    private String name;
    @JsonView(Views.ForPart.class)
    private BigDecimal price;
    @ManyToOne(targetEntity = PartType.class)
    @JoinColumn(name = "part_type")
    @JsonView({Views.ForPartType.class, Views.ForPart.class})
    private PartType partType;

    @ManyToOne(targetEntity = PartSubtype.class)
    @JoinColumn(name = "part_subtype")
    @JsonView({Views.ForPartType.class, Views.ForPart.class, Views.ForPartSubType.class})
    private PartSubtype partSubtype;

    @OneToMany
    @JsonView(Views.ForPart.class)
    private List<Car> cars;
    @ManyToMany
    @JoinTable(
            name = "parts_warehouses",
            joinColumns = {@JoinColumn(name = "part_id")},
            inverseJoinColumns = {@JoinColumn(name = "warehouse_id")}
    )
    @JsonView(Views.ForPart.class)
    private List<Warehouse> warehouses;
}
