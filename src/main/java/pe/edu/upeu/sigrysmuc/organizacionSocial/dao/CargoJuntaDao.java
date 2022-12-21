package pe.edu.upeu.sigrysmuc.organizacionSocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.CargoJunta;

@Repository
public interface CargoJuntaDao extends JpaRepository<CargoJunta, Integer> {
}
