package com.ahmed.bettracker.dto;

import jakarta.validation.constraints.*;

public record BetRequest(
    @NotBlank(message = "Sport is required")
    String sport,

    @NotBlank(message = "Team is required")
    String team,

    @NotNull(message = "Odds are required")
    @DecimalMin(value = "1.01", message = "Odds must be greater than 1.0")
    Double odds,

    @NotNull(message = "Stake is required")
    @DecimalMin(value = "0.01", message = "Stake must be positive")
    Double stake
) {}