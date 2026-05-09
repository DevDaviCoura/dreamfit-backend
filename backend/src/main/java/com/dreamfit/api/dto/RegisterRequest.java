package com.dreamfit.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RegisterRequest(
        @NotBlank String nome,
        @Email @NotBlank String email,
        @NotBlank String senha,
        @Min(12) int idade,
        @NotBlank String sexo,
        @Positive double peso,
        @Positive double altura,
        @Positive double metaPeso,
        @NotBlank String objetivo,
        @NotBlank String nivelAtividade,
        @NotBlank String experienciaComTreino,
        String restricoes
) {
}
