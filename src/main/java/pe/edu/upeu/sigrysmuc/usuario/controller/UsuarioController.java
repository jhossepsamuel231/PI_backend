package pe.edu.upeu.sigrysmuc.usuario.controller;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.SolicitudService;
import pe.edu.upeu.sigrysmuc.usuario.dto.RecuperarCuentaDto;
import pe.edu.upeu.sigrysmuc.usuario.dto.UsuarioDto;
import pe.edu.upeu.sigrysmuc.usuario.entity.Persona;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;
import pe.edu.upeu.sigrysmuc.usuario.service.UsuarioService;

import java.util.HashMap;
import java.util.List;
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

	@GetMapping("/verificarRegistradoSolicitud/{idUsuario}")
	public ResponseEntity< ? > verificarRegistradoSolicitud(@PathVariable int idUsuario){
		Solicitud solicitud = solicitudService.verificarRegistradoSolicitud(idUsuario);
		return  ResponseEntity.ok(solicitud);
	}

	@GetMapping("/listadoSolicitudPorUsuario/{idUsuario}")
	public ResponseEntity< ? > listadoDeSolicitudPorUsuario(@PathVariable int idUsuario){
		List<Solicitud> solicitud = solicitudService.listadoDeSoliDeUsuarios(idUsuario);
		return  ResponseEntity.ok(solicitud);
	}

	@GetMapping("/listadoNotificacionPorUsuario/{idUsuario}")
	public ResponseEntity< ? > listadoDeNotificacionPorUsuario(@PathVariable int idUsuario){
		List<Notificacion> notificacion = solicitudService.listadoDeNotificacionPorUsuario(idUsuario);
		return  ResponseEntity.ok(notificacion);
	}


}
