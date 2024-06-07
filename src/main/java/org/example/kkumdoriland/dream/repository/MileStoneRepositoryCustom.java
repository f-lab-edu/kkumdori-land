package org.example.kkumdoriland.dream.repository;

import org.example.kkumdoriland.dream.domain.MileStone;

public interface MileStoneRepositoryCustom {
    MileStone findByIdWithMember(Long id);
}
