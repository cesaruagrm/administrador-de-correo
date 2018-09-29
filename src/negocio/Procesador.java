/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import negocio.Analex;
import protocolos.EnviarCorreo;
import negocio.OnReceive;
import negocio.OnSend;
import negocio.ListenerCorreo;
import javax.mail.MessagingException;

/**
 *
 * @author Lisbeth
 */
public class Procesador implements ListenerCorreo {

    Analex analizador;
    EnviarCorreo correo;

    public Procesador() {
        analizador = new Analex();
        correo = new EnviarCorreo();
    }

    @Override
    public void Receive(OnReceive evento) {
        try {
            correo.EnviarCorreo(analizador.Analizar(evento.getAsunto(), evento.getCliente()), evento.getCliente());
        } catch (MessagingException ex) {
            //Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Send(OnSend evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
