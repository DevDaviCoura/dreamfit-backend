package com.dreamfit.api.dto;

import jakarta.validation.constraints.PositiveOrZero;
import java.util.UUID;

public record PhysicalAssessmentRequest(
        UUID userId,
        @PositiveOrZero double cintura,
        @PositiveOrZero double quadril,
        @PositiveOrZero double torax,
        @PositiveOrZero double braco,
        @PositiveOrZero double coxa,
        @PositiveOrZero double percentualGordura
) {
}

