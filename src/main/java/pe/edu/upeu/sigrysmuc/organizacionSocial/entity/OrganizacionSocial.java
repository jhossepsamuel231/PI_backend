package pe.edu.upeu.sigrysmuc.organizacionSocial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organizaacion_social")
public class OrganizacionSocial {

    @Id
    @Column(name = "id_organizacion_social")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrganizacionSocial;

    private String codigoOrganizacionSocial;

    @Column(name = "fecha_vigencia")
    private LocalDate fechaVigencia;

    @Column(name = "nombre_organizacion")
    private String nombreOrganizacion;

    @Column(name = "estado_organizacion_social")
    private Integer estadoOrganizacionSocial;

    //Foraneas

    @ManyToOne
    @JoinColumn(name="idResolucion", nullable=false)
    private Resolucion resolucion;

    //relacion

   /* @OneToMany(mappedBy="organizacionSocial")
    private Set<JuntaDirectiva> juntaDirectiva;*/


}
