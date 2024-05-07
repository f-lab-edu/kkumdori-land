package org.example.kkumdoriland.dream.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.kkumdoriland.common.domain.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class DailyHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String contentText;
    private int stepSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mileStoneId")
    private MileStone mileStone;

    public DailyHistory(String contentText, int stepSize, MileStone mileStone) {
        this.contentText = contentText;
        this.stepSize = stepSize;
        this.mileStone = mileStone;
    }
}
