package com.ahmed.bettracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
@Data
@NoArgsConstructor
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sport;
    private String team;
    private Double odds;
    private Double stake;

    @Enumerated(EnumType.STRING)
    private BetStatus status = BetStatus.PENDING;

    private LocalDateTime placedAt = LocalDateTime.now();

    public enum BetStatus { PENDING, WON, LOST }

    public double profit() {
        if (status == BetStatus.WON) return Math.round((stake * odds - stake) * 100.0) / 100.0;
        if (status == BetStatus.LOST) return -stake;
        return 0;
    }
}