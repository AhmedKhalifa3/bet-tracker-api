package com.ahmed.bettracker.service;

import com.ahmed.bettracker.dto.StatsResponse;
import com.ahmed.bettracker.model.Bet;
import com.ahmed.bettracker.repository.BetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BetServiceTest {

    @Mock BetRepository repo;
    @InjectMocks BetService service;

    @Test
    void statsReturnsCorrectWinRate() {
        Bet won = new Bet(); won.setStake(10.0); won.setOdds(2.0); won.setStatus(Bet.BetStatus.WON);
        Bet lost = new Bet(); lost.setStake(10.0); lost.setOdds(2.0); lost.setStatus(Bet.BetStatus.LOST);
        when(repo.findAll()).thenReturn(List.of(won, lost));

        StatsResponse stats = service.getStats();

        assertEquals(50.0, stats.winRate());
        assertEquals(20.0, stats.totalStaked());
        assertEquals(0.0, stats.totalProfit());
    }

    @Test
    void pendingBetDoesNotAffectProfit() {
        Bet pending = new Bet(); pending.setStake(15.0); pending.setOdds(3.0);
        when(repo.findAll()).thenReturn(List.of(pending));

        StatsResponse stats = service.getStats();

        assertEquals(0.0, stats.totalProfit());
        assertEquals(1, stats.pendingBets());
    }
}