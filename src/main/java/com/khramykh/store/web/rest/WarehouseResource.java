package com.khramykh.store.web.rest;

import com.khramykh.store.domain.orgs.Warehouse;
import com.khramykh.store.domain.parts.PartType;
import com.khramykh.store.repository.PartTypeRepo;
import com.khramykh.store.repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseResource {
    @Autowired
    private WarehouseRepo warehouseRepo;

    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok().body(warehouseRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<Warehouse> warehouse = warehouseRepo.findById(id);
        if (warehouse.isPresent()) {
            return ResponseEntity.ok().body(warehouse);
        }
        return ResponseEntity.badRequest().body("Warehouse with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @ModelAttribute Warehouse warehouse
    ) throws URISyntaxException {
        if (warehouse != null) {
            warehouseRepo.save(warehouse);
            return ResponseEntity.created(new URI("/api/warehouses/" + warehouse.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @ModelAttribute Warehouse warehouse
    ) {
        if (warehouse != null) {
            warehouseRepo.save(warehouse);
            return ResponseEntity.ok().body(warehouse);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }
}
