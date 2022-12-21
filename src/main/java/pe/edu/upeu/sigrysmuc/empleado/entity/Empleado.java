package pe.edu.upeu.sigrysmuc.empleado.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;
import pe.edu.upeu.sigrysmuc.usuario.entity.Persona;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @Column(name = "id_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;

    @Column(name = "codigo_empleado")
    private String codigoEmpleado;

    @Column(name = "estado_empleado")
    private int estadoEmpleado;

    //Foranea

    @ManyToOne
    @JoinColumn(name="idCargoEmpleado", nullable=false)
    private CargoEmpleado cargoEmpleado;

    @ManyToOne
    @JoinColumn(name="idDepartamento", nullable=false)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name="idPersona", nullable=false)
    private Persona persona;

    //relacion

    /*@OneToMany(mappedBy="empleado")
    private Set<InformeTecnico> informeTecnico;

    @OneToMany(mappedBy="empleado")
    private Set<Notificacion> notificacion;*/

}
