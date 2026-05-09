package com.dreamfit.api.service;

import com.dreamfit.api.dto.BmiResponse;
import org.springframework.stereotype.Service;

@Service
public class ImcService {
    public BmiResponse calcular(double peso, double altura) {
        double imc = peso / (altura * altura);
        double arredondado = arredondar(imc);
        return new BmiResponse(arredondado, classificar(arredondado));
    }

    public String classificar(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        }
        if (imc < 25) {
            return "Peso saudavel";
        }
        if (imc < 30) {
            return "Sobrepeso";
        }
        return "Obesidade";
    }

    public double arredondar(double valor) {
        return Math.round(valor * 10.0) / 10.0;
    }
}

