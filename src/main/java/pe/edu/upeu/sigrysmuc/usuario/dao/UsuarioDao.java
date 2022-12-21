package pe.edu.upeu.sigrysmuc.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.usuario.entity.Persona;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer>{

    Usuario findByUsername(String username);

    Usuario findByUsernameAndPassword(String usu, String contrasenia);

    Optional<Usuario> findOneByUsername(String username);

}
