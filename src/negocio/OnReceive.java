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
public class OnReceive extends EventObject {

    String cliente;
    String asunto;

    public OnReceive(String cliente, String asunto, Object source) {
        super(source);
        this.cliente = cliente;
        this.asunto = asunto;
    }

    public String getCliente() {
        return cliente;
    }

    public String getAsunto() {
        return asunto;
    }
}
