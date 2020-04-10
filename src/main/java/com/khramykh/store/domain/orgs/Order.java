package com.khramykh.store.domain.orgs;

import com.khramykh.store.domain.parts.Part;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Part> parts = new ArrayList<>();
    private Date orderDate;
}
