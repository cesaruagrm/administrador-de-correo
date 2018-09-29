/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolos;

import negocio.GestorEvento;
import negocio.Procesador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lisbeth
 */
public class Correo extends Thread {

    Procesador procesador;
    boolean terminado = false;
    int intervalo = 5 * 1000;
    LeerCorreo leer;

    public Correo() {
        procesador = new Procesador();
        GestorEvento.addListener(procesador);
        leer = new LeerCorreo();
        leer.Conectar();
    }

    @Override
    public void run() {
        super.run();
        while (!terminado) {
            leer.LeerCorreo();
            try {
                Thread.sleep(intervalo);
            } catch (InterruptedException ex) {
                Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }

    public void Escuchar() {
        this.start();
    }

   
}
