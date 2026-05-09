package com.dreamfit.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record AvaliacaoFisicaRequest(
        @Positive double peso,
        @Positive double altura,
        @Min(12) int idade,
        @NotBlank String sexo,
        @PositiveOrZero double pescoco,
        @PositiveOrZero double ombro,
        @PositiveOrZero double torax,
        @PositiveOrZero double cintura,
        @PositiveOrZero double quadril,
        @PositiveOrZero double abdomen,
        @PositiveOrZero double bracoDireito,
        @PositiveOrZero double bracoEsquerdo,
        @PositiveOrZero double antebracoDireito,
        @PositiveOrZero double antebracoEsquerdo,
        @PositiveOrZero double coxaDireita,
        @PositiveOrZero double coxaEsquerda,
        @PositiveOrZero double panturrilhaDireita,
        @PositiveOrZero double panturrilhaEsquerda,
        @PositiveOrZero double dobraBicipital,
        @PositiveOrZero double dobraTricipital,
        @PositiveOrZero double dobraPeitoral,
        @PositiveOrZero double dobraSubescapular,
        @PositiveOrZero double dobraAxilarMedia,
        @PositiveOrZero double dobraSuprailiaca,
        @PositiveOrZero double dobraAbdominal,
        @PositiveOrZero double dobraCoxa,
        @PositiveOrZero double dobraPanturrilha
) {
}
