/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.EventListener;

/**
 *
 * @author Lisbeth
 */
public interface ListenerCorreo extends EventListener {

    public void Receive(OnReceive evento);

    public void Send(OnSend evento);
}
