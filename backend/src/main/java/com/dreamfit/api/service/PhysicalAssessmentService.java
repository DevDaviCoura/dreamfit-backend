package com.dreamfit.api.service;

import com.dreamfit.api.dto.PhysicalAssessmentRequest;
import com.dreamfit.api.dto.PhysicalAssessmentResponse;
import com.dreamfit.api.model.PhysicalAssessment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PhysicalAssessmentService {
    private final UserService userService;
    private final Map<UUID, PhysicalAssessment> assessments = new ConcurrentHashMap<>();

    public PhysicalAssessmentService(UserService userService) {
        this.userService = userService;
    }

    public PhysicalAssessmentResponse create(PhysicalAssessmentRequest request) {
        userService.findById(request.userId());
        PhysicalAssessment assessment = new PhysicalAssessment(
                UUID.randomUUID(),
                request.userId(),
                request.cintura(),
                request.quadril(),
                request.torax(),
                request.braco(),
                request.coxa(),
                request.percentualGordura(),
                LocalDate.now()
        );
        assessments.put(assessment.getId(), assessment);
        return toResponse(assessment);
    }

    public List<PhysicalAssessmentResponse> listByUser(UUID userId) {
        return assessments.values().stream()
                .filter(assessment -> assessment.getUserId().equals(userId))
                .map(this::toResponse)
                .toList();
    }

    private PhysicalAssessmentResponse toResponse(PhysicalAssessment assessment) {
        return new PhysicalAssessmentResponse(
                assessment.getId(),
                assessment.getUserId(),
                assessment.getCintura(),
                assessment.getQuadril(),
                assessment.getTorax(),
                assessment.getBraco(),
                assessment.getCoxa(),
                assessment.getPercentualGordura(),
                assessment.getData()
        );
    }
}

