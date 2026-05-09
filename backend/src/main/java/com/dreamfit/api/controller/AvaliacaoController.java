package com.dreamfit.api.controller;

import com.dreamfit.api.dto.AvaliacaoFisicaRequest;
import com.dreamfit.api.dto.AvaliacaoFisicaResponse;
import com.dreamfit.api.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {
    private final AvaliacaoFisicaService avaliacaoFisicaService;

    public AvaliacaoController(AvaliacaoFisicaService avaliacaoFisicaService) {
        this.avaliacaoFisicaService = avaliacaoFisicaService;
    }

    @PostMapping("/{usuarioId}")
    public AvaliacaoFisicaResponse criar(@PathVariable UUID usuarioId, @Valid @RequestBody AvaliacaoFisicaRequest request) {
        return avaliacaoFisicaService.criar(usuarioId, request);
    }

    @GetMapping("/{usuarioId}")
    public List<AvaliacaoFisicaResponse> listar(@PathVariable UUID usuarioId) {
        return avaliacaoFisicaService.listar(usuarioId);
    }
}
