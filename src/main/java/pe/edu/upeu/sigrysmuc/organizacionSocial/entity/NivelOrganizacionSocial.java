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
@Table(name = "nivel_organizacio_social")
public class NivelOrganizacionSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_organizacion_social")
    private Integer idNivelOrganizacionSocial;

    @Column(name = "nivel_organizacion_social")
    private String nivelOrganizacionSocial;

    @Column(name = "estado_organizacin_social")
    private int estadoNivelOrgSocial;

    //relacion

    /*@OneToMany(mappedBy="nivelOrganizacionSocial")
    private List<Solicitud> uno;*/
}
