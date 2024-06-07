package org.example.kkumdoriland.dream.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.dream.domain.Dream;

import static org.example.kkumdoriland.dream.domain.QDream.dream;

@RequiredArgsConstructor
public class DreamRepositoryImpl implements DreamRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Dream findByIdWithMember(Long id) {
        return jpaQueryFactory
                .selectFrom(dream)
                .leftJoin(dream.user).fetchJoin()
                .where(dream.id.eq(id))
                .fetchOne();
    }
}
