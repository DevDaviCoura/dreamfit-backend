package com.dreamfit.api.service;

import com.dreamfit.api.dto.LoginRequest;
import com.dreamfit.api.dto.RegisterRequest;
import com.dreamfit.api.dto.UserResponse;
import com.dreamfit.api.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public UserResponse register(RegisterRequest request) {
        boolean emailAlreadyExists = users.values().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(request.email()));

        if (emailAlreadyExists) {
            throw new IllegalArgumentException("Email ja cadastrado.");
        }

        User user = new User(
                UUID.randomUUID(),
                request.nome(),
                request.email(),
                request.senha(),
                request.idade(),
                request.sexo(),
                request.peso(),
                request.altura(),
                request.metaPeso(),
                request.objetivo(),
                request.nivelAtividade(),
                request.experienciaComTreino(),
                request.restricoes() == null || request.restricoes().isBlank() ? "Sem restricoes informadas" : request.restricoes()
        );
        users.put(user.getId(), user);
        return toResponse(user);
    }

    public UserResponse login(LoginRequest request) {
        User user = users.values().stream()
                .filter(candidate -> candidate.getEmail().equalsIgnoreCase(request.email()))
                .filter(candidate -> candidate.getSenha().equals(request.senha()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha invalidos."));

        return toResponse(user);
    }

    public User findById(UUID id) {
        return Optional.ofNullable(users.get(id))
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado."));
    }

    public Collection<User> findAll() {
        return users.values();
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getIdade(),
                user.getSexo(),
                user.getPeso(),
                user.getAltura(),
                user.getMetaPeso(),
                user.getObjetivo(),
                user.getNivelAtividade(),
                user.getExperienciaComTreino(),
                user.getRestricoes()
        );
    }
}
