package pe.edu.upeu.sigrysmuc.usuario.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@Column(name = "nom_usuario")
	private String username;
	
	private String password;

	private String Nombre;
	
	private Boolean estado;

	private String imagenUsuario;

	//Foranea

	@ManyToOne
	@JoinColumn(name = "idRol")
	private Rol rol;

	@ManyToOne
	@JoinColumn(name = "idPersona")
	private Persona persona;

	//relacion

	/*@OneToMany(mappedBy="usuario")
	private Set<Solicitud> cinco;*/


}
