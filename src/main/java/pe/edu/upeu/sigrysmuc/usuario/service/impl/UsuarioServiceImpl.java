package pe.edu.upeu.sigrysmuc.usuario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upeu.sigrysmuc.solicitud.dao.RolDao;
import pe.edu.upeu.sigrysmuc.usuario.dao.PersonaDao;
import pe.edu.upeu.sigrysmuc.constantes.Constantes;
import pe.edu.upeu.sigrysmuc.usuario.dao.UsuarioDao;
import pe.edu.upeu.sigrysmuc.usuario.dto.RecuperarCuentaDto;
import pe.edu.upeu.sigrysmuc.usuario.dto.UsuarioDto;
import pe.edu.upeu.sigrysmuc.usuario.entity.Persona;
import pe.edu.upeu.sigrysmuc.usuario.entity.Rol;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;
import pe.edu.upeu.sigrysmuc.usuario.service.UsuarioService;

import javax.mail.internet.MimeMessage;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private RolDao rolDao;

    @Override
    public Usuario crearUsuario(UsuarioDto usuarioDto) {

        Persona personaNueva = new Persona();
        Usuario usuarioNuevo = new Usuario();
        personaNueva.setNombrePersona(usuarioDto.getNombrePersona());
        personaNueva.setApellidoMaterno(usuarioDto.getApellidoMaterno());
        personaNueva.setApellidoPaterno(usuarioDto.getApellidoPaterno());
        personaNueva.setCorreo(usuarioDto.getCorreo());
        personaNueva.setFechaNacimiento(usuarioDto.getFechaNacimiento());
        personaNueva.setDni(usuarioDto.getDni());
        personaNueva.setTelefono(usuarioDto.getTelefono());
        personaNueva.setEstadoPersona(Constantes.PERSONA_USUARIO_CREADO);
        personaDao.save(personaNueva);

        Persona persona = personaDao.findById(personaNueva.getIdPersona()).orElse(null);
        Rol rol = rolDao.findById(1).orElse(null);

        usuarioNuevo.setUsername(usuarioDto.getDni());
        usuarioNuevo.setNombre(usuarioDto.getNombrePersona());
        usuarioNuevo.setNombre(usuarioDto.getNombrePersona());
        usuarioNuevo.setPassword(new BCryptPasswordEncoder().encode(usuarioDto.getPassword()));
        //usuarioNuevo.setPassword(usuarioDto.getPassword());
        usuarioNuevo.setRol(rol);
        usuarioNuevo.setPersona(persona);
        usuarioNuevo.setEstado(Constantes.ESTADO_ACTIVO);

       return usuarioDao.save(usuarioNuevo);
    }

    @Override
    public Usuario logearUsuario(String usuario, String contrasenia) {
        return usuarioDao.findByUsernameAndPassword(usuario,contrasenia);
    }

    @Override
    public Boolean validarUsuarioExistente(String nomUsuario) {

        Boolean isInBBDD = false;

        Usuario usuarioEncontrado = usuarioDao.findByUsername( nomUsuario );

        isInBBDD =  usuarioEncontrado != null;

        return isInBBDD;

    }
}
