package com.dreamfit.api.model;

import java.time.LocalDate;
import java.util.UUID;

public class PhysicalAssessment {
    private UUID id;
    private UUID userId;
    private double cintura;
    private double quadril;
    private double torax;
    private double braco;
    private double coxa;
    private double percentualGordura;
    private LocalDate data;

    public PhysicalAssessment() {
    }

    public PhysicalAssessment(UUID id, UUID userId, double cintura, double quadril, double torax, double braco,
                              double coxa, double percentualGordura, LocalDate data) {
        this.id = id;
        this.userId = userId;
        this.cintura = cintura;
        this.quadril = quadril;
        this.torax = torax;
        this.braco = braco;
        this.coxa = coxa;
        this.percentualGordura = percentualGordura;
        this.data = data;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public double getCintura() { return cintura; }
    public void setCintura(double cintura) { this.cintura = cintura; }
    public double getQuadril() { return quadril; }
    public void setQuadril(double quadril) { this.quadril = quadril; }
    public double getTorax() { return torax; }
    public void setTorax(double torax) { this.torax = torax; }
    public double getBraco() { return braco; }
    public void setBraco(double braco) { this.braco = braco; }
    public double getCoxa() { return coxa; }
    public void setCoxa(double coxa) { this.coxa = coxa; }
    public double getPercentualGordura() { return percentualGordura; }
    public void setPercentualGordura(double percentualGordura) { this.percentualGordura = percentualGordura; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}

