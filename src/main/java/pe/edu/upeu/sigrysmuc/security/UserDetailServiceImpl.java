package pe.edu.upeu.sigrysmuc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.usuario.dao.UsuarioDao;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findOneByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("El usuario con username "+username+" no existe"));

        return new UserDetailsImpl(usuario);
    }
}
