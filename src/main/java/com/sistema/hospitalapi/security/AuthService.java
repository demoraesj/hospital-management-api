package com.sistema.hospitalapi.security;

import com.sistema.hospitalapi.dto.auth.AutenticacaoRequestDTO;
import com.sistema.hospitalapi.dto.auth.TokenResponseDTO;
import com.sistema.hospitalapi.dto.usuario.UsuarioRequestDTO;
import com.sistema.hospitalapi.exception.RegraNegocioException;
import com.sistema.hospitalapi.model.Usuario;
import com.sistema.hospitalapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder; //criptografa senha

    private final JwtService jwtService; //gera o token jwt

    private final AuthenticationManager authenticationManager; //valida login automaticamente

    public TokenResponseDTO register(UsuarioRequestDTO request) {
        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha())) //criptografa antes do save
                .perfil(request.getPerfil())
                .build();

        usuarioRepository.save(usuario);

        String jwtToken = jwtService.generateToken(usuario); //gera token JWT do user

        return TokenResponseDTO.builder() //retorna token no DTO response
                .token(jwtToken)
                .build();
    }

    //AuthenticationManager valida email  e senha automaticamente usando
    //UserDetailsService PasswordEncoder
    public TokenResponseDTO authenticate(AutenticacaoRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );

        Usuario usuario = usuarioRepository //Busca usuário no banco após autenticação
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RegraNegocioException("Usuário não encontrado")); //se passar pelo AuthenticationManager
                                                                              //valida novamente

        String jwtToken = jwtService.generateToken(usuario); //Gera token JWT

        return TokenResponseDTO.builder() //Return token
                .token(jwtToken)
                .build();
    }

}
