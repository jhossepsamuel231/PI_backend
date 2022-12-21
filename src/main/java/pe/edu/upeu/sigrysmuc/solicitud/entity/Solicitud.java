package pe.edu.upeu.sigrysmuc.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.NivelOrganizacionSocial;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.OrganizacionSocial;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.TipoOrganizacion;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.ZonaUbicacion;
import pe.edu.upeu.sigrysmuc.usuario.entity.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "solicitud")
public class Solicitud  {

    @Id
    @Column(name = "id_solicitud")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;

    @Column(name = "nombre_oraganizacion")
    private String nombreOrganizacion;

    @Column(name = "codigo_solicitud")
    private String codigoSolicitud;

    @Column(name = "direccion")
    private String direccionSolicitud;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "numero_expediente")
    private String numeroExpediente;

    @Column(name = "estado_solicitud")
    private int estadoSolicitd;

    @Column(name = "correo_solicitante")
    private String correoSolicitante;

    //Foraneas

    /*@ManyToOne
    @JoinColumn(name="id_estado_solicitud", nullable=false)
    private EstadoSolicitud estadoSolicitud;*/


    @ManyToOne
    @JoinColumn(name="id_tipo_organizacion_social")
    private TipoOrganizacion tipoOrganizacion;


    @ManyToOne
    @JoinColumn(name="id_nivel_organizacion_social")
    private NivelOrganizacionSocial nivelOrganizacionSocial;


    @ManyToOne
    @JoinColumn(name="id_zona_ubicacion")
    private ZonaUbicacion zonaUbicacion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name="id_tipo_solicitud")
    private TipoSolicitud tipoSolicitud;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_organizacion_social")
    private OrganizacionSocial organizacionSocialSolicitud;
    //relacion

    /*@OneToMany(mappedBy="solicitud")
    private Set<InformeTecnico> informeTecnico;

    @OneToMany(mappedBy="solicitud")
    private Set<Notificacion> notificacion;

    @OneToMany(mappedBy="solicitudDocumento")
    private Set<Documento> documentosSolicitud;*/
}
