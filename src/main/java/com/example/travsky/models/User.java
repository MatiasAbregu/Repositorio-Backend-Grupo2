package com.example.travsky.models;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Entidad que representa a un usuario.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 * Implementa métodos de UserDetails.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails{ 
    
    // Identificador único autoincrementable del usuario.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "u_code")
    private int code;
    
    // Nombre de usuario único.
    @Column(name = "username")
    private String username;
    
    // Contraseña asociada al usuario.
    @Column(name = "password")
    private String password;
    
    // Email asociado al usuario.
    @Column(name = "email")
    private String email;
    
    // Teléfono asociado al usuario.
    @Column(name = "cellphone")
    private String cellphone;

    // Rol asociado al usuario.
    @Column(name = "role")
    private String role;
    
    // Método sobrescrito para obtener roles del usuario.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}