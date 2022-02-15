package com.app.satpoint.repos;
import com.app.satpoint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogonDao extends JpaRepository<User,Long> {
    public Optional<User> findByUsername(String username);
}
