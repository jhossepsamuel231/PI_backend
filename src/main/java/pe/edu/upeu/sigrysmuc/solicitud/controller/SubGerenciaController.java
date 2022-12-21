package pe.edu.upeu.sigrysmuc.solicitud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.JuntaDirectiva;
import pe.edu.upeu.sigrysmuc.solicitud.dao.InformeTecnicoDao;
import pe.edu.upeu.sigrysmuc.solicitud.dto.InformeTecnicoDto;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.InformeTecnicoService;
import pe.edu.upeu.sigrysmuc.solicitud.service.SolicitudService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/subGerencia")
public class SubGerenciaController {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private InformeTecnicoService informeTecnicoService;


    @GetMapping("/listarSoliParaSubirInformeTecnico")
    public ResponseEntity< ? > listarSoliParaSubirInformeTecnico(){
        List<Solicitud> solicitud = solicitudService.listaParaSubGerencia();
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/buscarSoli/{idSolicitud}")
    public Solicitud buscarSoliParaInformeTecnico(@PathVariable int idSolicitud){
        return solicitudService.listarSolicitudParaRegistrarInformeTecnico(idSolicitud);
    }



    @PostMapping("/crear-informe-tecnico/{idSolicitud}")
    public ResponseEntity< ? > crearInformeTecnico(@RequestBody InformeTecnicoDto informeTecnicoDto,@PathVariable int idSolicitud){
        Map<String, Object> response = new HashMap<>();
        InformeTecnico informeTecnicoCreado = null;

        try {

            informeTecnicoCreado = informeTecnicoService.registrarInformeTecnico(informeTecnicoDto, idSolicitud);

        } catch (
                DataAccessException e) {

            response.put("mensaje", "Oops, paso algo con el recurso");
            response.put("error", e.getMessage() );

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Informe Tecnico agregado correctamente!");
        response.put("data", informeTecnicoCreado );

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    //Buscar por codigo Solicitud
    @GetMapping("/buscarSolicitudParaSubirInformeTecnico/{codigoSolicitud}")
    public ResponseEntity< ? > buscarSolicitudParaSubirInformeTecnico(@PathVariable String codigoSolicitud){
        Solicitud solicitud = solicitudService.buscarSolicitudParaSubirInformeTecnico(codigoSolicitud);
        return  ResponseEntity.ok(solicitud);
    }

}
