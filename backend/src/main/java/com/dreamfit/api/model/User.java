package com.dreamfit.api.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private int idade;
    private String sexo;
    private double peso;
    private double altura;
    private double metaPeso;
    private String objetivo;
    private String nivelAtividade;
    private String experienciaComTreino;
    private String restricoes;

    public User() {
    }

    public User(UUID id, String nome, String email, String senha, int idade, String sexo, double peso, double altura,
                double metaPeso, String objetivo, String nivelAtividade, String experienciaComTreino, String restricoes) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.metaPeso = metaPeso;
        this.objetivo = objetivo;
        this.nivelAtividade = nivelAtividade;
        this.experienciaComTreino = experienciaComTreino;
        this.restricoes = restricoes;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public double getMetaPeso() { return metaPeso; }
    public void setMetaPeso(double metaPeso) { this.metaPeso = metaPeso; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public String getNivelAtividade() { return nivelAtividade; }
    public void setNivelAtividade(String nivelAtividade) { this.nivelAtividade = nivelAtividade; }
    public String getExperienciaComTreino() { return experienciaComTreino; }
    public void setExperienciaComTreino(String experienciaComTreino) { this.experienciaComTreino = experienciaComTreino; }
    public String getRestricoes() { return restricoes; }
    public void setRestricoes(String restricoes) { this.restricoes = restricoes; }
}
