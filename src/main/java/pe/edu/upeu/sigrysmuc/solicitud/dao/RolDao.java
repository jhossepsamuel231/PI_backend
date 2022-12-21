package pe.edu.upeu.sigrysmuc.solicitud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.usuario.entity.Rol;

@Repository
public interface RolDao extends JpaRepository<Rol, Integer> {
}
