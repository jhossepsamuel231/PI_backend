package pe.edu.upeu.sigrysmuc.organizacionSocial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dao.TipoOrganizacionDao;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.TipoOrganizacion;
import pe.edu.upeu.sigrysmuc.organizacionSocial.service.TipoOrganizacionService;

import java.util.List;

@Service
public class TipoOrganizacionServiceImpl implements TipoOrganizacionService {

    @Autowired
    private TipoOrganizacionDao tipoOrganizacionDao;

    @Override
    public List<TipoOrganizacion> listarTipoOragnizacioSocial() {
        return tipoOrganizacionDao.findAll();
    }

    @Override
    public TipoOrganizacion encontrarTipoOrgById(Integer idTipoOrg) {
        return tipoOrganizacionDao.findById(idTipoOrg).orElse(null);
    }
}
