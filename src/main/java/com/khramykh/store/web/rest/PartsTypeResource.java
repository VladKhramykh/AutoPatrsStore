package com.khramykh.store.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.domain.parts.Part;
import com.khramykh.store.domain.parts.PartType;
import com.khramykh.store.repository.PartRepo;
import com.khramykh.store.repository.PartTypeRepo;
import com.khramykh.store.util.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/car-parts-types")
public class PartsTypeResource {
    @Autowired
    private PartTypeRepo partTypeRepo;

    @GetMapping
    @JsonView(Views.ForPartType.class)
    public ResponseEntity all() {
        return ResponseEntity.ok().body(partTypeRepo.findAll());
    }

    @GetMapping("{id}")
    @JsonView(Views.ForPartType.class)
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<PartType> partType = partTypeRepo.findById(id);
        if (partType.isPresent()) {
            return ResponseEntity.ok().body(partType);
        }
        return ResponseEntity.badRequest().body("Part type with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @RequestBody PartType partType
    ) throws URISyntaxException {
        if (partType != null) {
            PartType created = partTypeRepo.save(partType);
            return ResponseEntity.created(new URI("/api/car-parts-types/" + created.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @RequestBody PartType partType
    ) {
        if (partType != null) {
            partTypeRepo.save(partType);
            return ResponseEntity.ok().body(partType);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(
            @PathVariable Long id
    ) {
        if (id != null) {
            partTypeRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }
}
