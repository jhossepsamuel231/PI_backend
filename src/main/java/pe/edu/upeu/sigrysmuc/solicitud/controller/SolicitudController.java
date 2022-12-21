package pe.edu.upeu.sigrysmuc.solicitud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.JuntaDirectiva;
import pe.edu.upeu.sigrysmuc.solicitud.dto.DocumentoDto;
import pe.edu.upeu.sigrysmuc.solicitud.dto.SolicitudDto;
import pe.edu.upeu.sigrysmuc.solicitud.dto.SolicitudJuntaDirectivaDto;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Documento;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.SolicitudService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/solicitud")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping("/crearSolicitud")
    public ResponseEntity< ? > crearSolicitud(@RequestBody SolicitudDto solicitudDto){

        Map<String, Object> response = new HashMap<>();
        Solicitud solicitudCreada = new Solicitud();

        try {

            solicitudCreada = solicitudService.registrarSolicitud(solicitudDto);

        } catch (DataAccessException e) {

            response.put("mensaje", "Oops, paso algo con el recurso");
            response.put("error", e.getMessage() );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "solicitud enviada correctamente!");
        response.put("data", solicitudCreada );

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @PostMapping("/crearSolicitudJuntaDirectiva/{idSolicitud}")
    public ResponseEntity< ? > crearSolicitudJuntaDirectiva(@PathVariable int idSolicitud, @RequestBody SolicitudJuntaDirectivaDto solicitudJuntaDirectivaDto){

        Map<String, Object> response = new HashMap<>();
        JuntaDirectiva juntaDirectivaCreada = null;

        try {

            juntaDirectivaCreada = solicitudService.registrarJuntaDirectiva(idSolicitud, solicitudJuntaDirectivaDto);

        } catch (DataAccessException e) {

            response.put("mensaje", "Oops, paso algo con el recurso");
            response.put("error", e.getMessage() );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "solicitud enviada correctamente!");
        response.put("data", juntaDirectivaCreada );

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/listJuntaDirectivaDeSolicitante/{idSolicitud}")
    public ResponseEntity< ? > listarJuntaDirectivaParaSolicitante(@PathVariable int idSolicitud){
        List<JuntaDirectiva> juntaDirectiva = solicitudService.listarJuntaDirectivaParaSolicitante(idSolicitud);
        return ResponseEntity.ok(juntaDirectiva);
    }

    @PostMapping("/guardar-documentos-solicitud/{idSolicitud}")
    public int guardarDocsSolicitud(@PathVariable int idSolicitud ,@RequestBody DocumentoDto documentoDto){

        System.out.println(documentoDto);

        return solicitudService.registrarDocumentos(idSolicitud ,documentoDto);

        /*Map<String, Object> response = new HashMap<>();
       // Documento documentoGuardado = null;

        try {

            int documentoGuardado = solicitudService.registrarDocumentos(idSolicitud ,documentoDto);

        } catch (DataAccessException e) {

            response.put("mensaje", "Oops, paso algo con el recurso");
            response.put("error", e.getMessage() );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "documentos guardados correctamente!");
        //response.put("data", documentoGuardado );

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);*/
    }


    //ACTUALIZAR ORGANIZACION:

    @PostMapping("/actualizarSolicitudOrganizacion")
    public ResponseEntity< ? > actualizarOrganizacion(@RequestBody SolicitudDto solicitudDto){

        Map<String, Object> response = new HashMap<>();
        Solicitud solicitudCreada = new Solicitud();

        try {

            solicitudCreada = solicitudService.registrarSolicitudActualizarOrganizacion(solicitudDto);

        } catch (DataAccessException e) {

            response.put("mensaje", "Oops, paso algo con el recurso");
            response.put("error", e.getMessage() );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "solicitud de Actualizacion enviada correctamente!");
        response.put("data", solicitudCreada );

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }



}
