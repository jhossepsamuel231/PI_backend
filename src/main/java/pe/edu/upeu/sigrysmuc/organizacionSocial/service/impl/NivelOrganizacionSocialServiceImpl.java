package pe.edu.upeu.sigrysmuc.organizacionSocial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dao.NivelOrganizacionDao;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.NivelOrganizacionSocial;
import pe.edu.upeu.sigrysmuc.organizacionSocial.service.NivelOrganizacionSocialService;

import java.util.List;

@Service
public class NivelOrganizacionSocialServiceImpl implements NivelOrganizacionSocialService {

    @Autowired
    private NivelOrganizacionDao nivelOrganizacionSocialDao;

    @Override
    public List<NivelOrganizacionSocial> listarNivelOrganizacionSociales() {
        return nivelOrganizacionSocialDao.findAll();
    }

    @Override
    public NivelOrganizacionSocial encontrarNicelOrgById(Integer idNivelOrg) {
        return nivelOrganizacionSocialDao.findById(idNivelOrg).orElse(null);
    }
}
