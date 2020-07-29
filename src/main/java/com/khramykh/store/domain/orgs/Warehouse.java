package com.khramykh.store.domain.orgs;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.domain.parts.Part;
import com.khramykh.store.util.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class, Views.ForPart.class})
    private Long id;
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class, Views.ForPart.class})
    private String name;
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class, Views.ForPart.class})
    private String address;
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class})
    private String telNumber;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonView(Views.ForWarehouse.class)
    private Company company;
    @ManyToMany
    @JoinTable(
            name = "parts_warehouses",
            joinColumns = {@JoinColumn (name="part_id")},
            inverseJoinColumns = {@JoinColumn (name="warehouse_id")}
    )
    private List<Part> parts;

    public Warehouse(Long id) {
        this.id = id;
    }
}
