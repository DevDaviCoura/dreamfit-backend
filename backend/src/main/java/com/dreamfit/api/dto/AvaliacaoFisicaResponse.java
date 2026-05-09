package com.dreamfit.api.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AvaliacaoFisicaResponse(
        UUID id,
        UUID usuarioId,
        double peso,
        double altura,
        int idade,
        String sexo,
        double pescoco,
        double ombro,
        double torax,
        double cintura,
        double quadril,
        double abdomen,
        double bracoDireito,
        double bracoEsquerdo,
        double antebracoDireito,
        double antebracoEsquerdo,
        double coxaDireita,
        double coxaEsquerda,
        double panturrilhaDireita,
        double panturrilhaEsquerda,
        double dobraBicipital,
        double dobraTricipital,
        double dobraPeitoral,
        double dobraSubescapular,
        double dobraAxilarMedia,
        double dobraSuprailiaca,
        double dobraAbdominal,
        double dobraCoxa,
        double dobraPanturrilha,
        double pesoIdealMinimo,
        double pesoIdealMaximo,
        double percentualGordura,
        double massaGorda,
        double massaMagra,
        String classificacaoCorporal,
        String recomendacaoTreinoRefinada,
        String recomendacaoAlimentarRefinada,
        String relatorioCorporal,
        LocalDate data
) {
}
