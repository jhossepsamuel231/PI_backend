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
@Table(name = "departamentos")
public class Departamento {

    @Id
    @Column(name = "id_departamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartamento;

    @Column(name = "nombre_departamento")
    private String nombreDepartamento;

    @Column(name = "codigo_departamento")
    private String codigoDepartamento;

    @Column(name = "estado_departamento")
    private int estadoDepartamento;

    //relacion

    @OneToMany(mappedBy="departamento")
    private Set<Empleado> empleado;
}
