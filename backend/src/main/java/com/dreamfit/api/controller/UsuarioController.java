package com.dreamfit.api.controller;

import com.dreamfit.api.dto.DashboardResponse;
import com.dreamfit.api.dto.LoginRequest;
import com.dreamfit.api.dto.RegisterRequest;
import com.dreamfit.api.dto.UserResponse;
import com.dreamfit.api.service.DashboardService;
import com.dreamfit.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UserService userService;
    private final DashboardService dashboardService;

    public UsuarioController(UserService userService, DashboardService dashboardService) {
        this.userService = userService;
        this.dashboardService = dashboardService;
    }

    @PostMapping("/cadastrar")
    public DashboardResponse cadastrar(@Valid @RequestBody RegisterRequest request) {
        UserResponse user = userService.register(request);
        return dashboardService.buscar(user.id());
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }
}

