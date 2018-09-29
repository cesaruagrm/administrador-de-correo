/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.modificarDeposito;
import datos.registrarUsuario;
import datos.reporte;
import datos.mostrarDocumento;
import datos.registrarDeposito;
import datos.eliminarDocumento;
import datos.mostrarAlerta;
import datos.modificarContenido;
import datos.mostrarUsuario;
import datos.Ayuda;
import datos.eliminarUsuario;
import datos.eliminarContenido;
import datos.modificarAlerta;
import datos.modificarUsuario;
import datos.registrarDocumento;
import datos.eliminarDeposito;
import datos.eliminarAlerta;
import datos.registrarContenido;
import datos.mostrarDeposito;
import datos.registrarAlerta;
import datos.Estadisticas;
import datos.mostrarContenido;
import datos.mostrarRecursos;
import datos.registrarRecursos;
import java.util.HashMap;

/**
 *
 * @author Lisbeth
 */
public class Gestor {

    private static HashMap<String, AccionCorreo> lista;

    public Gestor() {
        lista = new HashMap<>();
        lista.put("ayuda", new Ayuda());
        
        //usuarios 
         lista.put("registrarUsuario", new registrarUsuario()); 
         lista.put("MostrarUsuario", new mostrarUsuario());
         lista.put("modificarUsuario", new modificarUsuario());
         lista.put("EliminarUsuario", new eliminarUsuario());
         
         // Alertas
         lista.put("registrarAlerta", new registrarAlerta()); 
         lista.put("MostrarAlertas", new mostrarAlerta());
         lista.put("modificarAlerta", new modificarAlerta());
         lista.put("EliminarAlerta", new eliminarAlerta());
         
         //contenido 
         lista.put("registrarContenido", new registrarContenido()); 
         lista.put("MostrarContenido", new mostrarContenido());
         lista.put("modificarContenido", new modificarContenido());
         lista.put("EliminarContenido", new eliminarContenido());
         
         //deposito Digital 
         lista.put("registrarDeposito", new registrarDeposito()); 
         lista.put("MostrarDeposito", new mostrarDeposito());
         lista.put("modificarDeposito", new modificarDeposito());
         lista.put("EliminarDeposito", new eliminarDeposito());
         
          //documentos
         lista.put("registrarDocumento", new registrarDocumento()); 
         lista.put("MostrarDocumento", new mostrarDocumento());
         lista.put("EliminarDocumento", new eliminarDocumento());
         
         
           //recursos
         lista.put("registrarRecursos", new registrarRecursos()); 
        lista.put("MostrarRecursos", new mostrarRecursos());
         

        lista.put("reporte", new reporte());
        lista.put("estadistica", new Estadisticas());
        
  
    }

    public AccionCorreo getAccionMail(String nombre) {
        AccionCorreo accionMail = lista.get(nombre);
        if (accionMail == null) {
            return lista.get("ayuda");
        }
        return accionMail;
    }
     
    
}
