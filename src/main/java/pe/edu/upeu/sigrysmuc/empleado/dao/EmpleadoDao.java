package pe.edu.upeu.sigrysmuc.empleado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sigrysmuc.empleado.entity.Empleado;

@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
}
