package com.mongault.kiku.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "card_reviews",
        uniqueConstraints = @UniqueConstraint(columnNames = {"card_id", "mode"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewMode mode;

    @Column(nullable = false)
    private int quality = 0;          // 0-5

    @Column(nullable = false)
    @Builder.Default
    private double easeFactor = 2.5;

    @Column(nullable = false)
    @Builder.Default
    private int interval = 1;

    @Column(nullable = false)
    private LocalDate nextReview;

    @Column()
    private LocalDate reviewedAt;
}