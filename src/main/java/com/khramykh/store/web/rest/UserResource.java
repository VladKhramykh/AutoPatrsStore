package com.khramykh.store.web.rest;

import com.khramykh.store.domain.orgs.User;
import com.khramykh.store.exception.BadRequestAlertException;
import com.khramykh.store.exception.UserNotFoundException;
import com.khramykh.store.repository.UserRepo;
import com.khramykh.store.service.UserService;
import com.khramykh.store.service.dto.UserRegistrationDTO;
import com.khramykh.store.service.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepo userRepo;


    private static final String URI = "http://localhost:8081/api/users";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity index() {
        return ResponseEntity.ok().body(userRepo.findAll());
    }

    @GetMapping("/{id}")
    public User getOne(
            @PathVariable Long id
    ) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent())
            return user.get();
        throw new UserNotFoundException();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
            @PathVariable Long id,
            @Valid @ModelAttribute UserProfileDTO userProfileDTO,
            @AuthenticationPrincipal User user
    ) {
        if (userProfileDTO == null) {
            throw new UserNotFoundException();
        }
        User updated = userService.updateUser(user, userProfileDTO);
        return ResponseEntity
                .ok()
                .body(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable Long id
    ) {
        if (id == 0)
            throw new BadRequestAlertException("Id can not be 0");
        User deleted = userService.deleteUser(id);
        return ResponseEntity.ok().body(deleted);
    }

    @PostMapping
    public ResponseEntity create(
            @Valid @RequestBody UserRegistrationDTO userRegistrationDTO
    ) throws URISyntaxException {
        if (userRegistrationDTO != null) {
            userService.addUser(userRegistrationDTO);
            User user = userRepo.findByEmail(userRegistrationDTO.getEmail());
            return ResponseEntity.created(new URI(URI)).body(user);
        } else
            throw new BadRequestAlertException("Bad request");
    }

}
