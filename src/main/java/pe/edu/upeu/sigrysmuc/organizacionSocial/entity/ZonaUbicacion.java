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
@Table(name = "zona_ubicacion")
public class ZonaUbicacion {

    @Id
    @Column(name = "id_zona_ubicacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idZonaUbicacion;

    @Column(name = "zona_ubicacion")
    private String zonaUbicacion;

    @Column(name = "estado_zona_ubicacion")
    private int estadoZona;

    //relacion

    /*@OneToMany(mappedBy="zonaUbicacion")
    private List<Solicitud> tres;*/
}
