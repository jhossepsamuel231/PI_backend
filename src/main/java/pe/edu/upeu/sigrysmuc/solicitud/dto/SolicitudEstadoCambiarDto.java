package pe.edu.upeu.sigrysmuc.solicitud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudEstadoCambiarDto {

    private Integer estadoSolicitud;

    private Integer estado;

}
