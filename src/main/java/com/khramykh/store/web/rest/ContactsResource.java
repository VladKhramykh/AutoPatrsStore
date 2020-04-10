package com.khramykh.store.web.rest;


import com.khramykh.store.domain.car.CarMark;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactsResource {

    static List<CarMark> carMarks = new LinkedList<>();

    static {
        carMarks.add(new CarMark("Renault", null));
        carMarks.add(new CarMark("Audi", null));
        carMarks.add(new CarMark("Bmw", null));
    }

    @GetMapping
    public List<CarMark> about() {
        return carMarks;
    }

    @PostMapping
    @PreAuthorize("ADMIN")
    public CarMark add(
            @RequestBody CarMark carMark
    ) {
        carMarks.add(carMark);
        return carMark;
    }
}
