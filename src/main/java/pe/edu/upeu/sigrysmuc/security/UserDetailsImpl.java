package pe.edu.upeu.sigrysmuc.security;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.upeu.sigrysmuc.usuario.entity.Rol;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		/* for (int i = 0; i<usuario.getRol().length; i++){
			authorities.add(new SimpleGrantedAuthority(usuario.getRol().[i].getNombre_rol()));
		} */

        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
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

    public Integer getIdUsuario() {
        return usuario.getIdUsuario();
    }

    public String getNombre() {
        return usuario.getNombre();
    }

    public Rol getRol() {
        return usuario.getRol();
    }

    public String getCorreo(){
        return usuario.getPersona().getCorreo();
    }

}
