package com.ahmed.bettracker.repository;

import com.ahmed.bettracker.model.Bet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    Page<Bet> findBySport(String sport, Pageable pageable);
    Page<Bet> findByStatus(Bet.BetStatus status, Pageable pageable);
}