package com.dreamfit.api.service;

import com.dreamfit.api.model.User;
import org.springframework.stereotype.Service;

@Service
public class RecomendacaoService {
    public String treinoInicial(User user) {
        return resumoTreino(user, false);
    }

    public String dietaInicial(User user) {
        return resumoDieta(user, false);
    }

    public String treinoCompletoInicial(User user) {
        String experiencia = valor(user.getExperienciaComTreino(), "iniciante");
        String restricoes = valor(user.getRestricoes(), "sem restricoes informadas");
        return """
                Treino ABC inicial (%s, atividade %s)

                A - Peito, ombro e triceps
                - Supino reto: 3x10
                - Supino inclinado com halteres: 3x10
                - Desenvolvimento de ombros: 3x10
                - Elevacao lateral: 3x12
                - Triceps corda: 3x12

                B - Costas e biceps
                - Puxada frontal: 3x10
                - Remada baixa: 3x10
                - Remada unilateral: 3x12
                - Rosca direta: 3x10
                - Rosca alternada: 3x12

                C - Pernas e core
                - Agachamento livre ou guiado: 3x10
                - Leg press: 3x12
                - Cadeira extensora: 3x12
                - Mesa flexora: 3x12
                - Panturrilha em pe: 4x12
                - Prancha: 3x30-45s

                Cardio: 20 a 30 min, 2 a 4x por semana conforme objetivo.
                Ajuste por experiencia: %s.
                Restricoes consideradas: %s.
                """.formatted(normalizarObjetivo(user.getObjetivo()), user.getNivelAtividade(), experiencia, restricoes);
    }

    public String dietaCompletaInicial(User user) {
        String objetivo = normalizarObjetivo(user.getObjetivo());
        String restricoes = valor(user.getRestricoes(), "sem restricoes informadas");
        if (objetivo.equals("ganhar massa")) {
            return """
                    Dieta inicial para ganho de massa

                    Cafe da manha:
                    - 3 ovos inteiros
                    - 80 g de aveia
                    - 1 banana

                    Almoco:
                    - 300 g de arroz cozido
                    - 150 g de feijao
                    - 180 g de frango, carne magra ou peixe
                    - Salada livre com 10 ml de azeite

                    Pre-treino:
                    - 150 g de batata doce ou 2 paes franceses
                    - 120 g de frango desfiado ou 1 dose de whey

                    Jantar:
                    - 250 g de arroz ou macarrao cozido
                    - 180 g de proteina magra
                    - Legumes cozidos

                    Ceia:
                    - 170 g de iogurte natural ou 200 ml de leite
                    - 30 g de castanhas ou pasta de amendoim

                    Restricoes consideradas: %s.
                    """.formatted(restricoes);
        }
        if (objetivo.equals("manter peso")) {
            return """
                    Dieta inicial para manutencao

                    Cafe da manha:
                    - 2 ovos
                    - 50 g de aveia ou 2 fatias de pao integral
                    - 1 fruta

                    Almoco:
                    - 200 g de arroz cozido
                    - 100 g de feijao
                    - 150 g de frango, carne magra ou peixe
                    - Salada livre

                    Lanche:
                    - 1 iogurte natural
                    - 1 fruta

                    Jantar:
                    - 150 g de arroz ou 200 g de batata
                    - 150 g de proteina
                    - Legumes e folhas

                    Restricoes consideradas: %s.
                    """.formatted(restricoes);
        }
        return """
                Dieta inicial para emagrecimento

                Cafe da manha:
                - 2 ovos
                - 40 g de aveia ou 1 fatia de pao integral
                - 1 fruta

                Almoco:
                - 150 g de arroz cozido
                - 100 g de feijao
                - 160 g de frango, carne magra ou peixe
                - Salada livre e legumes

                Lanche:
                - 1 iogurte zero/natural ou 1 dose de whey
                - 1 fruta

                Jantar:
                - 120 g de arroz ou 180 g de batata
                - 160 g de proteina magra
                - Salada livre

                Ceia opcional:
                - Cha, gelatina zero ou 100 g de cottage

                Restricoes consideradas: %s.
                """.formatted(restricoes);
    }

    public String metaSemanal(User user) {
        double diferenca = Math.abs(user.getPeso() - user.getMetaPeso());
        String objetivo = normalizarObjetivo(user.getObjetivo());

        if (diferenca < 0.2 || objetivo.equals("manter peso")) {
            return "Manter variacao semanal entre -0,2 kg e +0,2 kg.";
        }
        if (objetivo.equals("ganhar massa")) {
            return "Ganhar cerca de 0,25 kg a 0,5 kg por semana.";
        }
        return "Reduzir cerca de 0,5% a 1% do peso corporal por semana.";
    }

    public String treinoRefinado(User user, double percentualGordura, String classificacaoCorporal) {
        String complemento = percentualGordura >= 28
                ? "Prioridade: preservar massa magra, aumentar gasto calorico e controlar intensidade articular."
                : "Prioridade: progressao de carga, volume bem distribuido e ajuste por desempenho.";
        return treinoCompletoInicial(user) + "\nRefinamento corporal: " + classificacaoCorporal + " (" + percentualGordura + "% de gordura). " + complemento;
    }

    public String dietaRefinada(User user, double percentualGordura, double massaMagra) {
        String proteina = "Proteina diaria sugerida: " + arredondar(massaMagra * 2.0) + " g baseada na massa magra.";
        String ajuste = percentualGordura >= 28
                ? "Ajuste: reduzir carboidratos do jantar em 30 a 50 g e manter salada livre."
                : "Ajuste: manter carboidratos no pre e pos-treino para performance.";
        return dietaCompletaInicial(user) + "\nRefinamento corporal: " + proteina + "\n" + ajuste;
    }

    private String resumoTreino(User user, boolean refinado) {
        return normalizarObjetivo(user.getObjetivo()) + ": treino ABC " + user.getNivelAtividade()
                + " com experiencia " + valor(user.getExperienciaComTreino(), "iniciante")
                + (refinado ? " refinado pela avaliacao fisica." : " baseado no cadastro.");
    }

    private String resumoDieta(User user, boolean refinado) {
        return normalizarObjetivo(user.getObjetivo()) + ": plano alimentar com porcoes definidas"
                + (refinado ? " e ajuste por composicao corporal." : " a partir do cadastro.");
    }

    private String normalizarObjetivo(String objetivo) {
        if (objetivo == null) {
            return "emagrecer";
        }
        String valor = objetivo.toLowerCase();
        if (valor.contains("ganhar")) {
            return "ganhar massa";
        }
        if (valor.contains("manter")) {
            return "manter peso";
        }
        return "emagrecer";
    }

    private String valor(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    private double arredondar(double valor) {
        return Math.round(valor * 10.0) / 10.0;
    }
}
