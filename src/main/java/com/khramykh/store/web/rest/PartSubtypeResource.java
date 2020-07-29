package com.khramykh.store.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.domain.parts.PartSubtype;
import com.khramykh.store.domain.parts.PartType;
import com.khramykh.store.repository.PartSubtypeRepo;
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
@RequestMapping("/api/car-parts-subtypes")
public class PartSubtypeResource {
    @Autowired
    private PartSubtypeRepo partSubtypeRepo;

    @GetMapping
    @JsonView(Views.ForPartSubType.class)
    public ResponseEntity all() {
        return ResponseEntity.ok().body(partSubtypeRepo.findAll());
    }

    @GetMapping("{id}")
    @JsonView(Views.ForPartSubType.class)
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<PartSubtype> partSubtype = partSubtypeRepo.findById(id);
        if (partSubtype.isPresent()) {
            return ResponseEntity.ok().body(partSubtype);
        }
        return ResponseEntity.badRequest().body("Part type with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @RequestBody PartSubtype partSubtype
    ) throws URISyntaxException {
        if (partSubtype != null) {
            PartSubtype created = partSubtypeRepo.save(partSubtype);
            return ResponseEntity.created(new URI("/api/car-parts-types/" + created.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @RequestBody PartSubtype partSubtype
    ) {
        if (partSubtype != null) {
            partSubtypeRepo.save(partSubtype);
            return ResponseEntity.ok().body(partSubtype);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(
            @PathVariable Long id
    ) {
        if (id != null) {
            partSubtypeRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }
}
