package com.ahmed.bettracker.dto;

public record BetRequest(String sport, String team, Double odds, Double stake) {}