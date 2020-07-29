package com.khramykh.store.domain.parts;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.util.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "part_subtypes")
public class PartSubtype {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.ForPartType.class, Views.ForPartSubType.class, Views.ForPart.class})
    private Long id;
    @JsonView({Views.ForPartType.class, Views.ForPartSubType.class, Views.ForPart.class})
    private String name;
    @ManyToOne(targetEntity = PartType.class)
    @JoinColumn(name = "part_type_id")
    @JsonView(Views.ForPartSubType.class)
    private PartType partType;


}

