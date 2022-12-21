package pe.edu.upeu.sigrysmuc.solicitud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.JuntaDirectiva;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;

import java.util.List;

@Repository
public interface SolicitudDao extends JpaRepository<Solicitud, Integer> {

    @Query(value = "SELECT * FROM solicitud AS soli INNER JOIN nivel_organizacio_social AS nivel ON soli.id_nivel_organizacion_social=nivel.id_nivel_organizacion_social INNER JOIN tipo_organizacio AS tipo ON tipo.id_tipo_organizacion_social = soli.id_tipo_organizacion_social INNER JOIN zona_ubicacion AS zona ON zona.id_zona_ubicacion=soli.id_zona_ubicacion INNER JOIN tipo_solicitud AS tipoSoli ON tipoSoli.id_tipo_solicitud=soli.id_tipo_solicitud WHERE estado_solicitud=1", nativeQuery = true)
    List<Solicitud> listadoSolicitudParaMesaDePartes();

    @Query(value = "SELECT * FROM solicitud AS soli INNER JOIN nivel_organizacio_social AS nivel ON soli.id_nivel_organizacion_social=nivel.id_nivel_organizacion_social INNER JOIN tipo_organizacio AS tipo ON tipo.id_tipo_organizacion_social = soli.id_tipo_organizacion_social INNER JOIN zona_ubicacion AS zona ON zona.id_zona_ubicacion=soli.id_zona_ubicacion INNER JOIN tipo_solicitud AS tipoSoli ON tipoSoli.id_tipo_solicitud=soli.id_tipo_solicitud WHERE estado_solicitud=2", nativeQuery = true)
    List<Solicitud> listadoSolicitudParaGerenciaPrimeraRevision();

    @Query(value = "SELECT * FROM solicitud AS soli INNER JOIN nivel_organizacio_social AS nivel ON soli.id_nivel_organizacion_social=nivel.id_nivel_organizacion_social INNER JOIN tipo_organizacio AS tipo ON tipo.id_tipo_organizacion_social = soli.id_tipo_organizacion_social INNER JOIN zona_ubicacion AS zona ON zona.id_zona_ubicacion=soli.id_zona_ubicacion INNER JOIN tipo_solicitud AS tipoSoli ON tipoSoli.id_tipo_solicitud=soli.id_tipo_solicitud WHERE estado_solicitud=3", nativeQuery = true)
    List<Solicitud> listadoSolicitudParaSubGerencia();

    @Query(value = "SELECT * FROM solicitud AS soli INNER JOIN nivel_organizacio_social AS nivel ON soli.id_nivel_organizacion_social=nivel.id_nivel_organizacion_social INNER JOIN tipo_organizacio AS tipo ON tipo.id_tipo_organizacion_social = soli.id_tipo_organizacion_social INNER JOIN zona_ubicacion AS zona ON zona.id_zona_ubicacion=soli.id_zona_ubicacion INNER JOIN tipo_solicitud AS tipoSoli ON tipoSoli.id_tipo_solicitud=soli.id_tipo_solicitud WHERE estado_solicitud=4", nativeQuery = true)
    List<Solicitud> listadoSolicitudParaGerenciaUltimaRevision();

    @Query(value = "SELECT * FROM solicitud AS soli INNER JOIN usuarios AS usu ON soli.id_usuario=usu.id_usuario INNER JOIN personas AS pers ON pers.id_persona=usu.id_persona INNER JOIN tipo_solicitud AS tiposoli ON soli.id_tipo_solicitud=tiposoli.id_tipo_solicitud INNER JOIN tipo_organizacio AS tipoorg ON soli.id_tipo_organizacion_social=tipoorg.id_tipo_organizacion_social INNER JOIN nivel_organizacio_social AS nivelorg ON soli.id_nivel_organizacion_social=nivelorg.id_nivel_organizacion_social WHERE soli.id_solicitud=?1",nativeQuery = true)
    Solicitud buscarSoliParaInformeTecnico(int idSolicitud);

    @Query(value = "SELECT * FROM solicitud WHERE estado_solicitud=5 AND numero_expediente = ?1", nativeQuery = true)
    Solicitud listarParaRegistrarResolucion(String numeroExpediente);

    //gerencia parte dos

    //Usuario
    @Query(value = "SELECT * FROM usuarios AS usu INNER JOIN solicitud AS soli ON usu.id_usuario=soli.id_usuario WHERE soli.id_tipo_solicitud=1 AND usu.id_usuario=?1", nativeQuery = true)
    Solicitud verificarRegistroSolicitud(int idUsuario);

    @Query(value = "SELECT * FROM usuarios AS usu INNER JOIN solicitud AS soli ON usu.id_usuario=soli.id_usuario WHERE soli.id_tipo_solicitud=1 AND soli.estado_solicitud=5 AND usu.id_usuario=?1", nativeQuery = true)
    Solicitud verificarRegistradoSolicitud(int idUsuario);

    //SubGerencia:

    @Query(value = "SELECT * FROM  solicitud AS soli INNER JOIN tipo_solicitud AS tipoSoli ON soli.id_tipo_solicitud=tipoSoli.id_tipo_solicitud WHERE soli.estado_solicitud=3 AND soli.codigo_solicitud=?1", nativeQuery = true)
    Solicitud buscarSolicitudParaSubirInformeTecnico(String codigoSolicitud);

    //Usuario

    @Query(value = "SELECT * FROM solicitud AS soli INNER JOIN usuarios AS usu ON soli.id_usuario=usu.id_usuario WHERE soli.id_usuario=?1", nativeQuery = true)
    List<Solicitud> listadoDeSolicitudPorUsuario(int idUsuario);
}
