package pe.edu.upeu.sigrysmuc.organizacionSocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.ZonaUbicacion;
import pe.edu.upeu.sigrysmuc.organizacionSocial.service.ZonaUbicacionService;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/zonaUbicacion")
public class ZonaUbicacionController {

    @Autowired
    private ZonaUbicacionService zonaUbicacionService;

    @GetMapping("/listarZonaUbic")
    public ResponseEntity< ? > listarZonaUbic(){
        List<ZonaUbicacion> zonaUbic = zonaUbicacionService.listarZonaUbicacionOrgSocial();
        return ResponseEntity.ok(zonaUbic);
    }
}
