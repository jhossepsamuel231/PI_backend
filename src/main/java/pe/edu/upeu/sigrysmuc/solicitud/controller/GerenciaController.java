package pe.edu.upeu.sigrysmuc.solicitud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dto.ResolucionDto;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.CargoJunta;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.OrganizacionSocial;
import pe.edu.upeu.sigrysmuc.solicitud.dto.DocumentoDto;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Documento;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.GerenciaService;
import pe.edu.upeu.sigrysmuc.solicitud.service.InformeTecnicoService;
import pe.edu.upeu.sigrysmuc.solicitud.service.SolicitudService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/gerencia")
public class GerenciaController {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private GerenciaService gerenciaService;

    @Autowired
    private InformeTecnicoService informeTecnicoService;

    @GetMapping("/listarSoliParaPrimeraRevision")
    public ResponseEntity< ? > ListarParaPrimeraRevision(){
        List<Solicitud> solicitud = solicitudService.listaParaGerenciaPrimeraRevision();
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/listarSoliDocParaPrimeraRevision/{idSolicitud}")
    public ResponseEntity< ? > ListarDocParaPrimeraRevision(@PathVariable int idSolicitud){
        List<Documento> doc = gerenciaService.listarParaPriemraRevision(idSolicitud);
        return ResponseEntity.ok(doc);
    }

    @GetMapping("/listarSoliParaResolucion/{idSolicitud}")
    public ResponseEntity< ? > ListarParaAgregarResolucion(@PathVariable int idSolicitud ){
        Solicitud solicitudEncontrada = solicitudService.listarSolicitudParaRegistrarResolucion(idSolicitud);
        return ResponseEntity.ok(solicitudEncontrada);
    }


    /* Parte Dos */

    @GetMapping("/listar-informes")
    public List<InformeTecnico> getDocumentos(){
        return informeTecnicoService.readAllDocumentos();
    }

    @GetMapping("/listarParaRegistrarResolucion")
    public ResponseEntity< ? > listarParaRegistrarResolucion( ){
        List<InformeTecnico> solicitudEncontrada = gerenciaService.listarParaRegistrarResolucion();
        return ResponseEntity.ok(solicitudEncontrada);
    }

    @GetMapping("/listadoSoliParaRegistrar/{idInformeTecnico}")
    public ResponseEntity< ? > listadoSoliParaRegistrarResolucion(@PathVariable int idInformeTecnico ){
        InformeTecnico solicitudEncontrada = gerenciaService.listadoRegistrarResolucion(idInformeTecnico);
        return ResponseEntity.ok(solicitudEncontrada);
    }

    @GetMapping("/buscarSoliParaResolucion")
    @ResponseBody
    public ResponseEntity< ? > buscarSolicitudParaRegistroResolucion(@RequestParam String codigo){
        Solicitud solicitudEncontrada = solicitudService.buscarPararegistroResolucion(codigo);
        return  ResponseEntity.ok(solicitudEncontrada);
    }

    @PostMapping("/registrar-resolucion-orgSocial/{idSolicitud}")
    public ResponseEntity< ? > registrarResolucionYOrganizacionSocial(@RequestBody ResolucionDto resolucionDto, @PathVariable int idSolicitud){

        Map<String, Object> response = new HashMap<>();
        OrganizacionSocial organizacionSocialNueva = null;

        try {

            organizacionSocialNueva = gerenciaService.registrarResolucion(resolucionDto, idSolicitud);

        } catch (DataAccessException e) {

            response.put("mensaje", "Oops, paso algo con el recurso");
            response.put("error", e.getMessage() );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "resolucion y organizacion guardados correctamente!");
        response.put("data", organizacionSocialNueva );

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/listadoParaEnvioNotificacion/{idInformeTecnico}")
    public ResponseEntity< ? > listadoParaEnvioDeNotificacion(@PathVariable int idInformeTecnico){
        InformeTecnico informeTecnico = gerenciaService.listadoParaEnvioNotificacion(idInformeTecnico);
        return  ResponseEntity.ok(informeTecnico);
    }

    @PostMapping("/enviarNotificacion/{idSolicitud}")
    public ResponseEntity< ? > enviarNotificacion(@PathVariable int idSolicitud, @RequestBody Notificacion notificacion){

        Map<String, Object> response = new HashMap<>();
        Notificacion notificacionCreada = null;

        try {

            notificacionCreada = gerenciaService.enviarNotificacion(notificacion, idSolicitud);

        } catch (DataAccessException e) {

            response.put("mensaje", "Oops, paso algo con el recurso");
            response.put("error", e.getMessage() );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "notificacion enviada correctamente!");
        response.put("data", notificacionCreada );

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    //buscar para registrar resolucion;
    @GetMapping("/buscarSoliParaRegistrarResolucion/{codigoInformeTecnico}")
    public ResponseEntity< ? > listadoParaEnvioDeNotificacion(@PathVariable String codigoInformeTecnico){
        InformeTecnico informeTecnico = solicitudService.buscarSolicitudParaRegistrarResolucion(codigoInformeTecnico);
        return  ResponseEntity.ok(informeTecnico);
    }


}

