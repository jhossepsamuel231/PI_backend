package pe.edu.upeu.sigrysmuc.solicitud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformeTecnicoDto {


    private String codigoInformeTecnico;
    private String descripcion;
    private String docInformeTecnico;
    private String resultado;

    //private int idEmpleado;
}
