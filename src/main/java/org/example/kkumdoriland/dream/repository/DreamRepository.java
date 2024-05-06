package org.example.kkumdoriland.dream.repository;

import java.util.Optional;
import org.example.kkumdoriland.dream.domain.Dream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DreamRepository extends JpaRepository<Dream, Long> {
    Optional<Dream> findDreamByTitle(String title);
}
