package pe.edu.upeu.sigrysmuc.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.empleado.entity.Empleado;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notificacion")
public class Notificacion {

    @Id
    @Column(name = "id_notificacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotificacion;

    @Column(name = "resultado_notificacion")
    private String resultadoNotificacion;

    @Column(name = "documento_notificacion")
    private String documentoNotificacion;

    @Column(name = "correo_enviado")
    private String correoEnviado;

    @Column(name = "descripcion_notificacion")
    private String descripcionNotificacion;

    //Foraneas

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="idEmpleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name="idSolicitud")
    private Solicitud solicitud;
}
