package com.sistema.hospitalapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    /*
     * Serviço JWT responsável por:
     * extrair username do token
     * validar token
     */
    private final JwtService jwtService;

    /*
     * Serviço do Spring Security que:
     * busca usuário no banco
     * carrega UserDetails
     */
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        //Pega header Authorization

        final String authHeader = request.getHeader("Authorization");

        final String jwtToken;

        final String userEmail;

        /*
         * Verifica:
         * se header existe
         * se começa com Bearer
         *
         * Se não existir:
         * continua requisição normalmente
         */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);

            return;
        }

        /*
         * Remove "Bearer "
         *
         * Exemplo:
         * Bearer abc123
         *
         * Resultado:
         * abc123
         */
        jwtToken = authHeader.substring(7);


        //Extrai email/username do token.
        userEmail = jwtService.extractUsername(jwtToken);

        /*
         * Verifica:
         * se usuário existe no token
         * se ainda NÃO existe autenticação no contexto
         */
        if (
                userEmail != null &&
                        SecurityContextHolder.getContext().getAuthentication() == null
        ) {
            /*
             * Busca usuário no banco
             */
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(userEmail);

            /*
             * Valida token
             */
            if (jwtService.isTokenValid(jwtToken, userDetails)) {

                /*
                 * Cria autenticação do Spring Security
                 */
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                /*
                 * Adiciona detalhes da requisição
                 */
                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                /*
                 * Aqui o usuário é autenticado
                 * no contexto do Spring Security
                 */
                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
            }
        }

        /*
         * Continua fluxo da requisição
         */
        filterChain.doFilter(request, response);
    }
}
