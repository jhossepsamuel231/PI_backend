package pe.edu.upeu.sigrysmuc.organizacionSocial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dao.CargoJuntaDao;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.CargoJunta;
import pe.edu.upeu.sigrysmuc.organizacionSocial.service.CargoJuntaService;

import java.util.List;

@Service
public class CargoJuntaServiceImpl implements CargoJuntaService {

    @Autowired
    private CargoJuntaDao cargoJuntaDao;

    @Override
    public List<CargoJunta> listarCargoJunta() {
        return cargoJuntaDao.findAll();
    }

    @Override
    public CargoJunta ecnontrarCargoJunta(Integer idCargoJunta) {
        return cargoJuntaDao.findById(idCargoJunta).orElse(null);
    }
}
