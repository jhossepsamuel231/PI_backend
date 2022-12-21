package pe.edu.upeu.sigrysmuc.solicitud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Requisito;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDto {

    private List<RequisitoDto> documentosRequisitos;

}
