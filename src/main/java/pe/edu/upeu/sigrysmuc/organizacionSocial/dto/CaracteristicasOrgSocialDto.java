package pe.edu.upeu.sigrysmuc.organizacionSocial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristicasOrgSocialDto {

    private Integer idTipoOrganizacionSocial;
    private String tipoOrganizacionSocial;
    private int idNivelOrganizacionSocial;
    List<?> listaNivel;
}
