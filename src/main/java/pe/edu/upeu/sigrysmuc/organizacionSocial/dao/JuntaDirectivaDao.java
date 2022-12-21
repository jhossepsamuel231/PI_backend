package pe.edu.upeu.sigrysmuc.organizacionSocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.JuntaDirectiva;

import java.util.List;

@Repository
public interface JuntaDirectivaDao extends JpaRepository<JuntaDirectiva, Integer> {

    @Query(value = "SELECT * FROM juntas_directivas AS junta INNER JOIN personas AS per ON per.id_persona=junta.id_persona INNER JOIN cargo_junta AS cargo ON cargo.id_cargo=junta.id_cargo_junta INNER JOIN solicitud AS soli ON junta.id_solicitud=soli.id_solicitud WHERE soli.id_solicitud=?1", nativeQuery = true)
    List<JuntaDirectiva> listarJuntaDirectivaParaSolicitante(int idSolicitud);
}
