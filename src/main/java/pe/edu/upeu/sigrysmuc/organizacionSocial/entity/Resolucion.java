package pe.edu.upeu.sigrysmuc.organizacionSocial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resolucion")
public class Resolucion {

    @Id
    @Column(name = "id_resolucion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResolucion;

    @Column(name = "codigo_resolucion")
    private String codigoResolucion;

    @Column(name = "documento_resolucion")
    private String documentoResolucion;

    @Column(name = "descripcion_resolucion")
    private String descripcionResolucion;

    @Column(name = "nombre_organizacion")
    private String nombreOrganizacion;

    //relacion

    @OneToMany(mappedBy="resolucion")
    private Set<OrganizacionSocial> organizacionSocial;

}
