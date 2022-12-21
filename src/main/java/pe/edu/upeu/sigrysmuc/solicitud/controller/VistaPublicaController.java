package pe.edu.upeu.sigrysmuc.solicitud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Requisito;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.VistaPublicaService;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/vistaPublica")
public class VistaPublicaController {

    @Autowired
    private VistaPublicaService vistaPublicaService;

    @GetMapping("/listarRequsitos")
    public ResponseEntity< ? > listarrequisitos(){
        List<Requisito> requisito = vistaPublicaService.listarRequisito();
        return ResponseEntity.ok(requisito);
    }

}
