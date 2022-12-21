package pe.edu.upeu.sigrysmuc.organizacionSocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.NivelOrganizacionSocial;

@Repository
public interface NivelOrganizacionDao extends JpaRepository<NivelOrganizacionSocial, Integer> {
}
