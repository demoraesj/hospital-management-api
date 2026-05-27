
package com.sistema.hospitalapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    /*
     * Chave secreta usada para assinar o JWT.
     *
     * O Spring pega automaticamente do application.properties
     */
    @Value("${security.jwt.chave-assinatura}")
    private String jwtSecret;


    /*
     * Tempo de expiração do token em minutos.
     *
     * Exemplo:
     * security.jwt.expiracao=30
     */
    @Value("${security.jwt.expiracao}")
    private Long jwtExpiration;


    /*
     * Extrai o username/email do token.
     *
     * No JWT:
     * subject = dono do token
     */
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }


    /*
     * Método genérico para extrair informações do token.
     *
     * Pode extrair:
     * - subject
     * - expiration
     * - issuedAt
     */
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }


    /*
     * Gera token simples.
     *
     * Sem claims extras.
     */
    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }


    /*
     * Gera token com claims extras.
     *
     * Claims extras podem ser:
     * - perfil
     * - permissões
     * - roles
     */
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {

        return buildToken(extraClaims, userDetails);
    }


    /*
     * Método principal responsável
     * por montar o JWT.
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {

        return Jwts
                .builder()

                // adiciona claims extras
                .setClaims(extraClaims)

                // define dono do token
                .setSubject(userDetails.getUsername())

                // data criação token
                .setIssuedAt(new Date(System.currentTimeMillis()))

                // expiração token
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtExpiration * 60 * 1000
                        )
                )

                // assina token com HS256
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)

                // gera token final
                .compact();
    }


    /*
     * Valida token.
     *
     * Verifica:
     * - usuário correto
     * - token expirado
     */
    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {

        final String username = extractUsername(token);

        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token)
        );
    }


    /*
     * Verifica se token expirou.
     */
    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }


    /*
     * Extrai data de expiração.
     */
    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }


    /*
     * Extrai todas as informações do token.
     *
     * Aqui o JWT é:
     * - validado
     * - lido
     * - decodificado
     */
    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()

                // chave usada para validar assinatura
                .setSigningKey(getSignInKey())

                .build()

                // faz parse do token
                .parseClaimsJws(token)

                // retorna payload do token
                .getBody();
    }


    /*
     * Converte a chave String
     * em chave criptográfica válida.
     */
    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
