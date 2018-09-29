/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolos;

import negocio.GestorEvento;
import negocio.OnReceive;
import negocio.Constantes;
import com.sun.mail.pop3.POP3Folder;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;

/**
 *
 * @author Lisbeth
 */
public class LeerCorreo {

    private Store almacenMensaje;
    private Folder bandejaEntrada;

    public void Conectar() {

        Properties propiedadesLectura = System.getProperties();
        Session sesionLectura = Session.getInstance(propiedadesLectura);
        try {
            almacenMensaje = sesionLectura.getStore("pop3");
            //almacenMensaje.connect(servidor, cuenta, clave);            
            almacenMensaje.connect(Constantes.SERVER, Constantes.CORREO_SISTEMA, Constantes.PASSWRD_SISTEMA);

            Folder[] f = almacenMensaje.getDefaultFolder().list();
            //Folder f = almacenMensaje.getFolder("INBOX");
            for (Folder fd : f) {
                System.out.println(">> " + fd.getName());
            }
            bandejaEntrada = almacenMensaje.getFolder("INBOX");
            if ((bandejaEntrada != null) && (bandejaEntrada.exists())) {
                LeerCorreo();
                System.out.println("Esperando para leer");
            }
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(LeerCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(LeerCorreo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }

    public void LeerCorreo() {
        Message mensajes[];
        try {
            bandejaEntrada.open(Folder.READ_WRITE);
        } catch (MessagingException ex) {
            Logger.getLogger(LeerCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        FlagTerm opcionesLectura = new FlagTerm(new Flags(Flags.Flag.RECENT), false);
        try {
            mensajes = bandejaEntrada.search(opcionesLectura);
            for (Message mensaje : mensajes) {
                String from = InternetAddress.toString(mensaje.getFrom());
                int i = from.indexOf('<');
                int j = from.indexOf('>');
                if (j >= 0) {
                    from = from.substring(i + 1, j);

                    String asunto = mensaje.getSubject();

                    if (asunto.indexOf(':') < 0) {
                        asunto = asunto + ":";
                    }

                    int k = asunto.indexOf(':');

                    asunto = asunto.substring(0, k);
                    if (asunto.equals("") || asunto.equals("registrar_grupo")) {
                        //||asunto.equals("ver_carrito")||asunto.equals("comprar")){
                        asunto = mensaje.getSubject() + from;
                    } else {
                        asunto = mensaje.getSubject();
                    }
                    
                    ProcesarMensaje(mensaje);
                    
                    GestorEvento.dispatchOnReceive(new OnReceive(from, asunto, this));
                    //  mensaje.setFlag(Flags.Flag.DELETED, true);
                    mensaje.setFlag(Flags.Flag.DELETED, true);
                    System.out.println("***Terminado ");
                }
            }
            bandejaEntrada.close(true);
            almacenMensaje.close();
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(LeerCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ProcesarMensaje(Message mensaje) throws MessagingException, IOException {
        System.out.println("Mensaje " + mensaje.getMessageNumber());
        System.out.println("Fecha envio: " + mensaje.getSentDate());
        System.out.println("De: " + mensaje.getFrom()[0]);
        System.out.println("Asunto: " + mensaje.getSubject());

        String contentType = mensaje.getContentType();
        String messageContent = "";
        String attachFiles = "";
        //System.out.println("Tipo de Contenido: " + contentType);
        if (mensaje.isMimeType("text/plain")) {
            //System.out.println((String) mensaje.getContent());
        } else if (mensaje.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) mensaje.getContent();
            int numeroPartes = multipart.getCount();
            for (int partCount = 0; partCount < numeroPartes; partCount++) {
                MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(partCount);
                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                    // this part is attachment
                    String fileName = part.getFileName();
                    attachFiles += fileName + ", ";
                    //part.saveFile(new File(part.getFileName()));
                    part.saveFile("D:/Servidor/ArchivosCorreo/" + File.separator + fileName);
                   // System.out.println(fileName);
                } else {
                    //this part may be the message content
                    messageContent = part.getContent().toString();
                    //System.out.println(messageContent);

                }
            }
        }
    }
}
