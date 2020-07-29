package com.khramykh.store.domain.parts;

import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "part_types")
public class PartType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.ForPartType.class, Views.ForPartSubType.class, Views.ForPart.class})
    private Long id;
    @JsonView({Views.ForPartType.class, Views.ForPartSubType.class, Views.ForPart.class})
    private String name;
    @OneToMany(mappedBy = "partType")
    @JsonView(Views.ForPartType.class)
    List<PartSubtype> partSubtypes;
}
