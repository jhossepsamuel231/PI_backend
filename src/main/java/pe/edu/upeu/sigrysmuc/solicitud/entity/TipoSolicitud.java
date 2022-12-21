package pe.edu.upeu.sigrysmuc.solicitud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_solicitud")
public class TipoSolicitud {

    @Id
    @Column(name = "id_tipo_solicitud")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoSolicitud;

    @Column(name = "nombre_tipo_solicitud")
    private String nombreTipoSolicitud;

    @Column(name = "estado_tipo_solicitud")
    private int estadoTipoSolicitud;

    //relacion
   /* @OneToMany(mappedBy = "tipoSolicitud")
    private List<Solicitud> seis;*/
}
