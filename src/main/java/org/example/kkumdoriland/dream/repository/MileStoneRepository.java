package org.example.kkumdoriland.dream.repository;

import org.example.kkumdoriland.dream.domain.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileStoneRepository extends JpaRepository<MileStone, Long>, MileStoneRepositoryCustom {

}
