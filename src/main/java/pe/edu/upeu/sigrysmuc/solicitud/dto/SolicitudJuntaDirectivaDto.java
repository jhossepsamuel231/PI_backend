package pe.edu.upeu.sigrysmuc.solicitud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudJuntaDirectivaDto {

    private String nombrePersona;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private String domicilio;
    private String telefono;
    private String correo;
    private int estadoPersona;

    private Integer idCargoJunta;
    private Integer idSolicitud;
    private Integer idPersona;
}
