package pe.edu.upeu.sigrysmuc.organizacionSocial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_organizacio")
public class TipoOrganizacion {

    @Id
    @Column(name = "id_tipo_organizacion_social")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoOrganizacionSocial;

    @Column(name = "tipo_organizacion_social")
    private String tipoOrganizacionSocial;

    @Column(name = "estado_tipo_organizacion_social")
    private int estadoTipoOrgSocial;

    //Foraneas

    //relacion

    /*@OneToMany(mappedBy="tipoOrganizacion")
    private List<Solicitud> dos;*/


}
