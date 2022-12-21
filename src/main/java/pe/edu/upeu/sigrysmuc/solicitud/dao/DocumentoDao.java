package pe.edu.upeu.sigrysmuc.solicitud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Documento;


import java.util.List;

@Repository
public interface DocumentoDao extends JpaRepository<Documento, Integer> {

    @Query(value = "SELECT * FROM documentos AS doc INNER JOIN requisito AS req ON doc.id_requisito=req.id_requisito WHERE doc.id_solicitud = ?1", nativeQuery = true)
    List<Documento> listarDocPrimeraRevision(int idSolicitud);

}
