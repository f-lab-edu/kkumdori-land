package org.example.kkumdoriland.dream.repository;

import org.example.kkumdoriland.dream.domain.Dream;

public interface DreamRepositoryCustom {
    Dream findByIdWithMember(Long id);
}
