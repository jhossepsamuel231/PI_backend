package pe.edu.upeu.sigrysmuc.usuario.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.empleado.entity.Empleado;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.JuntaDirectiva;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @Column(name = "id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    private String nombrePersona;

    private String dni;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    private String telefono;

    private String domicilio;

    private String correo;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "estado_persona")
    private int estadoPersona;

    //Foraneas



    //relacion

    /*@OneToMany(mappedBy="persona")
    private Set<Empleado> empleado;

    @OneToMany(mappedBy = "persona")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy="persona")
    private Set<JuntaDirectiva> juntaDirectiva;*/
}
