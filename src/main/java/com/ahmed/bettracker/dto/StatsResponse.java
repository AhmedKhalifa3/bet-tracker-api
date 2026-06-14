package com.ahmed.bettracker.dto;

public record StatsResponse(
    double totalStaked,
    double totalProfit,
    double winRate,
    int totalBets,
    long pendingBets
) {}