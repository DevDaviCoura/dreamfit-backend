package com.dreamfit.api.controller;

import com.dreamfit.api.dto.RecommendationRequest;
import com.dreamfit.api.dto.RecommendationResponse;
import com.dreamfit.api.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recomendacoes")
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/treino")
    public RecommendationResponse workout(@Valid @RequestBody RecommendationRequest request) {
        return new RecommendationResponse(recommendationService.workout(request.objetivo(), request.nivelAtividade()));
    }

    @PostMapping("/dieta")
    public RecommendationResponse diet(@Valid @RequestBody RecommendationRequest request) {
        return new RecommendationResponse(recommendationService.diet(request.objetivo()));
    }
}

