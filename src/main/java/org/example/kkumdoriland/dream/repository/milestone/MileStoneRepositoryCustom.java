package org.example.kkumdoriland.dream.repository.milestone;

import org.example.kkumdoriland.dream.domain.MileStone;

public interface MileStoneRepositoryCustom {
    MileStone findByIdWithMember(Long id);
}
