package com.dreamfit.api.dto;

import java.util.UUID;

public record DashboardResponse(
        UUID usuarioId,
        String nome,
        int idade,
        String sexo,
        double altura,
        String nivelAtividade,
        String experienciaComTreino,
        String restricoes,
        double imc,
        String classificacaoImc,
        String objetivo,
        double pesoAtual,
        double metaPeso,
        String metaSemanalSugerida,
        String treinoRecomendado,
        String dietaRecomendada,
        String treinoCompleto,
        String dietaCompleta,
        String relatorioCorporal,
        AvaliacaoFisicaResponse ultimaAvaliacao
) {
}
