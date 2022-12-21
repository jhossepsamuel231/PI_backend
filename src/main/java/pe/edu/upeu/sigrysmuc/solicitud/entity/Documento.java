package pe.edu.upeu.sigrysmuc.solicitud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @Column(name = "id_documento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;

    @Column(name = "adjuntable_documento")
    private String adjuntableDocumento;

    //Foreaneas:

    @ManyToOne
    @JoinColumn(name="id_solicitud")
    private Solicitud solicitudDocumento;

    @ManyToOne
    @JoinColumn(name="id_requisito")
    private Requisito requisitoDocumento;
}
