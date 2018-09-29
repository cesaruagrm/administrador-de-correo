/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.EventObject;

/**
 *
 * @author Lisbeth
 */
public class OnSend extends EventObject {

    String cliente;
    String mensaje;
    boolean estado;

    public OnSend(String cliente, String mensaje, boolean estado, Object source) {
        super(source);
        this.cliente = cliente;
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    
 
}
