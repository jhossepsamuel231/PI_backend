package pe.edu.upeu.sigrysmuc.solicitud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;

import java.util.List;

@Repository
public interface InformeTecnicoDao extends JpaRepository<InformeTecnico, Integer> {

    @Query(value = "SELECT * FROM informe_tecnico AS informe INNER JOIN solicitud AS soli ON soli.id_solicitud=informe.id_solicitud INNER JOIN zona_ubicacion AS zona ON soli.id_zona_ubicacion=zona.id_zona_ubicacion WHERE soli.estado_solicitud=4", nativeQuery = true)
    List<InformeTecnico> listarSoliParaRegistrarResolucion();

    @Query(value = "SELECT * FROM informe_tecnico AS informe INNER JOIN solicitud AS soli ON informe.id_solicitud=soli.id_solicitud WHERE informe.id_informe_tecnico=?1", nativeQuery = true)
    InformeTecnico listadoRegistrarResolucion(int idInformeTecnico);


    @Query(value = "SELECT * FROM usuarios AS usu INNER JOIN personas AS per ON usu.id_persona=per.id_persona INNER JOIN solicitud AS soli ON soli.id_usuario=usu.id_usuario INNER JOIN informe_tecnico AS infor ON infor.id_solicitud=soli.id_solicitud WHERE infor.id_informe_tecnico=?1", nativeQuery = true)
    InformeTecnico listadaoParaEnvioNotificacion(int idInformeTecnico);

    @Query(value = "SELECT * FROM informe_tecnico AS informe INNER JOIN solicitud AS soli ON informe.id_solicitud=soli.id_solicitud WHERE soli.estado_solicitud=4 AND informe.codigo_informe_tecnico=?1", nativeQuery = true)
    InformeTecnico buscarSolicitudParaRegistrarResolucion(String codigoInformeTecnico);

}
