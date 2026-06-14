package com.ahmed.bettracker.repository;

import com.ahmed.bettracker.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findBySport(String sport);
    List<Bet> findByStatus(Bet.BetStatus status);
}