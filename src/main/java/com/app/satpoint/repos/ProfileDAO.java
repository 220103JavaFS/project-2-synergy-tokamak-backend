package com.app.satpoint.repos;

import com.app.satpoint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDAO extends JpaRepository<User, Long> {
}
