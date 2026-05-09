package com.dreamfit.api.dto;

import jakarta.validation.constraints.Positive;

public record BmiRequest(
        @Positive double peso,
        @Positive double altura
) {
}

