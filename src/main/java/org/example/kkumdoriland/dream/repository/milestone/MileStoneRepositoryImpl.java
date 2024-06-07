package org.example.kkumdoriland.dream.repository.milestone;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.dream.domain.MileStone;

import static org.example.kkumdoriland.dream.domain.QDream.dream;
import static org.example.kkumdoriland.dream.domain.QMileStone.mileStone;
import static org.example.kkumdoriland.member.domain.QMember.member;

@RequiredArgsConstructor
public class MileStoneRepositoryImpl implements MileStoneRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public MileStone findByIdWithMember(Long id) {
        return jpaQueryFactory.selectFrom(mileStone)
                .leftJoin(mileStone.dream, dream)
                .leftJoin(dream.user, member)
                .where(mileStone.id.eq(id))
                .fetchOne();
    }
}
