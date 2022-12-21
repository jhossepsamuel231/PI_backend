package pe.edu.upeu.sigrysmuc.organizacionSocial.service;

import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.CargoJunta;

import java.util.List;

public interface CargoJuntaService {

    List<CargoJunta> listarCargoJunta();

    CargoJunta ecnontrarCargoJunta(Integer idCargoJunta);
}
