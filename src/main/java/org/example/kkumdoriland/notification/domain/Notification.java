package org.example.kkumdoriland.notification.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.kkumdoriland.common.domain.BaseEntity;
import org.example.kkumdoriland.member.domain.Member;

@Entity
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition= "json")
    private NotificationContents contents;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="userId")
    private Member user;
}
