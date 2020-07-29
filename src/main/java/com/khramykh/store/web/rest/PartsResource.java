package com.khramykh.store.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.khramykh.store.domain.parts.Part;
import com.khramykh.store.repository.PartRepo;
import com.khramykh.store.service.dto.SearchFormDto;
import com.khramykh.store.util.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car-parts")
public class PartsResource {
    @Autowired
    private PartRepo partRepo;

    @GetMapping
    @JsonView(Views.ForPart.class)
    public ResponseEntity all(
            @RequestParam(name = "partSubtype", required = false) Long id
    ) {
        List<Part> parts;
        if (id != null) {
            parts = partRepo.findAll()
                    .stream()
                    .filter(part -> part.getPartSubtype().getId().equals(id))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(parts);
        } else {
            parts = partRepo.findAll();
        }
        return ResponseEntity.ok().body(parts);
    }

    @GetMapping("{id}")
    @JsonView(Views.ForPart.class)
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<Part> part = partRepo.findById(id);
        if (part.isPresent()) {
            return ResponseEntity.ok().body(part);
        }
        return ResponseEntity.badRequest().body("Part with this id does not exist");
    }

    @GetMapping("count")
    public ResponseEntity count() {
        return ResponseEntity.ok(partRepo.getCount());
    }

    @JsonView(Views.ForPart.class)
    @PostMapping("search")
    public ResponseEntity search(
            @RequestBody SearchFormDto searchFormDto
    ) {
        List<Part> parts = partRepo.searchPartsBySearchForm(
            searchFormDto.getName(),
                searchFormDto.getPartTypeId(),
                searchFormDto.getCarId()
        );

        return ResponseEntity.ok(parts);
    }


    @PostMapping
    public ResponseEntity add(
            @Valid @RequestBody Part part
    ) throws URISyntaxException {
        if (part != null) {
            partRepo.save(part);

            return ResponseEntity.created(new URI("/api/car-parts/" + part.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @RequestBody Part part
    ) {
        if (part != null) {
            partRepo.save(part);
            return ResponseEntity.ok().body(part);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(
            @PathVariable Long id
    ) {
        if (id != null) {
            partRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Incorrect part id");
    }
}
