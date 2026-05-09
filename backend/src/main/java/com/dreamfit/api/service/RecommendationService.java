package com.dreamfit.api.service;

import org.springframework.stereotype.Service;

@Service
public class RecommendationService {
    private final RecomendacaoService recomendacaoService;

    public RecommendationService(RecomendacaoService recomendacaoService) {
        this.recomendacaoService = recomendacaoService;
    }

    public String workout(String objetivo, String nivelAtividade) {
        return switch (objetivo.toLowerCase()) {
            case "ganhar massa" -> nivelAtividade + ": musculacao 4x/semana com progressao de cargas e descanso ativo.";
            case "perder peso" -> nivelAtividade + ": treino funcional 3x/semana + cardio intervalado 2x/semana.";
            case "condicionamento" -> nivelAtividade + ": circuito full body, mobilidade e cardio continuo.";
            default -> nivelAtividade + ": musculacao leve, caminhada e manutencao 3x/semana.";
        };
    }

    public String diet(String objetivo) {
        return switch (objetivo.toLowerCase()) {
            case "ganhar massa" -> "Superavit calorico controlado, proteinas em todas as refeicoes e carboidratos no pre-treino.";
            case "perder peso" -> "Deficit calorico leve, vegetais volumosos, proteina magra e hidratacao consistente.";
            case "condicionamento" -> "Carboidratos de qualidade, boas gorduras e refeicoes equilibradas para energia.";
            default -> "Prato equilibrado com proteina, fibras, carboidratos integrais e rotina alimentar sustentavel.";
        };
    }
}
