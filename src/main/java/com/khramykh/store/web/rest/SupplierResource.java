package com.khramykh.store.web.rest;

import com.khramykh.store.domain.orgs.Supplier;
import com.khramykh.store.domain.parts.PartType;
import com.khramykh.store.repository.PartTypeRepo;
import com.khramykh.store.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierResource {
    @Autowired
    private SupplierRepo supplierRepo;

    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok().body(supplierRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if (supplier.isPresent()) {
            return ResponseEntity.ok().body(supplier);
        }
        return ResponseEntity.badRequest().body("Supplier with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @RequestBody Supplier supplier
    ) throws URISyntaxException {
        if (supplier != null) {
            supplierRepo.save(supplier);
            return ResponseEntity.created(new URI("/api/suppliers/" + supplier.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @RequestBody Supplier supplier
    ) {
        if (supplier != null) {
            supplierRepo.save(supplier);
            return ResponseEntity.ok().body(supplier);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (
            @PathVariable Long id
    ) {
        if(id != null) {
            supplierRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Incorrect supplier id");
    }
}
