package org.example.kkumdoriland.dream.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.kkumdoriland.common.domain.BaseEntity;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class MileStone extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "text", nullable=true)
    private String description;
    private boolean achieved;
    private int totalScoreToEarn;
    private int minimumStepSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dreamId")
    private Dream dream;

    public boolean isOwner(long memberId) {
        return dream.isOwner(memberId);
    }
}
