package pe.edu.upeu.sigrysmuc.solicitud.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.solicitud.entity.TipoSolicitud;

@Repository
public interface TipoSolicitudDao extends JpaRepository<TipoSolicitud, Integer> {
}
