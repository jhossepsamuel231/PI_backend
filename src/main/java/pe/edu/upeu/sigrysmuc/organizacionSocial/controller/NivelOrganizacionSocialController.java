package pe.edu.upeu.sigrysmuc.organizacionSocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.NivelOrganizacionSocial;
import pe.edu.upeu.sigrysmuc.organizacionSocial.service.NivelOrganizacionSocialService;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/nivelOrganizacion")
public class NivelOrganizacionSocialController {

    @Autowired
    private NivelOrganizacionSocialService nivelOrganizacionSocialService;

    @GetMapping("/listarNivelOrg")
    public ResponseEntity< ? > listarNivelOrg(){
        List<NivelOrganizacionSocial> nivelesOrg = nivelOrganizacionSocialService.listarNivelOrganizacionSociales();
        return ResponseEntity.ok(nivelesOrg);
    }
}
