package pe.edu.upeu.sigrysmuc.empleado.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cargo_empleados")
public class CargoEmpleado {

    @Id
    @Column(name = "id_cargo_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargoEmpleado;

    @Column(name = "nombre_cargo_empleado")
    private String nombreCargoEmpleado;

    @Column(name = "estado_cargo_empleado")
    private int estadoCargoEmpleado;

    //relacion

    @OneToMany(mappedBy="cargoEmpleado")
    private Set<Empleado> empleado;
}
