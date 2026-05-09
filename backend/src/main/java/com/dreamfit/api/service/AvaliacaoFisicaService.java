package com.dreamfit.api.service;

import com.dreamfit.api.dto.AvaliacaoFisicaRequest;
import com.dreamfit.api.dto.AvaliacaoFisicaResponse;
import com.dreamfit.api.model.AvaliacaoFisica;
import com.dreamfit.api.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AvaliacaoFisicaService {
    private final UserService userService;
    private final RecomendacaoService recomendacaoService;
    private final Map<UUID, AvaliacaoFisica> avaliacoes = new ConcurrentHashMap<>();

    public AvaliacaoFisicaService(UserService userService, RecomendacaoService recomendacaoService) {
        this.userService = userService;
        this.recomendacaoService = recomendacaoService;
    }

    public AvaliacaoFisicaResponse criar(UUID usuarioId, AvaliacaoFisicaRequest request) {
        User user = userService.findById(usuarioId);
        double alturaMetros = alturaEmMetros(request.altura());
        double percentualGordura = calcularPercentualGordura(request);
        double massaGorda = arredondar(request.peso() * percentualGordura / 100);
        double massaMagra = arredondar(request.peso() - massaGorda);
        double pesoIdealMinimo = calcularPesoIdealMinimo(massaMagra, alturaMetros, request.sexo());
        double pesoIdealMaximo = calcularPesoIdealMaximo(massaMagra, alturaMetros, request.sexo(), pesoIdealMinimo);
        String classificacao = classificarCorpo(percentualGordura, request.sexo());
        String relatorio = """
                Composicao corporal
                Gordura atual: %.1f%%
                Peso atual: %.1f kg
                Peso ideal estimado: %.1f kg a %.1f kg
                Massa gorda: %.1f kg
                Massa magra: %.1f kg
                Resultado: %s
                Evolucao corporal: acompanhe peso, cintura, percentual de gordura e massa magra a cada avaliacao.
                """.formatted(percentualGordura, request.peso(), pesoIdealMinimo, pesoIdealMaximo, massaGorda, massaMagra, classificacao);

        AvaliacaoFisica avaliacao = new AvaliacaoFisica();
        avaliacao.setId(UUID.randomUUID());
        avaliacao.setUsuarioId(usuarioId);
        avaliacao.setPeso(request.peso());
        avaliacao.setAltura(request.altura());
        avaliacao.setIdade(request.idade());
        avaliacao.setSexo(request.sexo());
        avaliacao.setPescoco(request.pescoco());
        avaliacao.setOmbro(request.ombro());
        avaliacao.setTorax(request.torax());
        avaliacao.setCintura(request.cintura());
        avaliacao.setQuadril(request.quadril());
        avaliacao.setAbdomen(request.abdomen());
        avaliacao.setBracoDireito(request.bracoDireito());
        avaliacao.setBracoEsquerdo(request.bracoEsquerdo());
        avaliacao.setAntebracoDireito(request.antebracoDireito());
        avaliacao.setAntebracoEsquerdo(request.antebracoEsquerdo());
        avaliacao.setCoxaDireita(request.coxaDireita());
        avaliacao.setCoxaEsquerda(request.coxaEsquerda());
        avaliacao.setPanturrilhaDireita(request.panturrilhaDireita());
        avaliacao.setPanturrilhaEsquerda(request.panturrilhaEsquerda());
        avaliacao.setDobraBicipital(request.dobraBicipital());
        avaliacao.setDobraTricipital(request.dobraTricipital());
        avaliacao.setDobraPeitoral(request.dobraPeitoral());
        avaliacao.setDobraSubescapular(request.dobraSubescapular());
        avaliacao.setDobraAxilarMedia(request.dobraAxilarMedia());
        avaliacao.setDobraSuprailiaca(request.dobraSuprailiaca());
        avaliacao.setDobraAbdominal(request.dobraAbdominal());
        avaliacao.setDobraCoxa(request.dobraCoxa());
        avaliacao.setDobraPanturrilha(request.dobraPanturrilha());
        avaliacao.setPesoIdealMinimo(pesoIdealMinimo);
        avaliacao.setPesoIdealMaximo(pesoIdealMaximo);
        avaliacao.setPercentualGordura(percentualGordura);
        avaliacao.setMassaGorda(massaGorda);
        avaliacao.setMassaMagra(massaMagra);
        avaliacao.setClassificacaoCorporal(classificacao);
        avaliacao.setRecomendacaoTreinoRefinada(recomendacaoService.treinoRefinado(user, percentualGordura, classificacao));
        avaliacao.setRecomendacaoAlimentarRefinada(recomendacaoService.dietaRefinada(user, percentualGordura, massaMagra));
        avaliacao.setRelatorioCorporal(relatorio);
        avaliacao.setData(LocalDate.now());

        avaliacoes.put(avaliacao.getId(), avaliacao);
        return toResponse(avaliacao);
    }

    public List<AvaliacaoFisicaResponse> listar(UUID usuarioId) {
        return avaliacoes.values().stream()
                .filter(avaliacao -> avaliacao.getUsuarioId().equals(usuarioId))
                .sorted(Comparator.comparing(AvaliacaoFisica::getData).reversed())
                .map(this::toResponse)
                .toList();
    }

    public Optional<AvaliacaoFisicaResponse> ultima(UUID usuarioId) {
        return avaliacoes.values().stream()
                .filter(avaliacao -> avaliacao.getUsuarioId().equals(usuarioId))
                .max(Comparator.comparing(AvaliacaoFisica::getData))
                .map(this::toResponse);
    }

    private double calcularPercentualGordura(AvaliacaoFisicaRequest request) {
        double alturaMetros = alturaEmMetros(request.altura());
        double alturaCm = alturaMetros * 100;
        boolean masculino = request.sexo().equalsIgnoreCase("masculino") || request.sexo().equalsIgnoreCase("homem");
        double somaPollock7 = request.dobraPeitoral()
                + request.dobraAbdominal()
                + request.dobraCoxa()
                + request.dobraTricipital()
                + request.dobraSubescapular()
                + request.dobraSuprailiaca()
                + request.dobraAxilarMedia();

        if (somaPollock7 > 0 && possuiPollock7(request)) {
            double densidade = masculino
                    ? 1.112 - 0.00043499 * somaPollock7 + 0.00000055 * Math.pow(somaPollock7, 2) - 0.00028826 * request.idade()
                    : 1.097 - 0.00046971 * somaPollock7 + 0.00000056 * Math.pow(somaPollock7, 2) - 0.00012828 * request.idade();
            return limitar(arredondar(495 / densidade - 450));
        }

        if (request.pescoco() > 0 && request.cintura() > 0 && alturaCm > 0) {
            if (masculino && request.cintura() > request.pescoco()) {
                double resultado = 495 / (1.0324 - 0.19077 * Math.log10(request.cintura() - request.pescoco()) + 0.15456 * Math.log10(alturaCm)) - 450;
                return limitar(arredondar(resultado));
            }
            if (!masculino && request.quadril() > 0 && request.cintura() + request.quadril() > request.pescoco()) {
                double resultado = 495 / (1.29579 - 0.35004 * Math.log10(request.cintura() + request.quadril() - request.pescoco()) + 0.22100 * Math.log10(alturaCm)) - 450;
                return limitar(arredondar(resultado));
            }
        }

        double imc = request.peso() / (alturaMetros * alturaMetros);
        double sexoBinario = masculino ? 1 : 0;
        double estimado = 1.2 * imc + 0.23 * request.idade() - 10.8 * sexoBinario - 5.4;
        return limitar(arredondar(estimado));
    }

    private String classificarCorpo(double percentual, String sexo) {
        boolean masculino = sexo.equalsIgnoreCase("masculino") || sexo.equalsIgnoreCase("homem");
        if (masculino) {
            if (percentual < 14) return "Atletico";
            if (percentual < 21) return "Saudavel";
            if (percentual < 28) return "Atencao";
            return "Elevado";
        }
        if (percentual < 21) return "Atletico";
        if (percentual < 28) return "Saudavel";
        if (percentual < 35) return "Atencao";
        return "Elevado";
    }

    private AvaliacaoFisicaResponse toResponse(AvaliacaoFisica avaliacao) {
        return new AvaliacaoFisicaResponse(
                avaliacao.getId(),
                avaliacao.getUsuarioId(),
                avaliacao.getPeso(),
                avaliacao.getAltura(),
                avaliacao.getIdade(),
                avaliacao.getSexo(),
                avaliacao.getPescoco(),
                avaliacao.getOmbro(),
                avaliacao.getTorax(),
                avaliacao.getCintura(),
                avaliacao.getQuadril(),
                avaliacao.getAbdomen(),
                avaliacao.getBracoDireito(),
                avaliacao.getBracoEsquerdo(),
                avaliacao.getAntebracoDireito(),
                avaliacao.getAntebracoEsquerdo(),
                avaliacao.getCoxaDireita(),
                avaliacao.getCoxaEsquerda(),
                avaliacao.getPanturrilhaDireita(),
                avaliacao.getPanturrilhaEsquerda(),
                avaliacao.getDobraBicipital(),
                avaliacao.getDobraTricipital(),
                avaliacao.getDobraPeitoral(),
                avaliacao.getDobraSubescapular(),
                avaliacao.getDobraAxilarMedia(),
                avaliacao.getDobraSuprailiaca(),
                avaliacao.getDobraAbdominal(),
                avaliacao.getDobraCoxa(),
                avaliacao.getDobraPanturrilha(),
                avaliacao.getPesoIdealMinimo(),
                avaliacao.getPesoIdealMaximo(),
                avaliacao.getPercentualGordura(),
                avaliacao.getMassaGorda(),
                avaliacao.getMassaMagra(),
                avaliacao.getClassificacaoCorporal(),
                avaliacao.getRecomendacaoTreinoRefinada(),
                avaliacao.getRecomendacaoAlimentarRefinada(),
                avaliacao.getRelatorioCorporal(),
                avaliacao.getData()
        );
    }

    private double limitar(double valor) {
        return Math.max(3, Math.min(65, valor));
    }

    private boolean possuiPollock7(AvaliacaoFisicaRequest request) {
        return request.dobraPeitoral() > 0
                && request.dobraAbdominal() > 0
                && request.dobraCoxa() > 0
                && request.dobraTricipital() > 0
                && request.dobraSubescapular() > 0
                && request.dobraSuprailiaca() > 0
                && request.dobraAxilarMedia() > 0;
    }

    private double alturaEmMetros(double altura) {
        return altura > 3 ? altura / 100 : altura;
    }

    private double calcularPesoIdealMinimo(double massaMagra, double alturaMetros, String sexo) {
        boolean masculino = sexo.equalsIgnoreCase("masculino") || sexo.equalsIgnoreCase("homem");
        double pesoPorComposicao = masculino ? massaMagra / 0.88 : massaMagra / 0.78;
        double pisoPorAltura = alturaMetros * alturaMetros * (masculino ? 22.0 : 20.5);
        return arredondar(Math.max(pesoPorComposicao, pisoPorAltura));
    }

    private double calcularPesoIdealMaximo(double massaMagra, double alturaMetros, String sexo, double minimo) {
        boolean masculino = sexo.equalsIgnoreCase("masculino") || sexo.equalsIgnoreCase("homem");
        double pesoPorComposicao = masculino ? massaMagra / 0.84 : massaMagra / 0.72;
        double tetoPorAltura = alturaMetros * alturaMetros * (masculino ? 27.0 : 25.0);
        return arredondar(Math.max(minimo, Math.min(pesoPorComposicao, tetoPorAltura)));
    }

    private double arredondar(double valor) {
        return Math.round(valor * 10.0) / 10.0;
    }
}
