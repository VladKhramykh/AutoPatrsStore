package com.khramykh.store.web.rest;

import com.khramykh.store.domain.orgs.Company;
import com.khramykh.store.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyResource {

    @Autowired
    private CompanyRepo companyRepo;

    @GetMapping("/{id}")
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<Company> company = companyRepo.findById(id);
        if (company.isPresent())
            return ResponseEntity.ok().body(company.get());
        return ResponseEntity.badRequest().body("Company with this id does not exist");
    }
}
