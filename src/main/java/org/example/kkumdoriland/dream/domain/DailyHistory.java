package org.example.kkumdoriland.dream.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.kkumdoriland.common.domain.BaseEntity;

@Entity
public class DailyHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String contentText;
    private int stepSize;

    @ManyToOne
    @JoinColumn(name="mileStoneId")
    private MileStone mileStone;
}
