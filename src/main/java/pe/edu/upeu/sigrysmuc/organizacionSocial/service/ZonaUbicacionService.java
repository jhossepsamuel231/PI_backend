package pe.edu.upeu.sigrysmuc.organizacionSocial.service;

import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.ZonaUbicacion;

import java.util.List;

public interface ZonaUbicacionService {

    List<ZonaUbicacion> listarZonaUbicacionOrgSocial();

    ZonaUbicacion encontrarZonaUbicById(Integer idZonaUbic);
}
