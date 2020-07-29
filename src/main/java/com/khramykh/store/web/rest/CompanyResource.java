package com.khramykh.store.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.domain.orgs.Company;
import com.khramykh.store.domain.orgs.Warehouse;
import com.khramykh.store.repository.CompanyRepo;
import com.khramykh.store.util.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyResource {

    @Autowired
    private CompanyRepo companyRepo;

    @GetMapping
    @JsonView(Views.ForCompany.class)
    public ResponseEntity all() {
        return ResponseEntity.ok().body(companyRepo.findAll());
    }

    @GetMapping("{id}")
    @JsonView(Views.ForCompany.class)
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<Company> company = companyRepo.findById(id);
        if (company.isPresent())
            return ResponseEntity.ok().body(company.get());
        return ResponseEntity.badRequest().body("Company with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @RequestBody Company company
    ) throws URISyntaxException {
        if (company != null) {
            companyRepo.save(company);
            return ResponseEntity.created(new URI("/api/companies/" + company.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @RequestBody Company company
    ) {
        if (company != null) {
            companyRepo.save(company);
            return ResponseEntity.ok().body(company);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(
            @PathVariable Long id
    ) {
        if (id != null) {
            companyRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }
}
