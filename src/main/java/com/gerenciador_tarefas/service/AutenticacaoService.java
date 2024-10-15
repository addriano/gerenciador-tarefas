package com.gerenciador_tarefas.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class AutenticacaoService {

    public static final String SIGNIN_KEY = "signinKey";
    public static final String AUTHORITIES = "authorities";
    public static final int EXPIRATION_TOKEN_ONE_HOUR = 3600000;
    private static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";

    static public void addJWTToken(HttpServletResponse response, Authentication authentication) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(AUTHORITIES, authentication.
                getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).
                collect(Collectors.toList()));

        String jwtToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN_ONE_HOUR))
                .signWith(SignatureAlgorithm.HS512, SIGNIN_KEY)
                .addClaims(claims)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, BEARER + " " + jwtToken);
        response.addHeader("Acesss-Control-Expose-Headers", HEADER_AUTHORIZATION);
    }

    static public UsernamePasswordAuthenticationToken obterAutenticacao(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);

        if (token != null) {
            Claims user = Jwts.parser()
                    .setSigningKey(SIGNIN_KEY)
                    .parseClaimsJws(token.replace(BEARER + " ", ""))
                    .getBody();

            if (user != null) {
                List<SimpleGrantedAuthority> permissoes = ((ArrayList<String>)user.get(AUTHORITIES))
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                return new UsernamePasswordAuthenticationToken(user, null, permissoes);
            } else {
                throw new RuntimeException("Autenticação falhou!");
            }
        }
        return null;
    }
}
