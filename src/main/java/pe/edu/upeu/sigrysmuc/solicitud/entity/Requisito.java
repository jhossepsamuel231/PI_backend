package pe.edu.upeu.sigrysmuc.solicitud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requisito")
public class Requisito {

    @Id
    @Column(name = "id_requisito")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRequisito;

    @Column(name = "nombre_requisito")
    private String nombreRequisito;

    @Column(name = "estado_requisito")
    private int estadoRequisito;

    @Column(name = "alcance")
    private int alcance; // 1: agregar // 2: actualizar // 3: ambos

    @Column(name = "documento_requisito_word")
    private String requisitoDocWord;

    @Column(name = "documento_requisto_pdf")
    private String requisitoDocPdf;

    //Foraneas



    //relacion

    /*@OneToMany(mappedBy="requisitoDocumento")
    private Set<Documento> documentoRequisito;*/
}
