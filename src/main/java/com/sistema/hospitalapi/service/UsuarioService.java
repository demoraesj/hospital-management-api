package com.sistema.hospitalapi.service;

import com.sistema.hospitalapi.dto.usuario.UsuarioRequestDTO;
import com.sistema.hospitalapi.dto.usuario.UsuarioResponseDTO;
import com.sistema.hospitalapi.exception.RegraNegocioException;
import com.sistema.hospitalapi.model.Usuario;
import com.sistema.hospitalapi.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfil(dto.getPerfil());

        return usuario;
    }

    private UsuarioResponseDTO toDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil())
                .build();
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RegraNegocioException("Email já cadastrado");
        }
        Usuario usuario = toEntity(dto);
        Usuario salvo = usuarioRepository.save(usuario);

        return toDTO(salvo);
    }
}
