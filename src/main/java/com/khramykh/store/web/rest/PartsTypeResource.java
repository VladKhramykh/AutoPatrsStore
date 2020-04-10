package com.khramykh.store.web.rest;

import com.khramykh.store.domain.parts.Part;
import com.khramykh.store.domain.parts.PartType;
import com.khramykh.store.repository.PartRepo;
import com.khramykh.store.repository.PartTypeRepo;
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
    public ResponseEntity all() {
        return ResponseEntity.ok().body(partTypeRepo.findAll());
    }

    @GetMapping("/{id}")
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
            @RequestBody PartType partType
    ) throws URISyntaxException {
        if (partType != null) {
            PartType created = partTypeRepo.save(partType);
            return ResponseEntity.created(new URI("/api/car-parts-type/" + created.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @ModelAttribute PartType partType
    ) {
        if (partType != null) {
            partTypeRepo.save(partType);
            return ResponseEntity.ok().body(partType);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }
}
