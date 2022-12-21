package pe.edu.upeu.sigrysmuc.solicitud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.empleado.dao.EmpleadoDao;
import pe.edu.upeu.sigrysmuc.empleado.entity.Empleado;
import pe.edu.upeu.sigrysmuc.solicitud.dao.InformeTecnicoDao;
import pe.edu.upeu.sigrysmuc.solicitud.dao.SolicitudDao;
import pe.edu.upeu.sigrysmuc.solicitud.dto.InformeTecnicoDto;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.InformeTecnicoService;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Service
public class InformeTecnicoServiceImpl implements InformeTecnicoService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private InformeTecnicoDao informeTecnicoDao;

    @Autowired
    private EmpleadoDao empleadoDao;

    @Autowired
    private SolicitudDao solicitudDao;

    @Override
    public List<InformeTecnico> readAllDocumentos() {
        return informeTecnicoDao.findAll();
    }

    @Override
    public InformeTecnico registrarInformeTecnico(InformeTecnicoDto informeTecnicoDto, int idSolicitud) {
        Solicitud solicitudEncontrada = solicitudDao.findById(idSolicitud).get();

        InformeTecnico informeTecnicoNuevo = new InformeTecnico();



        informeTecnicoNuevo.setCodigoInformeTecnico(informeTecnicoDto.getCodigoInformeTecnico());

        informeTecnicoNuevo.setDescripcion(informeTecnicoDto.getDescripcion());
        informeTecnicoNuevo.setDocInformeTecnico(informeTecnicoDto.getDocInformeTecnico());
        informeTecnicoNuevo.setFechaEmision(new Date());
        informeTecnicoNuevo.setResultado(informeTecnicoDto.getResultado());
        informeTecnicoNuevo.setSolicitud(solicitudEncontrada);

        InformeTecnico informeTecnicoRegistrado = informeTecnicoDao.save(informeTecnicoNuevo);

        informeTecnicoRegistrado.setCodigoInformeTecnico("IT-" + informeTecnicoRegistrado.getIdInformeTecnico());
        return informeTecnicoDao.save(informeTecnicoRegistrado);
    }

}
