package pe.edu.upeu.sigrysmuc.usuario.service;

import pe.edu.upeu.sigrysmuc.usuario.dto.RecuperarCuentaDto;
import pe.edu.upeu.sigrysmuc.usuario.dto.UsuarioDto;
import pe.edu.upeu.sigrysmuc.usuario.entity.Persona;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario crearUsuario(UsuarioDto usuarioDto);

    Usuario logearUsuario(String usuario, String contrasenia);

    Boolean validarUsuarioExistente(String username);


}
