package pe.edu.upeu.sigrysmuc.solicitud.service;

import pe.edu.upeu.sigrysmuc.solicitud.dto.InformeTecnicoDto;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;

import java.util.List;

public interface InformeTecnicoService {

    List<InformeTecnico> readAllDocumentos();
    InformeTecnico registrarInformeTecnico(InformeTecnicoDto informeTecnicoDto, int idSolicitud);


}
