package pe.edu.upeu.sigrysmuc.solicitud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sigrysmuc.solicitud.dao.SolicitudDao;
import pe.edu.upeu.sigrysmuc.solicitud.dto.SolicitudDto;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.SolicitudService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/mesaDePartes")
public class MesaDePartesController {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private SolicitudDao solicitudDao;

    @GetMapping("/listarSolicitudesMesaDePartes")
    public ResponseEntity< ? > listadoParaMesaDePartes(){
        List<Solicitud> solicitud = solicitudService.listaParaMesaDePartes();
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/actualizarEstado/{idsolicitud}/{area}")
    //@ResponseBody
    public Solicitud actualizarSolicitud(@PathVariable int idsolicitud,@PathVariable String area){
        Solicitud solicitudActualisada = solicitudService.derivarSolicitud(idsolicitud, area);
        return solicitudDao.save(solicitudActualisada);
        //return solicitudService.derivarSolicitud(idsolicitud, area);
    }

    @PutMapping("/registrarCodigo-NrExpediente/{iDSolicitud}")
    public ResponseEntity< ? > crearCodigoYNUmeroDeExpediente(@PathVariable int iDSolicitud ,@RequestBody Solicitud solicitud){

        Map<String, Object> response = new HashMap<>();
        Solicitud solicitudCreada = null;

        try {

            solicitudCreada = solicitudService.registrarCodigoYNumeroExp(iDSolicitud, solicitud);

        } catch (DataAccessException e) {

            response.put("mensaje", "Oops, paso algo con el recurso");
            response.put("error", e.getMessage() );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Codigo y Numero de Expediente creado correctamente!");
        response.put("data", solicitudCreada );

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
