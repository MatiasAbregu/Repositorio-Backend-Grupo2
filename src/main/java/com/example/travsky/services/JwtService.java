package com.example.travsky.services;

import com.example.travsky.models.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Servicio para la generación y validación de tokens JWT.
 */
@Service
public class JwtService {
    
    // Clave secreta para firmar los tokens JWT
    private static Key SKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
     /**
     * Genera un token JWT para el usuario dado.
     * @param user Detalles del usuario autenticado.
     * @param person Detalles de la persona asociada al usuario.
     * @return El token JWT generado.
     */
    public String getToken(UserDetails user, Person person){
        return getToken(new HashMap<>(), user, person);
    }
    
    /**
     * Genera un token JWT con reclamos personalizados para el usuario dado.
     * @param claims Reclamos adicionales para incluir en el token.
     * @param user Detalles del usuario autenticado.
     * @param person Detalles personales del usuario.
     * @return El token JWT generado.
     */
    public String getToken(Map<String, Object> claims, UserDetails user, Person person){
        claims.put("dni", person.getDni());
        claims.put("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("User"));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Obtiene el nombre de usuario del token JWT.
     * @param token El token JWT.
     * @return El nombre de usuario extraído del token.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }
   
    /**
     * Obtiene la fecha de expiración del token JWT.
     * @param token El token JWT.
     * @return La fecha de expiración del token.
     */
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica si el token JWT es válido para el usuario dado.
     * @param token El token JWT.
     * @param userDetails Detalles del usuario autenticado.
     * @return true si el token es válido, false de lo contrario.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Verifica si el token JWT ha expirado.
     * @param token El token JWT.
     * @return true si el token ha expirado, false de lo contrario.
     */
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
    
    /**
     * Obtiene todos los reclamos del token JWT.
     * @param token El token JWT.
     * @return Todos los reclamos del token.
     */
    private Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(SKey).build().parseClaimsJws(token).getBody();
    }
    
    /**
     * Obtiene un reclamo específico del token JWT.
     * @param <T> Método que maneja todo tipo de datos.
     * @param token El token JWT.
     * @param claimsResolver Función para resolver un reclamo específico.
     * @return El valor del reclamo especificado.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        Claims c = getAllClaims(token);
        return claimsResolver.apply(c);
    }

}