package pe.edu.upeu.sigrysmuc.solicitud.service;

import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.JuntaDirectiva;
import pe.edu.upeu.sigrysmuc.solicitud.dto.DocumentoDto;
import pe.edu.upeu.sigrysmuc.solicitud.dto.SolicitudDto;
import pe.edu.upeu.sigrysmuc.solicitud.dto.SolicitudJuntaDirectivaDto;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Documento;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;

import java.util.List;

public interface SolicitudService {

    //Solicitante:
    Solicitud registrarSolicitud(SolicitudDto solicitudDto);

    Solicitud registrarSolicitudPorsiaca(int idSolicitud, Solicitud solicitud);

    JuntaDirectiva registrarJuntaDirectiva(int idSolicitud, SolicitudJuntaDirectivaDto juntaDirectivaDto);

    List<JuntaDirectiva> listarJuntaDirectivaParaSolicitante(int idSolicitud);

    int registrarDocumentos(int idSolicitud ,DocumentoDto documentoDto);

    List<Solicitud> listaParaMesaDePartes();

    //solicitante actualizar organizacion:
    Solicitud registrarSolicitudActualizarOrganizacion(SolicitudDto solicitudDto);


    //Para todos
    Solicitud derivarSolicitud(int idSolicitud,String area);

    //MesaDePartes
    Solicitud registrarCodigoYNumeroExp(int iDSolicitud, Solicitud solicitud);

    //gerencia:
    Solicitud listarSolicitudParaRegistrarResolucion(int idSoliitud);

    Solicitud buscarPararegistroResolucion(String codigo);

    List<Solicitud> listaParaGerenciaPrimeraRevision();

    List<Solicitud> listaParaGerenciaUltimaRevision();

    //Gerencia:
    //Para registrar resolucion:

    InformeTecnico buscarSolicitudParaRegistrarResolucion(String codigoInformeTecnico);

    //subgerencia
    List<Solicitud> listaParaSubGerencia();

    Solicitud listarSolicitudParaRegistrarInformeTecnico(int idSoliitud);

    Solicitud buscarSolicitudParaSubirInformeTecnico(String codigoSolicitud);

    //usuario
    Solicitud verificarRegistroSolicitud(int idUsuario);

    Solicitud verificarRegistradoSolicitud(int idUsuario);

    //Listar soli por usuario

    List<Solicitud> listadoDeSoliDeUsuarios(int idUsuario);

    List<Notificacion> listadoDeNotificacionPorUsuario(int idUsuario);


}
