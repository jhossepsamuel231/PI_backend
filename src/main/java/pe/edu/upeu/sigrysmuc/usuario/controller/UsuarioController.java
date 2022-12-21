package pe.edu.upeu.sigrysmuc.usuario.controller;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.SolicitudService;
import pe.edu.upeu.sigrysmuc.usuario.dto.UsuarioDto;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;
import pe.edu.upeu.sigrysmuc.usuario.service.UsuarioService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private SolicitudService solicitudService;

	@PostMapping("/crear-usuario")
	public ResponseEntity< ? > crearUsuario(@RequestBody UsuarioDto usuarioDto) {

		Map<String, Object> response = new HashMap<>();

		Usuario usuarioCreado 		 = null;

		if(usuarioService.validarUsuarioExistente(usuarioDto.getUsername())) {
			response.put("mensaje", "El usuario enviado ya exite en base de datos: ".concat(usuarioDto.getUsername()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			usuarioCreado = usuarioService.crearUsuario( usuarioDto );

		} catch (DataAccessException e) {

			response.put("mensaje", "Oops, paso algo con el recurso");
			response.put("error", e.getMessage() );

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "Usuario creado correctamente!");
		response.put("data", usuarioCreado );

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@GetMapping("/verificarRegistroSolicitud/{idUsuario}")
	public ResponseEntity< ? > verificarRegistroSolicitud(@PathVariable int idUsuario){
		Solicitud solicitud = solicitudService.verificarRegistroSolicitud(idUsuario);
		return  ResponseEntity.ok(solicitud);
	}

}
