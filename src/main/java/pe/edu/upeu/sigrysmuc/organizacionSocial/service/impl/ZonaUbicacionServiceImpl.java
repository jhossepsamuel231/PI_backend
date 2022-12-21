package pe.edu.upeu.sigrysmuc.organizacionSocial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dao.ZonaUbicacionDao;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.ZonaUbicacion;
import pe.edu.upeu.sigrysmuc.organizacionSocial.service.ZonaUbicacionService;

import java.util.List;

@Service
public class ZonaUbicacionServiceImpl implements ZonaUbicacionService {

    @Autowired
    private ZonaUbicacionDao zonaUbicacionDao;

    @Override
    public List<ZonaUbicacion> listarZonaUbicacionOrgSocial() {
        return zonaUbicacionDao.findAll();
    }

    @Override
    public ZonaUbicacion encontrarZonaUbicById(Integer idZonaUbic) {
        return zonaUbicacionDao.findById(idZonaUbic).orElse(null);
    }
}
