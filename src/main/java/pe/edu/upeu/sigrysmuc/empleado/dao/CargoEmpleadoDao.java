package pe.edu.upeu.sigrysmuc.empleado.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sigrysmuc.empleado.entity.CargoEmpleado;

public interface CargoEmpleadoDao extends JpaRepository<CargoEmpleado, Integer> {
}
