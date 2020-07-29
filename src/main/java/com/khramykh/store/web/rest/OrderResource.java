package com.khramykh.store.web.rest;

import com.khramykh.store.domain.car.CarType;
import com.khramykh.store.domain.orgs.Order;
import com.khramykh.store.repository.CarTypeRepo;
import com.khramykh.store.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok().body(orderRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(
            @PathVariable Long id
    ) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok().body(order);
        }
        return ResponseEntity.badRequest().body("Order with this id does not exist");
    }

    @PostMapping
    public ResponseEntity add(
            @Valid @RequestBody Order order
    ) throws URISyntaxException {
        if (order != null) {
            orderRepo.save(order);
            return ResponseEntity.created(new URI("/api/orders/" + order.getId())).build();
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @PutMapping
    public ResponseEntity update(
            @Valid @RequestBody Order order
    ) {
        if (order != null) {
            orderRepo.save(order);
            return ResponseEntity.ok().body(order);
        }
        return ResponseEntity.badRequest().body("Please check your entries");
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (
            @PathVariable Long id
    ) {
        if(id != null) {
            orderRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body("Incorrect order id");
    }
}
