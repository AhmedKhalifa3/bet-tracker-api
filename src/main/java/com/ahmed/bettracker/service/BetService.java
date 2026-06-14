package com.ahmed.bettracker.service;

import com.ahmed.bettracker.dto.BetRequest;
import com.ahmed.bettracker.dto.StatsResponse;
import com.ahmed.bettracker.model.Bet;
import com.ahmed.bettracker.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository repo;

    public Bet placeBet(BetRequest req) {
        Bet bet = new Bet();
        bet.setSport(req.sport());
        bet.setTeam(req.team());
        bet.setOdds(req.odds());
        bet.setStake(req.stake());
        return repo.save(bet);
    }

    public Page<Bet> getAll(String sport, String status, Pageable pageable) {
        if (sport != null) return repo.findBySport(sport, pageable);
        if (status != null) return repo.findByStatus(Bet.BetStatus.valueOf(status.toUpperCase()), pageable);
        return repo.findAll(pageable);
    }

    public Bet getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bet not found with id: " + id));
    }

    public Bet settle(Long id, String result) {
        Bet bet = getById(id);
        bet.setStatus(Bet.BetStatus.valueOf(result.toUpperCase()));
        return repo.save(bet);
    }

    public StatsResponse getStats() {
        List<Bet> all = repo.findAll();
        double totalStaked = all.stream().mapToDouble(Bet::getStake).sum();
        double totalProfit = all.stream().mapToDouble(Bet::profit).sum();
        long wins = all.stream().filter(b -> b.getStatus() == Bet.BetStatus.WON).count();
        long settled = all.stream().filter(b -> b.getStatus() != Bet.BetStatus.PENDING).count();
        double winRate = settled > 0 ? Math.round((double) wins / settled * 1000.0) / 10.0 : 0;
        long pending = all.stream().filter(b -> b.getStatus() == Bet.BetStatus.PENDING).count();
        return new StatsResponse(totalStaked, totalProfit, winRate, all.size(), pending);
    }
}