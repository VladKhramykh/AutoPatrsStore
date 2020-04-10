package com.khramykh.store.web.rest;

import com.khramykh.store.domain.car.CarMark;
import com.khramykh.store.repository.CarMarkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @PathVariable String name
    ) {
        Optional<CarMark> carMark = carMarkRepo.findById(name);
        if (carMark.isPresent()) {
            return ResponseEntity.ok().body(carMark);
        }
        return ResponseEntity.badRequest().body("Car type with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @ModelAttribute CarMark carMark
    ) throws URISyntaxException {
        if (carMark != null) {
            carMarkRepo.save(carMark);
            return ResponseEntity.created(new URI("/api/users/" + carMark.getName())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @ModelAttribute CarMark carMark
    ) {
        if (carMark != null) {
            carMarkRepo.save(carMark);
            return ResponseEntity.ok().body(carMark);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }


}
