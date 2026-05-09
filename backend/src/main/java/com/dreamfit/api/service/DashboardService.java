package com.dreamfit.api.service;

import com.dreamfit.api.dto.AvaliacaoFisicaResponse;
import com.dreamfit.api.dto.BmiResponse;
import com.dreamfit.api.dto.DashboardResponse;
import com.dreamfit.api.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DashboardService {
    private final UserService userService;
    private final ImcService imcService;
    private final RecomendacaoService recomendacaoService;
    private final AvaliacaoFisicaService avaliacaoFisicaService;

    public DashboardService(UserService userService, ImcService imcService, RecomendacaoService recomendacaoService,
                            AvaliacaoFisicaService avaliacaoFisicaService) {
        this.userService = userService;
        this.imcService = imcService;
        this.recomendacaoService = recomendacaoService;
        this.avaliacaoFisicaService = avaliacaoFisicaService;
    }

    public DashboardResponse buscar(UUID usuarioId) {
        User user = userService.findById(usuarioId);
        BmiResponse imc = imcService.calcular(user.getPeso(), user.getAltura());
        AvaliacaoFisicaResponse ultimaAvaliacao = avaliacaoFisicaService.ultima(usuarioId).orElse(null);

        String treino = ultimaAvaliacao != null
                ? ultimaAvaliacao.recomendacaoTreinoRefinada()
                : recomendacaoService.treinoInicial(user);
        String dieta = ultimaAvaliacao != null
                ? ultimaAvaliacao.recomendacaoAlimentarRefinada()
                : recomendacaoService.dietaInicial(user);

        return new DashboardResponse(
                user.getId(),
                user.getNome(),
                user.getIdade(),
                user.getSexo(),
                user.getAltura(),
                user.getNivelAtividade(),
                user.getExperienciaComTreino(),
                user.getRestricoes(),
                imc.imc(),
                imc.classificacao(),
                user.getObjetivo(),
                user.getPeso(),
                user.getMetaPeso(),
                recomendacaoService.metaSemanal(user),
                treino,
                dieta,
                ultimaAvaliacao != null
                        ? ultimaAvaliacao.recomendacaoTreinoRefinada()
                        : recomendacaoService.treinoCompletoInicial(user),
                ultimaAvaliacao != null
                        ? ultimaAvaliacao.recomendacaoAlimentarRefinada()
                        : recomendacaoService.dietaCompletaInicial(user),
                ultimaAvaliacao != null
                        ? ultimaAvaliacao.relatorioCorporal()
                        : "Sem avaliacao fisica completa. O sistema usa cadastro, peso, altura, idade, sexo, objetivo, meta, atividade, experiencia e restricoes para montar o plano inicial.",
                ultimaAvaliacao
        );
    }
}
