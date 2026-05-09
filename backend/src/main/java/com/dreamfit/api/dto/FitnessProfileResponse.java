package com.dreamfit.api.dto;

public record FitnessProfileResponse(
        UserResponse usuario,
        BmiResponse imc,
        String treinoRecomendado,
        String dietaRecomendada
) {
}

