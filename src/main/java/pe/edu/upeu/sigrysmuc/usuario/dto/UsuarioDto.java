package pe.edu.upeu.sigrysmuc.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.usuario.entity.Rol;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {


    //private int rol;
    private String nombre;
    private String username;

    private String password;
    private Boolean estado;

    private String nombrePersona;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private String telefono;
    private String domicilio;
    private String correo;
    private Date fechaNacimiento;
}
