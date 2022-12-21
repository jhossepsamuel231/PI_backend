package pe.edu.upeu.sigrysmuc.solicitud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.solicitud.dao.RequisitoDao;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Requisito;
import pe.edu.upeu.sigrysmuc.solicitud.service.VistaPublicaService;

import java.util.List;

@Service
public class VistaPublicaServiceImpl implements VistaPublicaService {

    @Autowired
    private RequisitoDao requisitoDao;

    @Override
    public List<Requisito> listarRequisito() {
        return requisitoDao.findAll();
    }

}
