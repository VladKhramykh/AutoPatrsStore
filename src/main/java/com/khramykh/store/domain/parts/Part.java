package com.khramykh.store.domain.parts;

import com.khramykh.store.domain.car.Car;
import com.khramykh.store.domain.orgs.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = PartType.class)
    @JoinColumn(name="part_type")
    private PartType partType;
    @OneToMany
    private List<Car> cars;
    @ManyToMany
    @JoinTable(
            name = "parts_warehouses",
            joinColumns = {@JoinColumn (name="part_id")},
            inverseJoinColumns = {@JoinColumn (name="warehouse_id")}
    )
    private List<Warehouse> warehouses;
}
