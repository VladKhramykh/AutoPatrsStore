package com.khramykh.store.domain.orgs;

import com.khramykh.store.domain.parts.Part;
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
    private Long id;
    private String name;
    private String address;
    private String telNumber;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToMany
    @JoinTable(
            name = "parts_warehouses",
            joinColumns = {@JoinColumn (name="part_id")},
            inverseJoinColumns = {@JoinColumn (name="warehouse_id")}
    )
    private List<Part> parts;
}
