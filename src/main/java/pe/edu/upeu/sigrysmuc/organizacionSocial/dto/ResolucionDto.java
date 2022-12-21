package pe.edu.upeu.sigrysmuc.organizacionSocial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.OrganizacionSocial;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResolucionDto {

    private String codigoResolucion;
    private String documentoResolucion;
    private String nombreOrganizacion;
    private String descripcionResolucion;


    private Integer idOrganizacionSocial;
    private String codigoOrganizacionSocial;
    private Date fechaVigencia;
    private Integer resolucion;

}
