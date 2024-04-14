package org.example.kkumdoriland.user.repository;

import java.util.Optional;
import org.example.kkumdoriland.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
