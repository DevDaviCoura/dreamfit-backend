package com.dreamfit.api.dto;

public record BmiResponse(
        double imc,
        String classificacao
) {
}

