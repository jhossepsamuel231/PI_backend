package pe.edu.upeu.sigrysmuc.solicitud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sigrysmuc.constantes.Constantes;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dao.OrganizacionSocialDao;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dao.ResolucionDao;
import pe.edu.upeu.sigrysmuc.organizacionSocial.dto.ResolucionDto;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.OrganizacionSocial;
import pe.edu.upeu.sigrysmuc.organizacionSocial.entity.Resolucion;
import pe.edu.upeu.sigrysmuc.solicitud.dao.DocumentoDao;
import pe.edu.upeu.sigrysmuc.solicitud.dao.InformeTecnicoDao;
import pe.edu.upeu.sigrysmuc.solicitud.dao.NotificacionDao;
import pe.edu.upeu.sigrysmuc.solicitud.dao.SolicitudDao;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Documento;
import pe.edu.upeu.sigrysmuc.solicitud.entity.InformeTecnico;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Notificacion;
import pe.edu.upeu.sigrysmuc.solicitud.entity.Solicitud;
import pe.edu.upeu.sigrysmuc.solicitud.service.GerenciaService;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GerenciaServiceImpl implements GerenciaService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private DocumentoDao documentoDao;

    @Autowired
    private InformeTecnicoDao informeTecnicoDao;

    @Autowired
    private SolicitudDao solicitudDao;

    @Autowired
    private ResolucionDao resolucionDao;

    @Autowired
    private NotificacionDao notificacionDao;

    @Autowired
    private OrganizacionSocialDao organizacionSocialDao;

    @Override
    public List<Documento> listarParaPriemraRevision(int idSolicitud) {
        return documentoDao.listarDocPrimeraRevision(idSolicitud);
    }

    @Override
    public List<InformeTecnico> listarParaRegistrarResolucion() {
        return informeTecnicoDao.listarSoliParaRegistrarResolucion();
    }

    @Override
    public InformeTecnico listadoRegistrarResolucion(int idInformeTecnico) {
        return informeTecnicoDao.listadoRegistrarResolucion(idInformeTecnico);
    }

    @Override
    public OrganizacionSocial registrarResolucion(ResolucionDto resolucionDto, int idSolicitud) {
        Resolucion resolucionNueva = new Resolucion();

        resolucionNueva.setDocumentoResolucion(resolucionDto.getDocumentoResolucion());
        resolucionNueva.setNombreOrganizacion(resolucionDto.getNombreOrganizacion());
        resolucionNueva.setDescripcionResolucion(resolucionDto.getDescripcionResolucion());
        resolucionDao.save(resolucionNueva);

        Resolucion resolucion = resolucionDao.findById(resolucionNueva.getIdResolucion()).orElse(null);
        resolucion.setCodigoResolucion("resolucion-"+resolucion.getIdResolucion());

        OrganizacionSocial organizacionSocialNueva = new OrganizacionSocial();

        LocalDate date = LocalDate.now();

        organizacionSocialNueva.setNombreOrganizacion(resolucionDto.getNombreOrganizacion());
        organizacionSocialNueva.setEstadoOrganizacionSocial(Constantes.ORGANIZACION_SOCIAL_CREADA);
        organizacionSocialNueva.setFechaVigencia(date.plusYears(4));
        organizacionSocialNueva.setResolucion(resolucion);
        organizacionSocialDao.save(organizacionSocialNueva);

        OrganizacionSocial organizacionSocialCreada = organizacionSocialDao.findById(organizacionSocialNueva.getIdOrganizacionSocial()).orElse(null);
        Solicitud solicitud = new Solicitud();

        solicitud.setOrganizacionSocialSolicitud(organizacionSocialCreada);
        organizacionSocialCreada.setCodigoOrganizacionSocial("organizacion-"+organizacionSocialCreada.getIdOrganizacionSocial());

        return organizacionSocialDao.save(organizacionSocialCreada);
    }

    @Override
    public InformeTecnico listadoParaEnvioNotificacion(int idInformeTecnico) {
        return informeTecnicoDao.listadaoParaEnvioNotificacion(idInformeTecnico);
    }

    @Override
    public Notificacion enviarNotificacion(Notificacion notificacion, int idSolicitud) {

        Solicitud solicitudEncontrada = solicitudDao.findById(idSolicitud).orElse(null);

        notificacion.setSolicitud(solicitudEncontrada);
        notificacion.setDescripcionNotificacion(notificacion.getDescripcionNotificacion());
        notificacion.setResultadoNotificacion(notificacion.getResultadoNotificacion());

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            System.out.println("ESTE ES EL CORREO: " + notificacion.getCorreoEnviado());
            helper.setFrom("seniorbigotes42@gmail.com");
            helper.setTo(notificacion.getCorreoEnviado());
            helper.setSubject("Universidad Peruana Union - Informe");
            helper.setText(contentHtml(notificacion.getDocumentoNotificacion()), true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
        }
        return notificacionDao.save(notificacion);
    }

    private static String contentHtml(String documento_url) {
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:arial, 'helvetica neue', helvetica, sans-serif\">\r\n"
                + " <head>\r\n"
                + "  <meta charset=\"UTF-8\">\r\n"
                + "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\r\n"
                + "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
                + "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
                + "  <meta content=\"telephone=no\" name=\"format-detection\">\r\n"
                + "  <title>See the world</title><!--[if (mso 16)]>\r\n"
                + "    <style type=\"text/css\">\r\n"
                + "    a {text-decoration: none;}\r\n"
                + "    </style>\r\n"
                + "    <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\r\n"
                + "<xml>\r\n"
                + "    <o:OfficeDocumentSettings>\r\n"
                + "    <o:AllowPNG></o:AllowPNG>\r\n"
                + "    <o:PixelsPerInch>96</o:PixelsPerInch>\r\n"
                + "    </o:OfficeDocumentSettings>\r\n"
                + "</xml>\r\n"
                + "<![endif]--><!--[if !mso]><!-- -->\r\n"
                + "  <link href=\"https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i\" rel=\"stylesheet\"><!--<![endif]-->\r\n"
                + "  <style type=\"text/css\">\r\n"
                + "#outlook a {\r\n"
                + "	padding:0;\r\n"
                + "}\r\n"
                + ".es-button {\r\n"
                + "	mso-style-priority:100!important;\r\n"
                + "	text-decoration:none!important;\r\n"
                + "}\r\n"
                + "a[x-apple-data-detectors] {\r\n"
                + "	color:inherit!important;\r\n"
                + "	text-decoration:none!important;\r\n"
                + "	font-size:inherit!important;\r\n"
                + "	font-family:inherit!important;\r\n"
                + "	font-weight:inherit!important;\r\n"
                + "	line-height:inherit!important;\r\n"
                + "}\r\n"
                + ".es-desk-hidden {\r\n"
                + "	display:none;\r\n"
                + "	float:left;\r\n"
                + "	overflow:hidden;\r\n"
                + "	width:0;\r\n"
                + "	max-height:0;\r\n"
                + "	line-height:0;\r\n"
                + "	mso-hide:all;\r\n"
                + "}\r\n"
                + "[data-ogsb] .es-button {\r\n"
                + "	border-width:0!important;\r\n"
                + "	padding:10px 20px 10px 20px!important;\r\n"
                + "}\r\n"
                + "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important } .es-menu td a { font-size:12px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:16px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:16px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:16px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:20px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } .h-auto { height:auto!important } }\r\n"
                + "</style>\r\n"
                + " </head>\r\n"
                + " <body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\r\n"
                + "  <div class=\"es-wrapper-color\" style=\"background-color:#F0F0F0\"><!--[if gte mso 9]>\r\n"
                + "			<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\r\n"
                + "				<v:fill type=\"tile\" color=\"#f0f0f0\"></v:fill>\r\n"
                + "			</v:background>\r\n"
                + "		<![endif]-->\r\n"
                + "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F0F0F0\">\r\n"
                + "     <tr>\r\n"
                + "      <td valign=\"top\" style=\"padding:0;Margin:0\">\r\n"
                + "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\r\n"
                + "         <tr>\r\n"
                + "          <td align=\"center\" style=\"padding:0;Margin:0\">\r\n"
                + "           <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\r\n"
                + "             <tr>\r\n"
                + "              <td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-top:20px;padding-left:20px;padding-right:20px\">\r\n"
                + "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                 <tr>\r\n"
                + "                  <td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\r\n"
                + "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;border-width:2px;border-style:solid;border-color:transparent\" role=\"presentation\">\r\n"
                + "                     <tr>\r\n"
                + "                      <td align=\"left\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\""+documento_url+"\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#999999;font-size:14px\"><img class=\"adapt-img\" src=\"https://vgnjnj.stripocdn.email/content/guids/CABINET_d471a7584d8e8495d7935a3a17a15c32/images/upeu_logo.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"185.65\"></a></td>\r\n"
                + "                     </tr>\r\n"
                + "                   </table></td>\r\n"
                + "                 </tr>\r\n"
                + "               </table></td>\r\n"
                + "             </tr>\r\n"
                + "             <tr>\r\n"
                + "              <td align=\"left\" style=\"padding:0;Margin:0\">\r\n"
                + "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                 <tr>\r\n"
                + "                  <td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\">\r\n"
                + "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                     <tr>\r\n"
                + "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0\">\r\n"
                + "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                         <tr>\r\n"
                + "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #023052;background:none;height:1px;width:100%;margin:0px\"></td>\r\n"
                + "                         </tr>\r\n"
                + "                       </table></td>\r\n"
                + "                     </tr>\r\n"
                + "                   </table></td>\r\n"
                + "                 </tr>\r\n"
                + "               </table></td>\r\n"
                + "             </tr>\r\n"
                + "           </table></td>\r\n"
                + "         </tr>\r\n"
                + "       </table>\r\n"
                + "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\r\n"
                + "         <tr>\r\n"
                + "          <td align=\"center\" style=\"padding:0;Margin:0\">\r\n"
                + "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\r\n"
                + "             <tr>\r\n"
                + "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px\">\r\n"
                + "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                 <tr>\r\n"
                + "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\r\n"
                + "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                     <tr>\r\n"
                + "                      <td align=\"left\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0;padding-top:10px\"><h1 style=\"Margin:0;line-height:43px;mso-line-height-rule:exactly;font-family:lora, georgia, 'times new roman', serif;font-size:36px;font-style:normal;font-weight:normal;color:#666666\"><font color=\"#000066\"><b>Informe de la Universidad</b></font></h1></td>\r\n"
                + "                     </tr>\r\n"
                + "                     <tr>\r\n"
                + "                      <td align=\"left\" class=\"es-m-txt-l\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:27px;color:#333333;font-size:18px\">Se le informa que se ha subido un nuevo documento</p></td>\r\n"
                + "                     </tr>\r\n"
                + "                     <tr>\r\n"
                + "                      <td align=\"center\" style=\"padding:0;Margin:0\"><span class=\"es-button-border\" style=\"border-style:solid;border-color:#2CB543;background:#023052;border-width:0px;display:inline-block;border-radius:8px;width:auto\"><a href=\""+documento_url+"\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:18px;border-style:solid;border-color:#023052;border-width:10px 20px 10px 20px;display:inline-block;background:#023052;border-radius:8px;font-family:lora, georgia, 'times new roman', serif;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center\">Ver Documento</a></span></td>\r\n"
                + "                     </tr>\r\n"
                + "                   </table></td>\r\n"
                + "                 </tr>\r\n"
                + "               </table></td>\r\n"
                + "             </tr>\r\n"
                + "             <tr>\r\n"
                + "              <td align=\"left\" style=\"padding:0;Margin:0;padding-left:20px;padding-right:20px\">\r\n"
                + "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                 <tr>\r\n"
                + "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\r\n"
                + "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                     <tr>\r\n"
                + "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px;font-size:0\">\r\n"
                + "                       <table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
                + "                         <tr>\r\n"
                + "                          <td style=\"padding:0;Margin:0;border-bottom:1px solid #023052;background:none;height:1px;width:100%;margin:0px\"></td>\r\n"
                + "                         </tr>\r\n"
                + "                       </table></td>\r\n"
                + "                     </tr>\r\n"
                + "                   </table></td>\r\n"
                + "                 </tr>\r\n"
                + "               </table></td>\r\n"
                + "             </tr>\r\n"
                + "           </table></td>\r\n"
                + "         </tr>\r\n"
                + "       </table></td>\r\n"
                + "     </tr>\r\n"
                + "   </table>\r\n"
                + "  </div>\r\n"
                + " </body>\r\n"
                + "</html>"
                ;
    }
}
