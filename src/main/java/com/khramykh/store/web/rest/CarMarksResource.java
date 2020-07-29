package com.khramykh.store.web.rest;

import com.khramykh.store.domain.car.CarMark;
import com.khramykh.store.repository.CarMarkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/car-marks")
public class CarMarksResource {
    @Autowired
    private CarMarkRepo carMarkRepo;

    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok().body(carMarkRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<CarMark> carMark = carMarkRepo.findById(id);
        if (carMark.isPresent()) {
            return ResponseEntity.ok().body(carMark);
        }
        return ResponseEntity.badRequest().body("Car type with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "logo", required = false) MultipartFile logo
    ) throws URISyntaxException, IOException {
        if (name != null) {
            CarMark carMark = new CarMark();
            carMark.setName(name);
            if (logo != null)
                carMark.setLogo(logo.getBytes());
            carMarkRepo.save(carMark);
            CarMark created = carMarkRepo.findByName(carMark.getName());
            return ResponseEntity.created(new URI("/api/car-marks/" + created.getId())).body(created);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "logo", required = false) MultipartFile logo
    ) throws IOException {
        if (id != null) {
            CarMark carMark = new CarMark();
            carMark.setName(name);
            if (logo != null) {
                carMark.setLogo(logo.getBytes());
            }
            carMarkRepo.save(carMark);
            return ResponseEntity.ok(carMark);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (
            @PathVariable Long id
    ) {
        if(id != null) {
            carMarkRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Incorrect mark id");
    }
}
