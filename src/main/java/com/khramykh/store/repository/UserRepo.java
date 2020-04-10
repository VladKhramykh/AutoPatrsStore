package com.khramykh.store.repository;

import com.khramykh.store.domain.orgs.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
