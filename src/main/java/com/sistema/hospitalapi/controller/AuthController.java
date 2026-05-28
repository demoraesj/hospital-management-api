package com.sistema.hospitalapi.controller;

import com.sistema.hospitalapi.dto.auth.AutenticacaoRequestDTO;
import com.sistema.hospitalapi.dto.auth.TokenResponseDTO;
import com.sistema.hospitalapi.dto.usuario.UsuarioRequestDTO;
import com.sistema.hospitalapi.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(
            @RequestBody @Valid UsuarioRequestDTO request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponseDTO> authenticate(
            @RequestBody @Valid AutenticacaoRequestDTO request
    ) {
        return ResponseEntity.ok(
                authService.authenticate(request));
    }


}
