package pe.edu.upeu.sigrysmuc.solicitud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequisitoDto {
    private int idRequisito;
    private String  nombreRequisito;
    private int estadoRequisito;
    private int  alcance;
    private String requisitoDocWord;
    private String  requisitoDocPdf;
    private String file;
    private String verDocumento;
}
