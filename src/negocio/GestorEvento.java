/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import negocio.ListenerCorreo;
import javax.swing.event.EventListenerList;

/**
 *
 * 
 */
public class GestorEvento {

    private static final EventListenerList listadeEscuchadores = new EventListenerList();
    private static final Object lock = new Object();

    public static void dispatchOnReceive(OnReceive evento) {
        synchronized (lock) {
            ListenerCorreo[] listeners = listadeEscuchadores.getListeners(ListenerCorreo.class);
            for (ListenerCorreo listener : listeners) {
                listener.Receive(evento);
            }
        }
    }

    public static void dispatchOnSend(OnSend evento) {
        synchronized (lock) {
            ListenerCorreo[] listenerCorreos = listadeEscuchadores.getListeners(ListenerCorreo.class);
            for (ListenerCorreo listener : listenerCorreos) {
                listener.Send(evento);
            }
        }
    }

    public static void addListener(ListenerCorreo listener) {
        synchronized (lock) {
            listadeEscuchadores.add(ListenerCorreo.class, listener);
        }
    }

    public static void removeListiner(ListenerCorreo listener) {
        synchronized (lock) {
            listadeEscuchadores.remove(ListenerCorreo.class, listener);
        }
    }
}
