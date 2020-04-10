package com.khramykh.store.web.rest;

import com.khramykh.store.domain.orgs.Order;
import com.khramykh.store.domain.parts.Part;
import com.khramykh.store.repository.OrderRepo;
import com.khramykh.store.repository.PartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/car-parts")
public class PartsResource {
    @Autowired
    private PartRepo partRepo;

    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok().body(partRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<Part> part = partRepo.findById(id);
        if (part.isPresent()) {
            return ResponseEntity.ok().body(part);
        }
        return ResponseEntity.badRequest().body("Part with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @ModelAttribute Part part
    ) throws URISyntaxException {
        if (part != null) {
            partRepo.save(part);
            return ResponseEntity.created(new URI("/api/car-parts/" + part.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @ModelAttribute Part part
    ) {
        if (part != null) {
            partRepo.save(part);
            return ResponseEntity.ok().body(part);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }
}
