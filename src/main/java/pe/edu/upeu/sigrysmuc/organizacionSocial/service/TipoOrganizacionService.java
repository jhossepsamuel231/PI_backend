package pe.edu.upeu.sigrysmuc.organizacionSocial.service;

import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.TipoOrganizacion;

import java.util.List;

public interface TipoOrganizacionService {

    List<TipoOrganizacion> listarTipoOragnizacioSocial();

    TipoOrganizacion encontrarTipoOrgById(Integer idTipoOrg);
}
