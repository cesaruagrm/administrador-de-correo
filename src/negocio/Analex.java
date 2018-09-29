/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Ayuda;
import negocio.Gestor;
import java.util.LinkedList;
import java.util.List;
import negocio.AccionCorreo;

/**
 *
 * @author Lisbeth
 */
public class Analex {

    Gestor gestor;
    Autenticador autenticador;

    public Analex() {
        gestor = new Gestor();
        autenticador = new Autenticador();
    }

    public List<String> Parsear(String string) {
        List<String> datos = new LinkedList<>();
        String token = "";
        for (char caracter : string.toCharArray()) {
            if (caracter != ':') {
                token = token + caracter;
            } else {
                datos.add(token);
                token = "";
            }
        }
        if (token != "") {
            datos.add(token);
        }
        if (datos.size() == 0) {
            datos.add("");
        }
        return datos;
    }

    public String Analizar(String asunto, String cliente) {
        List<String> datos = Parsear(asunto);
        AccionCorreo accion = gestor.getAccionMail(datos.get(0));
        if (accion.getClass() != Ayuda.class) {
            if (autenticador.Verificar(datos, accion.getModel())) {
                datos.remove(0);
                return accion.getHtml(datos);
            } else {
                return accion.getExample();
            }
        }
        return Ayuda(accion, cliente);
    }

    public String Ayuda(AccionCorreo accion, String cliente) {
        List<String> direccion = new LinkedList<>();
        direccion.add(cliente);
       // System.out.println(accion.getHtml(direccion));
        return accion.getHtml(direccion);
    }

}
