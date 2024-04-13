package org.example.kkumdoriland.user.repository;

import org.example.kkumdoriland.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
