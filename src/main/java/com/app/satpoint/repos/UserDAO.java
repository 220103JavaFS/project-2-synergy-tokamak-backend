package com.app.satpoint.repos;

import com.app.satpoint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}
