package pe.edu.upeu.sigrysmuc.organizacionSocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.TipoOrganizacion;
import pe.edu.upeu.sigrysmuc.organizacionSocial.service.TipoOrganizacionService;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/tipoOrganizacion")
public class TipoOrganizacionSocialController {

    @Autowired
    private TipoOrganizacionService tipoOrganizacionService;

    @GetMapping("/listartipoOrg")
    public ResponseEntity< ? > listartipoOrg(){
        List<TipoOrganizacion> tiposOrg = tipoOrganizacionService.listarTipoOragnizacioSocial();
        return ResponseEntity.ok(tiposOrg);
    }
}
