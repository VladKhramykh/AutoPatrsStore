package com.khramykh.store.web.rest;

import com.khramykh.store.domain.car.Car;
import com.khramykh.store.exception.BadRequestAlertException;
import com.khramykh.store.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarResource {
    private static final String URI = "http://localhost:8081/api/cars";

    @Autowired
    private CarRepo carRepo;

    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok().body(carRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<Car> car = carRepo.findById(id);
        if (car.isPresent())
            return ResponseEntity.ok().body(car.get());
        return ResponseEntity.badRequest().body(new BadRequestAlertException("Car with this id does not exist"));
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @RequestBody Car car
    ) throws URISyntaxException {
        if (car != null) {
            carRepo.save(car);
            return ResponseEntity.created(new URI(URI)).body(car);
        }
        return ResponseEntity.badRequest().body(new BadRequestAlertException("Bad request"));

    }

    @PutMapping
    public ResponseEntity update(
            @Valid @RequestBody Car car
    ) throws URISyntaxException {
        if (car != null) {
            if(car.getId() != null)
                carRepo.save(car);
            return ResponseEntity.created(new URI(URI)).body(car);
        }
        return ResponseEntity.badRequest().body(new BadRequestAlertException("Bad request"));

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (
            @PathVariable Long id
    ) {
        if(id != null) {
            carRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Incorrect car id");
    }
}
