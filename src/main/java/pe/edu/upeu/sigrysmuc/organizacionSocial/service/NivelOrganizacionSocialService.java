package pe.edu.upeu.sigrysmuc.organizacionSocial.service;

import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.NivelOrganizacionSocial;

import java.util.List;

public interface NivelOrganizacionSocialService {

    List<NivelOrganizacionSocial> listarNivelOrganizacionSociales();

    NivelOrganizacionSocial encontrarNicelOrgById(Integer idNivelOrg);
}
