package org.example.kkumdoriland.dream;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import org.example.kkumdoriland.common.domain.BaseEntity;
import org.example.kkumdoriland.member.domain.Member;

@Entity
public class Dream extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name="ownerId")
    private Member user;
}
