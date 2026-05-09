package com.dreamfit.api.controller;

import com.dreamfit.api.dto.DashboardResponse;
import com.dreamfit.api.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{usuarioId}")
    public DashboardResponse buscar(@PathVariable UUID usuarioId) {
        return dashboardService.buscar(usuarioId);
    }
}

