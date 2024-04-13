package org.example.kkumdoriland.feed.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.kkumdoriland.common.BaseEntity;
import org.example.kkumdoriland.user.domain.User;

@Entity
public class Feed extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "json")
    private FeedContents contents;

    @ManyToOne
    @JoinColumn(name="authorId")
    private User user;
}
