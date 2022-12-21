package pe.edu.upeu.sigrysmuc.solicitud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.empleado.entity.Empleado;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "informe_tecnico")
public class InformeTecnico {

    @Id
    @Column(name = "id_informe_tecnico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInformeTecnico;

    @Column(name = "codigo_informe_tecnico")
    private String codigoInformeTecnico;

    private String descripcion;

    @Column(name = "documento_informe_tecnico")
    private String docInformeTecnico;

    private String resultado;

    private Date fechaEmision;

    //Foraneas

    @ManyToOne
    @JoinColumn(name="idEmpleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name="idSolicitud")
    private Solicitud solicitud;
}
