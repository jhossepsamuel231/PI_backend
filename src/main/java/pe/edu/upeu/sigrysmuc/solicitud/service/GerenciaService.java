package pe.edu.upeu.sigrysmuc.solicitud.service;

import pe.edu.upeu.sigrysmuc.organizacionSocial.dto.ResolucionDto;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.OrganizacionSocial;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.Resolucion;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Documento;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;

import java.util.List;

public interface GerenciaService {

    List<Documento> listarParaPriemraRevision(int idSolicitud);

    List<InformeTecnico> listarParaRegistrarResolucion();

    //parte dos

    InformeTecnico listadoRegistrarResolucion(int idInformeTecnico);

    OrganizacionSocial registrarResolucion(ResolucionDto resolucionDto, int idSolicitud);

    InformeTecnico listadoParaEnvioNotificacion(int idInformeTecnico);

    Notificacion enviarNotificacion(Notificacion notificacion, int idSolicitud);
}
