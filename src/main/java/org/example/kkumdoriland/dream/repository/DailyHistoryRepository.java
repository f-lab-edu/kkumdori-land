package org.example.kkumdoriland.dream.repository;

import org.example.kkumdoriland.dream.domain.DailyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyHistoryRepository extends JpaRepository<DailyHistory, Long> {

}
