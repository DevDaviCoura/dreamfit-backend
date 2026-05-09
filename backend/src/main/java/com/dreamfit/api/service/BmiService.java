package com.dreamfit.api.service;

import com.dreamfit.api.dto.BmiResponse;
import org.springframework.stereotype.Service;

@Service
public class BmiService {
    private final ImcService imcService;

    public BmiService(ImcService imcService) {
        this.imcService = imcService;
    }

    public BmiResponse calculate(double peso, double altura) {
        return imcService.calcular(peso, altura);
    }

    private String classify(double imc) {
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
}
