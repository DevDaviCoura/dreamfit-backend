package com.dreamfit.api.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String nome,
        String email,
        int idade,
        String sexo,
        double peso,
        double altura,
        double metaPeso,
        String objetivo,
        String nivelAtividade,
        String experienciaComTreino,
        String restricoes
) {
}
