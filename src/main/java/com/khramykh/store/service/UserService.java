package com.khramykh.store.service;

import com.khramykh.store.domain.orgs.Role;
import com.khramykh.store.domain.orgs.User;
import com.khramykh.store.exception.EmailAlreadyUsedException;
import com.khramykh.store.exception.UserNotFoundException;
import com.khramykh.store.repository.UserRepo;
import com.khramykh.store.service.dto.UserRegistrationDTO;
import com.khramykh.store.service.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

    public boolean addUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepo.findByEmail(userRegistrationDTO.getEmail()) != null)
            throw new EmailAlreadyUsedException();
        User user = userRegistrationDTOCastToUser(userRegistrationDTO);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);
        return true;
    }


    public User updateUser(User user, UserProfileDTO userProfileDTO) {
        User castedUser = userProfileDTOCastToUser(userProfileDTO);
        castedUser.setPassword(passwordEncoder.encode(userProfileDTO.getPassword()));
        String oldEmail = user.getEmail();
        String newEmail = castedUser.getEmail();
        boolean isEmailChanged = (oldEmail != null && !oldEmail.equals(newEmail)) || (newEmail != null && !newEmail.equals(oldEmail));
        if (isEmailChanged) {
            if (!StringUtils.isEmpty(newEmail)) {
                castedUser.setActivationCode(UUID.randomUUID().toString());
                // sendActivationCode;
            }
        }


        return userRepo.save(user);
    }

    public User deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.delete(user.get());
            return user.get();
        } else {
            throw new UserNotFoundException();
        }
    }



    private User userRegistrationDTOCastToUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        if (user.getId() != null && user.getId() != 0)
            user.setId(userRegistrationDTO.getId());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setAddress(userRegistrationDTO.getAddress());
        user.setTelNumber(userRegistrationDTO.getTelNumber());
        user.setEmail(userRegistrationDTO.getEmail());
        return user;
    }

    private User userProfileDTOCastToUser(UserProfileDTO userProfileDTO) {

        User user = new User();
        if (user.getId() != null && user.getId() != 0)
            user.setId(userProfileDTO.getId());
        user.setFirstName(userProfileDTO.getFirstName());
        user.setLastName(userProfileDTO.getLastName());

        user.setAddress(userProfileDTO.getAddress());
        user.setTelNumber(userProfileDTO.getTelNumber());
        user.setEmail(userProfileDTO.getEmail());
        user.setActivationCode(userProfileDTO.getActivationCode());
        return user;
    }


}
