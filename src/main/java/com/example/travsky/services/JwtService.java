package com.example.travsky.services;

import com.example.travsky.models.Person;
import com.example.travsky.models.User;
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
 * @author matia
 */

@Service
public class JwtService {
    
    private static Key SKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public String getToken(UserDetails user, Person person){
        return getToken(new HashMap<>(), user, person);
    }
    
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

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }
   
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
    
    private Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(SKey).build().parseClaimsJws(token).getBody();
    }
    
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        Claims c = getAllClaims(token);
        return claimsResolver.apply(c);
    }

}