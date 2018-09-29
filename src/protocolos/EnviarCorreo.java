/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolos;

import negocio.Constantes;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author Lisbeth
 */
public class EnviarCorreo {

    public Session sesionCorreo;
    String correoservidor = Constantes.CORREO_ADMINISTRADOR + Constantes.DOMINIO;
    final String username = Constantes.CORREO_SISTEMA;
    final String password = Constantes.PASSWRD_SISTEMA;
    String asunto = "Respuesta del Servidor";
// ------------------- correo para el server -----------------------
    public void EnviarCorreo(String html, String destinatario) throws MessagingException {
        Properties propiedadesEnvio = System.getProperties();
        propiedadesEnvio.put("mail.smtp.host", Constantes.SERVER);
        // propiedadesEnvio.put("mail.smtp.port", "25");
        propiedadesEnvio.put("mail.smtp.auth", "false");
        // propiedadesEnvio.put("mail.smtp.starttls.enable", "false");
        sesionCorreo = Session.getDefaultInstance(propiedadesEnvio);
//        sesionCorreo = Session.getInstance(propiedadesEnvio, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
        //sesionCorreo = Session.getInstance(propiedadesEnvio, null);
        Message mensageEnviar = new MimeMessage(sesionCorreo);
        mensageEnviar.setFrom(new InternetAddress(correoservidor));
        mensageEnviar.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensageEnviar.setSubject(asunto);
        mensageEnviar.setContent(html, "text/html");
        try {
            Transport.send(mensageEnviar);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
  
    
    
      public void EnviarCorreo(String html, String destinatario, String nombre) throws MessagingException, IOException {

        Properties propiedadesEnvio = System.getProperties();
        propiedadesEnvio.put("mail.smtp.host", Constantes.SERVER);
        propiedadesEnvio.put("mail.smtp.auth", "true");
        propiedadesEnvio.put("mail.smtp.starttls.enable", "true");
        sesionCorreo = Session.getInstance(propiedadesEnvio, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); //To change body of generated methods, choose Tools | Templates.
            }

        });
        MimeBodyPart cuerpoMensaje = new MimeBodyPart();
        cuerpoMensaje.setContent(html, "text/html");
        MimeBodyPart adjunto = new MimeBodyPart();
        adjunto.attachFile("D:/Servidor/ArchivosCorreo/" + nombre);

        Multipart multiparte = new MimeMultipart();

        multiparte.addBodyPart(cuerpoMensaje);
        multiparte.addBodyPart(adjunto);

        Message mensageEnviar = new MimeMessage(sesionCorreo);
        mensageEnviar.setFrom(new InternetAddress(correoservidor));
        mensageEnviar.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensageEnviar.setSubject(asunto);
        mensageEnviar.setContent(multiparte);
        Transport.send(mensageEnviar);
    }
 
  
    
// ------------------- correo para el gamil java -----------------------    
    
//    public void EnviarCorreo(String html, String destinatario) throws MessagingException {
//        //Properties propiedadesEnvio = System.getProperties();
////        propiedadesEnvio.put("mail.smtp.host", Constantes.SERVER);
////        // propiedadesEnvio.put("mail.smtp.port", "25");
////        propiedadesEnvio.put("mail.smtp.auth", "false");
//    String host = "smtp.gmail.com";
//
//        final String user = "grupo08scfcet@gmail.com";
//
//        final String password = new String("grupo08grupo08");
//
//        if (!user.equals("") && !password.equals("")) {
//            String SMTP_PORT = "465";
//            String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//
//            Properties props = new Properties();
//            props.put("mail.smtp.starttls.enable", "true");
//
//            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//            
//            props.put("mail.smtp.host", host);
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.debug", "true");
//            props.put("mail.smtp.port", SMTP_PORT);
//            props.put("mail.smtp.socketFactory.port", SMTP_PORT);
//            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
//            props.put("mail.smtp.socketFactory.fallback", "false");
//        // propiedadesEnvio.put("mail.smtp.starttls.enable", "false");
//        //sesionCorreo = Session.getDefaultInstance(propiedadesEnvio);
//        sesionCorreo = Session.getInstance(props, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(user, password); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
//        //sesionCorreo = Session.getInstance(propiedadesEnvio, null);
//        Message mensageEnviar = new MimeMessage(sesionCorreo);
//        mensageEnviar.setFrom(new InternetAddress(user));
//        mensageEnviar.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
//        mensageEnviar.setSubject(asunto);
//        mensageEnviar.setContent(html, "text/html");
//        try {
//            Transport.send(mensageEnviar);
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
//
//         }
//    }
//      public void EnviarCorreo(String html, String destinatario, String nombre) throws MessagingException, IOException {
//
//        Properties propiedadesEnvio = System.getProperties();
//        propiedadesEnvio.put("mail.smtp.host", Constantes.SERVER);
//        propiedadesEnvio.put("mail.smtp.auth", "true");
//        propiedadesEnvio.put("mail.smtp.starttls.enable", "true");
//        sesionCorreo = Session.getInstance(propiedadesEnvio, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
//        MimeBodyPart cuerpoMensaje = new MimeBodyPart();
//        cuerpoMensaje.setContent(html, "text/html");
//        MimeBodyPart adjunto = new MimeBodyPart();
//        adjunto.attachFile("D:/Servidor/ArchivosCorreo/" + nombre);
//
//        Multipart multiparte = new MimeMultipart();
//
//        multiparte.addBodyPart(cuerpoMensaje);
//        multiparte.addBodyPart(adjunto);
//
//        Message mensageEnviar = new MimeMessage(sesionCorreo);
//        mensageEnviar.setFrom(new InternetAddress(correoservidor));
//        mensageEnviar.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
//        mensageEnviar.setSubject(asunto);
//        mensageEnviar.setContent(multiparte);
//        Transport.send(mensageEnviar);
//    }
}
