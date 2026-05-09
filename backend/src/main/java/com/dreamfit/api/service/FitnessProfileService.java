package com.dreamfit.api.service;

import com.dreamfit.api.dto.BmiResponse;
import com.dreamfit.api.dto.FitnessProfileResponse;
import com.dreamfit.api.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FitnessProfileService {
    private final UserService userService;
    private final BmiService bmiService;
    private final RecommendationService recommendationService;

    public FitnessProfileService(UserService userService, BmiService bmiService, RecommendationService recommendationService) {
        this.userService = userService;
        this.bmiService = bmiService;
        this.recommendationService = recommendationService;
    }

    public FitnessProfileResponse profile(UUID userId) {
        User user = userService.findById(userId);
        BmiResponse bmi = bmiService.calculate(user.getPeso(), user.getAltura());
        return new FitnessProfileResponse(
                userService.toResponse(user),
                bmi,
                recommendationService.workout(user.getObjetivo(), user.getNivelAtividade()),
                recommendationService.diet(user.getObjetivo())
        );
    }
}

