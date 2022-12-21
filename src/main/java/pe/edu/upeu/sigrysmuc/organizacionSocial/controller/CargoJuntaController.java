package pe.edu.upeu.sigrysmuc.organizacionSocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.CargoJunta;
import pe.edu.upeu.sigrysmuc.organizacionSocial.service.CargoJuntaService;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/cargoJunta")
public class CargoJuntaController {

    @Autowired
    private CargoJuntaService cargoJuntaService;

    @GetMapping("/cargosJuntas")
    public ResponseEntity< ? > listarCargoJunta(){
        List<CargoJunta> cargoJunta = cargoJuntaService.listarCargoJunta();
        return ResponseEntity.ok(cargoJunta);
    }
}
