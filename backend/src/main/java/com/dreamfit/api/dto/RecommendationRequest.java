package com.dreamfit.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RecommendationRequest(
        @NotBlank String objetivo,
        @NotBlank String nivelAtividade
) {
}

