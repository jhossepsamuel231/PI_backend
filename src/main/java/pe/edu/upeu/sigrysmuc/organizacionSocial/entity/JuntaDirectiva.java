package pe.edu.upeu.sigrysmuc.organizacionSocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.usuario.entity.Persona;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "juntas_directivas")
public class JuntaDirectiva {

    @Id
    @Column(name = "id_junta_directiva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJuntaDirectiva;

    @Column(name = "estado_junta_directiva")
    private int estadoJuntaDirectiva;

    //Foranea


    @ManyToOne
    @JoinColumn(name="id_cargo_junta")
    private CargoJunta cargoJunta;


    @ManyToOne
    @JoinColumn(name="id_organizacion_social")
    private OrganizacionSocial organizacionSocial;


    @ManyToOne
    @JoinColumn(name="id_persona")
    private Persona persona;


    @ManyToOne
    @JoinColumn(name="id_solicitud")
    private Solicitud solicitudJunta;

}
