package pe.edu.upeu.sigrysmuc.solicitud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDto {

    private Integer zonaOrganizacionSocial;
    private Integer tipoOrganizacionSocial;
    private Integer nivelOrganizacionSocial;
    private Integer estadoSolicitud;

    private Integer tipoSolicitud;

    private String nombreOrganizacion;
    private String direccionSolicitud;
    private String correoSolicitante;

    private Integer usuario;
}
