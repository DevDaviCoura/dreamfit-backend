package com.dreamfit.api.model;

import java.time.LocalDate;
import java.util.UUID;

public class AvaliacaoFisica {
    private UUID id;
    private UUID usuarioId;
    private double peso;
    private double altura;
    private int idade;
    private String sexo;
    private double pescoco;
    private double ombro;
    private double torax;
    private double cintura;
    private double quadril;
    private double abdomen;
    private double bracoDireito;
    private double bracoEsquerdo;
    private double antebracoDireito;
    private double antebracoEsquerdo;
    private double coxaDireita;
    private double coxaEsquerda;
    private double panturrilhaDireita;
    private double panturrilhaEsquerda;
    private double dobraBicipital;
    private double dobraTricipital;
    private double dobraPeitoral;
    private double dobraSubescapular;
    private double dobraAxilarMedia;
    private double dobraSuprailiaca;
    private double dobraAbdominal;
    private double dobraCoxa;
    private double dobraPanturrilha;
    private double pesoIdealMinimo;
    private double pesoIdealMaximo;
    private double percentualGordura;
    private double massaGorda;
    private double massaMagra;
    private String classificacaoCorporal;
    private String recomendacaoTreinoRefinada;
    private String recomendacaoAlimentarRefinada;
    private String relatorioCorporal;
    private LocalDate data;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public double getPescoco() { return pescoco; }
    public void setPescoco(double pescoco) { this.pescoco = pescoco; }
    public double getOmbro() { return ombro; }
    public void setOmbro(double ombro) { this.ombro = ombro; }
    public double getTorax() { return torax; }
    public void setTorax(double torax) { this.torax = torax; }
    public double getCintura() { return cintura; }
    public void setCintura(double cintura) { this.cintura = cintura; }
    public double getQuadril() { return quadril; }
    public void setQuadril(double quadril) { this.quadril = quadril; }
    public double getAbdomen() { return abdomen; }
    public void setAbdomen(double abdomen) { this.abdomen = abdomen; }
    public double getBracoDireito() { return bracoDireito; }
    public void setBracoDireito(double bracoDireito) { this.bracoDireito = bracoDireito; }
    public double getBracoEsquerdo() { return bracoEsquerdo; }
    public void setBracoEsquerdo(double bracoEsquerdo) { this.bracoEsquerdo = bracoEsquerdo; }
    public double getAntebracoDireito() { return antebracoDireito; }
    public void setAntebracoDireito(double antebracoDireito) { this.antebracoDireito = antebracoDireito; }
    public double getAntebracoEsquerdo() { return antebracoEsquerdo; }
    public void setAntebracoEsquerdo(double antebracoEsquerdo) { this.antebracoEsquerdo = antebracoEsquerdo; }
    public double getCoxaDireita() { return coxaDireita; }
    public void setCoxaDireita(double coxaDireita) { this.coxaDireita = coxaDireita; }
    public double getCoxaEsquerda() { return coxaEsquerda; }
    public void setCoxaEsquerda(double coxaEsquerda) { this.coxaEsquerda = coxaEsquerda; }
    public double getPanturrilhaDireita() { return panturrilhaDireita; }
    public void setPanturrilhaDireita(double panturrilhaDireita) { this.panturrilhaDireita = panturrilhaDireita; }
    public double getPanturrilhaEsquerda() { return panturrilhaEsquerda; }
    public void setPanturrilhaEsquerda(double panturrilhaEsquerda) { this.panturrilhaEsquerda = panturrilhaEsquerda; }
    public double getDobraBicipital() { return dobraBicipital; }
    public void setDobraBicipital(double dobraBicipital) { this.dobraBicipital = dobraBicipital; }
    public double getDobraTricipital() { return dobraTricipital; }
    public void setDobraTricipital(double dobraTricipital) { this.dobraTricipital = dobraTricipital; }
    public double getDobraPeitoral() { return dobraPeitoral; }
    public void setDobraPeitoral(double dobraPeitoral) { this.dobraPeitoral = dobraPeitoral; }
    public double getDobraSubescapular() { return dobraSubescapular; }
    public void setDobraSubescapular(double dobraSubescapular) { this.dobraSubescapular = dobraSubescapular; }
    public double getDobraAxilarMedia() { return dobraAxilarMedia; }
    public void setDobraAxilarMedia(double dobraAxilarMedia) { this.dobraAxilarMedia = dobraAxilarMedia; }
    public double getDobraSuprailiaca() { return dobraSuprailiaca; }
    public void setDobraSuprailiaca(double dobraSuprailiaca) { this.dobraSuprailiaca = dobraSuprailiaca; }
    public double getDobraAbdominal() { return dobraAbdominal; }
    public void setDobraAbdominal(double dobraAbdominal) { this.dobraAbdominal = dobraAbdominal; }
    public double getDobraCoxa() { return dobraCoxa; }
    public void setDobraCoxa(double dobraCoxa) { this.dobraCoxa = dobraCoxa; }
    public double getDobraPanturrilha() { return dobraPanturrilha; }
    public void setDobraPanturrilha(double dobraPanturrilha) { this.dobraPanturrilha = dobraPanturrilha; }
    public double getPesoIdealMinimo() { return pesoIdealMinimo; }
    public void setPesoIdealMinimo(double pesoIdealMinimo) { this.pesoIdealMinimo = pesoIdealMinimo; }
    public double getPesoIdealMaximo() { return pesoIdealMaximo; }
    public void setPesoIdealMaximo(double pesoIdealMaximo) { this.pesoIdealMaximo = pesoIdealMaximo; }
    public double getPercentualGordura() { return percentualGordura; }
    public void setPercentualGordura(double percentualGordura) { this.percentualGordura = percentualGordura; }
    public double getMassaGorda() { return massaGorda; }
    public void setMassaGorda(double massaGorda) { this.massaGorda = massaGorda; }
    public double getMassaMagra() { return massaMagra; }
    public void setMassaMagra(double massaMagra) { this.massaMagra = massaMagra; }
    public String getClassificacaoCorporal() { return classificacaoCorporal; }
    public void setClassificacaoCorporal(String classificacaoCorporal) { this.classificacaoCorporal = classificacaoCorporal; }
    public String getRecomendacaoTreinoRefinada() { return recomendacaoTreinoRefinada; }
    public void setRecomendacaoTreinoRefinada(String recomendacaoTreinoRefinada) { this.recomendacaoTreinoRefinada = recomendacaoTreinoRefinada; }
    public String getRecomendacaoAlimentarRefinada() { return recomendacaoAlimentarRefinada; }
    public void setRecomendacaoAlimentarRefinada(String recomendacaoAlimentarRefinada) { this.recomendacaoAlimentarRefinada = recomendacaoAlimentarRefinada; }
    public String getRelatorioCorporal() { return relatorioCorporal; }
    public void setRelatorioCorporal(String relatorioCorporal) { this.relatorioCorporal = relatorioCorporal; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}
