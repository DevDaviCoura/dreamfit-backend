package com.dreamfit.api.controller;

import com.dreamfit.api.dto.BmiRequest;
import com.dreamfit.api.dto.BmiResponse;
import com.dreamfit.api.service.BmiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imc")
public class BmiController {
    private final BmiService bmiService;

    public BmiController(BmiService bmiService) {
        this.bmiService = bmiService;
    }

    @PostMapping
    public BmiResponse calculate(@Valid @RequestBody BmiRequest request) {
        return bmiService.calculate(request.peso(), request.altura());
    }
}

