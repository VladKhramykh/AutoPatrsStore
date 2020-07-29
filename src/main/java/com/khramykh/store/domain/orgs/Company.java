package com.khramykh.store.domain.orgs;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.util.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class})
    private Long id;
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class})
    private String name;
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class})
    private String directorName;
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class})
    private String officeAddress;
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class})
    private String telNumber;
    @JsonView({Views.ForWarehouse.class, Views.ForCompany.class})
    private String description;
    @OneToMany(mappedBy = "company")
    private Set<Warehouse> warehouses;

}
