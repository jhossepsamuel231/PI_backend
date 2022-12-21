package pe.edu.upeu.sigrysmuc.organizacionSocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cargo_junta")
public class CargoJunta {

    @Id
    @Column(name = "id_cargo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargoJunta;

    @Column(name = "cargo_junta")
    private String cargoJunta;

    @Column(name = "estado_cargo_junta")
    private int estadoCargoJunta;

    //relacion

    @JsonIgnore
    @OneToMany(mappedBy="cargoJunta")
    private Set<JuntaDirectiva> juntaDirectiva;
}
