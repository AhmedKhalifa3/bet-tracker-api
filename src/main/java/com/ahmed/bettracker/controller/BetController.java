package com.ahmed.bettracker.controller;

import com.ahmed.bettracker.dto.BetRequest;
import com.ahmed.bettracker.dto.StatsResponse;
import com.ahmed.bettracker.model.Bet;
import com.ahmed.bettracker.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bets")
@RequiredArgsConstructor
public class BetController {

    private final BetService service;

    @PostMapping
    public ResponseEntity<Bet> place(@Valid @RequestBody BetRequest req) {
        return ResponseEntity.status(201).body(service.placeBet(req));
    }

    @GetMapping
    public List<Bet> getAll(
            @RequestParam(required = false) String sport,
            @RequestParam(required = false) String status) {
        return service.getAll(sport, status);
    }

    @GetMapping("/{id}")
    public Bet getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}/settle")
    public Bet settle(@PathVariable Long id, @RequestParam String result) {
        return service.settle(id, result);
    }

    @GetMapping("/stats")
    public StatsResponse stats() {
        return service.getStats();
    }
}