package com.khramykh.store.domain.orgs;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Getter
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String directorName;
    private String officeAddress;
    private String telNumber;
    private String description;
    @OneToMany(mappedBy = "company")
    private Set<Warehouse> warehouses;

}
