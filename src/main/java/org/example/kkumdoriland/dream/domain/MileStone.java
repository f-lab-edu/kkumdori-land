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

    @ManyToOne
    @JoinColumn(name="dreamId")
    private Dream dream;
}
