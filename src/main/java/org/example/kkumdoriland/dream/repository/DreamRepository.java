package org.example.kkumdoriland.dream.repository;

import org.example.kkumdoriland.dream.domain.Dream;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DreamRepository extends JpaRepository<Dream, Long>, DreamRepositoryCustom {
    Optional<Dream> findDreamByTitle(String title);

    List<Dream> findDreamsByUserId(Long userId);
}
