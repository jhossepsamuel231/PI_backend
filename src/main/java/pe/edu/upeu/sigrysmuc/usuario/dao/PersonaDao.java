package pe.edu.upeu.sigrysmuc.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.usuario.entity.Persona;

@Repository
public interface PersonaDao extends JpaRepository<Persona, Integer> {


}
