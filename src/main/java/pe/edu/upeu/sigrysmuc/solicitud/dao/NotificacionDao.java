package pe.edu.upeu.sigrysmuc.solicitud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;

import java.util.List;

@Repository
public interface NotificacionDao extends JpaRepository<Notificacion, Integer> {

    @Query(value = "SELECT * FROM solicitud AS soli INNER JOIN usuarios AS usu ON soli.id_usuario=usu.id_usuario INNER JOIN notificacion AS notifi ON notifi.id_solicitud=soli.id_solicitud WHERE soli.id_usuario=?1", nativeQuery = true)
    List<Notificacion> listadoDeNotificacionPorUsuario(int idUsuario);
}
