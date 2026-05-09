package com.dreamfit.api.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PhysicalAssessmentResponse(
        UUID id,
        UUID userId,
        double cintura,
        double quadril,
        double torax,
        double braco,
        double coxa,
        double percentualGordura,
        LocalDate data
) {
}

