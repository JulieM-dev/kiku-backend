package com.mongault.kiku.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long initialOrder;

    @Column(nullable = false)
    private String japanese;

    @Column(nullable = false)
    private String kana;

    @Column(nullable = false)
    private String romaji;

    @Column(nullable = false)
    private String translation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private FormalityLevel formalityLevel = FormalityLevel.UNSPECIFIED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CardReview> reviews = new ArrayList<>();
}