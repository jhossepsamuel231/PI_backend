package pe.edu.upeu.sigrysmuc.solicitud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.constantes.Constantes;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dao.*;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.*;
import pe.edu.upeu.sigrysmuc.solicitud.dao.*;
import pe.edu.upeu.sigrysmuc.solicitud.dto.DocumentoDto;
import pe.edu.upeu.sigrysmuc.solicitud.dto.SolicitudDto;
import pe.edu.upeu.sigrysmuc.solicitud.dto.SolicitudJuntaDirectivaDto;
import pe.edu.upeu.sigrysmuc.solicitud.entity.*;
import pe.edu.upeu.sigrysmuc.solicitud.service.SolicitudService;
import pe.edu.upeu.sigrysmuc.usuario.dao.PersonaDao;
import pe.edu.upeu.sigrysmuc.usuario.dao.UsuarioDao;
import pe.edu.upeu.sigrysmuc.usuario.entity.Persona;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;

import java.util.Date;
import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudDao solicitudDao;

    @Autowired
    private NivelOrganizacionDao nivelOrganizacionSocialDao;

    @Autowired
    private TipoOrganizacionDao tipoOrganizacionDao;

    @Autowired
    private ZonaUbicacionDao zonaUbicacionDao;

    @Autowired
    private TipoSolicitudDao tipoSolicitudDao;

    @Autowired
    private CargoJuntaDao cargoJuntaDao;

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private JuntaDirectivaDao juntaDirectivaDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private DocumentoDao documentoDao;

    @Autowired
    private RequisitoDao requisitoDao;

    @Autowired
    private InformeTecnicoDao informeTecnicoDao;

    @Autowired
    private NotificacionDao notificacionDao;

    @Override
    public Solicitud registrarSolicitud(SolicitudDto solicitudDto) {
        System.out.println(solicitudDto);
        Solicitud registrarSolicitud = new Solicitud();
        Usuario usuario = new Usuario();


        NivelOrganizacionSocial nivelOrganizacion = nivelOrganizacionSocialDao.findById(solicitudDto.getNivelOrganizacionSocial()).orElse(null);
        TipoOrganizacion tipoOrganizacion = tipoOrganizacionDao.findById(solicitudDto.getTipoOrganizacionSocial()).orElse(null);
        ZonaUbicacion zonaUbicacion = zonaUbicacionDao.findById(solicitudDto.getZonaOrganizacionSocial()).orElse(null);

        Usuario usuarioEncontrado = usuarioDao.findById(solicitudDto.getUsuario()).orElse(null);
        TipoSolicitud tipoSolicitudEncontrado = tipoSolicitudDao.findById(1).orElse(null);

        registrarSolicitud.setUsuario(usuarioEncontrado);
        registrarSolicitud.setNombreOrganizacion(solicitudDto.getNombreOrganizacion());
        registrarSolicitud.setTipoOrganizacion(tipoOrganizacion);
        registrarSolicitud.setNivelOrganizacionSocial(nivelOrganizacion);
        registrarSolicitud.setZonaUbicacion(zonaUbicacion);
        registrarSolicitud.setCorreoSolicitante(solicitudDto.getCorreoSolicitante());
        registrarSolicitud.setEstadoSolicitd(Constantes.ESTADO_PROCESO);
        registrarSolicitud.setTipoSolicitud(tipoSolicitudEncontrado);
        registrarSolicitud.setDireccionSolicitud(solicitudDto.getDireccionSolicitud());
        registrarSolicitud.setCorreoSolicitante(solicitudDto.getCorreoSolicitante());
        registrarSolicitud.setFechaRegistro(new Date());

        return solicitudDao.save(registrarSolicitud);
    }

    @Override
    public Solicitud registrarSolicitudPorsiaca(int idSolicitud, Solicitud solicitud) {
        return solicitudDao.save(solicitud);
    }

    @Override
    public JuntaDirectiva registrarJuntaDirectiva(int idSolicitud, SolicitudJuntaDirectivaDto juntaDirectivaDto) {
        Solicitud solicitudEncontrada = solicitudDao.findById(idSolicitud).orElse(null);
        Persona registrarPersonaJunta = new Persona();
        JuntaDirectiva registrarJuntaDirectiva = new JuntaDirectiva();

        CargoJunta cargoJunta = cargoJuntaDao.findById(juntaDirectivaDto.getIdCargoJunta()).orElse(null);


        registrarPersonaJunta.setNombrePersona(juntaDirectivaDto.getNombrePersona());
        registrarPersonaJunta.setApellidoPaterno(juntaDirectivaDto.getApellidoPaterno());
        registrarPersonaJunta.setApellidoMaterno(juntaDirectivaDto.getApellidoMaterno());
        registrarPersonaJunta.setDni(juntaDirectivaDto.getDni());
        registrarPersonaJunta.setDomicilio(juntaDirectivaDto.getDomicilio());
        registrarPersonaJunta.setTelefono(juntaDirectivaDto.getTelefono());
        registrarPersonaJunta.setCorreo(juntaDirectivaDto.getCorreo());
        registrarPersonaJunta.setEstadoPersona(Constantes.PERSONA_JUNTA_DIRECTIVA_SOLICITUD);
        personaDao.save(registrarPersonaJunta);

        Persona persona = personaDao.findById(registrarPersonaJunta.getIdPersona()).orElse(null);
        registrarJuntaDirectiva.setCargoJunta(cargoJunta);
        registrarJuntaDirectiva.setPersona(persona);
        registrarJuntaDirectiva.setEstadoJuntaDirectiva(Constantes.JUNTA_DIRECTIVA_SOLICITUD);
        registrarJuntaDirectiva.setSolicitudJunta(solicitudEncontrada);


        return juntaDirectivaDao.save(registrarJuntaDirectiva);
    }

    @Override
    public List<JuntaDirectiva> listarJuntaDirectivaParaSolicitante(int idSolicitud) {
        return juntaDirectivaDao.listarJuntaDirectivaParaSolicitante(idSolicitud);
    }

    @Override
    public int registrarDocumentos(int idSolicitud ,DocumentoDto documentoDto) {
        System.out.println("este es el id "+idSolicitud);
        System.out.println("este es el documento dto " + documentoDto.getDocumentosRequisitos().get(2));
        Solicitud solicitudEncontrada = solicitudDao.findById(idSolicitud).orElse(null);
        for (int i = 0; i<documentoDto.getDocumentosRequisitos().size(); i++){
            Documento documentoCreado = new Documento();
            solicitudEncontrada.setEstadoSolicitd(Constantes.ESTADO_INICIO);
            documentoCreado.setSolicitudDocumento(solicitudEncontrada);
            documentoCreado.setAdjuntableDocumento(documentoDto.getDocumentosRequisitos().get(i).getFile());
            Requisito requisitoEncontrado = requisitoDao.findById(documentoDto.getDocumentosRequisitos().get(i).getIdRequisito()).orElse(null);
            documentoCreado.setRequisitoDocumento(requisitoEncontrado);
            documentoDao.save(documentoCreado);
        }

        /*Solicitud solicitudEncontrada = solicitudDao.findById(idSolicitud).orElse(null);
        Documento documentoCreado = new Documento();

        Requisito requisitoEncontrado = requisitoDao.findById(documentoDto.getRequisitoDocumento()).orElse(null);

        documentoCreado.setAdjuntableDocumento(documentoDto.getAdjuntableDocumento());
        documentoCreado.setRequisitoDocumento(requisitoEncontrado);
        documentoCreado.setSolicitudDocumento(solicitudEncontrada);*/

        return 1;
    }

    @Override
    public List<Solicitud> listaParaMesaDePartes() {
        return solicitudDao.listadoSolicitudParaMesaDePartes();
    }

    @Override
    public Solicitud registrarSolicitudActualizarOrganizacion(SolicitudDto solicitudDto) {
        System.out.println(solicitudDto);
        Solicitud registrarSolicitud = new Solicitud();
        Usuario usuario = new Usuario();


        NivelOrganizacionSocial nivelOrganizacion = nivelOrganizacionSocialDao.findById(solicitudDto.getNivelOrganizacionSocial()).orElse(null);
        TipoOrganizacion tipoOrganizacion = tipoOrganizacionDao.findById(solicitudDto.getTipoOrganizacionSocial()).orElse(null);
        ZonaUbicacion zonaUbicacion = zonaUbicacionDao.findById(solicitudDto.getZonaOrganizacionSocial()).orElse(null);

        Usuario usuarioEncontrado = usuarioDao.findById(solicitudDto.getUsuario()).orElse(null);
        TipoSolicitud tipoSolicitudEncontrado = tipoSolicitudDao.findById(2).orElse(null);

        registrarSolicitud.setUsuario(usuarioEncontrado);
        registrarSolicitud.setNombreOrganizacion(solicitudDto.getNombreOrganizacion());
        registrarSolicitud.setTipoOrganizacion(tipoOrganizacion);
        registrarSolicitud.setNivelOrganizacionSocial(nivelOrganizacion);
        registrarSolicitud.setZonaUbicacion(zonaUbicacion);
        registrarSolicitud.setEstadoSolicitd(Constantes.ESTADO_PROCESO);
        registrarSolicitud.setTipoSolicitud(tipoSolicitudEncontrado);
        registrarSolicitud.setCorreoSolicitante(solicitudDto.getCorreoSolicitante());
        registrarSolicitud.setDireccionSolicitud(solicitudDto.getDireccionSolicitud());
        registrarSolicitud.setFechaRegistro(new Date());

        return solicitudDao.save(registrarSolicitud);
    }

    @Override
    public List<Solicitud> listaParaGerenciaPrimeraRevision() {
        return solicitudDao.listadoSolicitudParaGerenciaPrimeraRevision();
    }

    @Override
    public List<Solicitud> listaParaSubGerencia() {
        return solicitudDao.listadoSolicitudParaSubGerencia();
    }

    @Override
    public List<Solicitud> listaParaGerenciaUltimaRevision() {
        return solicitudDao.listadoSolicitudParaGerenciaUltimaRevision();
    }

    @Override
    public InformeTecnico buscarSolicitudParaRegistrarResolucion(String codigoInformeTecnico) {
        return informeTecnicoDao.buscarSolicitudParaRegistrarResolucion(codigoInformeTecnico);
    }

    @Override
    public Solicitud derivarSolicitud(int idSolicitud,String area) {

        Solicitud solicitudEncontrada = solicitudDao.findById(idSolicitud).orElse(null);

        if (area.equals("mesaDePartes")) {
            solicitudEncontrada.setCodigoSolicitud("codigo-"+solicitudEncontrada.getIdSolicitud());
            solicitudEncontrada.setNumeroExpediente("0"+solicitudEncontrada.getIdSolicitud()+"-MDC-GPV-SOV-RUOS");
            solicitudEncontrada.setEstadoSolicitd(2);

        } else if (area.equals("GerenciaUno")) {

            solicitudEncontrada.setEstadoSolicitd(3);

        } else if (area.equals("subGerencia")) {

            solicitudEncontrada.setEstadoSolicitd(4);

        } else if (area.equals("GerenciaDos")) {

            solicitudEncontrada.setEstadoSolicitd(5);

        }else if (area.equals("rechazar")) {

            solicitudEncontrada.setEstadoSolicitd(6);
        }

        return solicitudDao.save(solicitudEncontrada);
    }

    @Override
    public Solicitud registrarCodigoYNumeroExp(int iDSolicitud, Solicitud solicitud) {
        Solicitud solicitudEncontrada = solicitudDao.findById(iDSolicitud).get();

        solicitudEncontrada.setCodigoSolicitud(solicitud.getCodigoSolicitud());
        solicitudEncontrada.setNumeroExpediente(solicitud.getNumeroExpediente());
        return solicitudDao.save(solicitudEncontrada);
    }

    @Override
    public Solicitud listarSolicitudParaRegistrarResolucion(int idSoliitud) {
        return solicitudDao.findById(idSoliitud).orElse(null);
    }

    @Override
    public Solicitud buscarPararegistroResolucion(String codigo) {
        return solicitudDao.listarParaRegistrarResolucion(codigo);
    }

    @Override
    public Solicitud listarSolicitudParaRegistrarInformeTecnico(int idSoliitud) {
        return solicitudDao.buscarSoliParaInformeTecnico(idSoliitud);
    }

    @Override
    public Solicitud buscarSolicitudParaSubirInformeTecnico(String codigoSolicitud) {
        return solicitudDao.buscarSolicitudParaSubirInformeTecnico(codigoSolicitud);
    }

    @Override
    public Solicitud verificarRegistroSolicitud(int idUsuario) {
        return solicitudDao.verificarRegistroSolicitud(idUsuario);
    }

    @Override
    public Solicitud verificarRegistradoSolicitud(int idUsuario) {
        return solicitudDao.verificarRegistradoSolicitud(idUsuario);
    }

    @Override
    public List<Solicitud> listadoDeSoliDeUsuarios(int idUsuario) {
        return solicitudDao.listadoDeSolicitudPorUsuario(idUsuario);
    }

    @Override
    public List<Notificacion> listadoDeNotificacionPorUsuario(int idUsuario) {
        return notificacionDao.listadoDeNotificacionPorUsuario(idUsuario);
    }
}
