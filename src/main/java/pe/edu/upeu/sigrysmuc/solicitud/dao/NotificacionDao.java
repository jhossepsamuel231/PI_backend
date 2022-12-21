package pe.edu.upeu.sigrysmuc.solicitud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;

@Repository
public interface NotificacionDao extends JpaRepository<Notificacion, Integer> {
}
