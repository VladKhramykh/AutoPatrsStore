package com.khramykh.store.web.rest;

import com.khramykh.store.domain.car.CarType;
import com.khramykh.store.repository.CarTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/car-types")
public class CarTypeResource {

    private static final String URI = "http://localhost:8081/api/car-types";

    @Autowired
    private CarTypeRepo carTypeRepo;

    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok().body(carTypeRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<CarType> carMark = carTypeRepo.findById(id);
        if (carMark.isPresent()) {
            return ResponseEntity.ok().body(carMark);
        }
        return ResponseEntity.badRequest().body("Car type with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @RequestBody CarType carType
    ) throws URISyntaxException {
        if (carType != null) {
            carTypeRepo.save(carType);
            return ResponseEntity.created(new URI(URI)).body(carType);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping("{id}")
    public ResponseEntity update(
            @PathVariable Long id,
            @Valid @RequestBody CarType carType
    ) {
        if (carType != null) {
            carType.setId(id);
            carTypeRepo.save(carType);
            return ResponseEntity.ok().body(carType);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(
            @PathVariable Long id
    ) {
        if (id != null) {
            carTypeRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }
}
