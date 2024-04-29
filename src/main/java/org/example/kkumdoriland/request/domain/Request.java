package org.example.kkumdoriland.request.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EnumType;
import org.example.kkumdoriland.common.domain.BaseEntity;
import org.example.kkumdoriland.member.domain.Member;

@Entity
public class Request extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="requesterId")
    private Member requester;

    @ManyToOne
    @JoinColumn(name="receiverId")
    private Member receiver;

    private boolean requesterLiked = true;
    private boolean receiverLiked = false;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;
}
