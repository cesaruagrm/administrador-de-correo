/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import negocio.Constantes;
import negocio.Stilo;
import java.util.List;
import negocio.AccionCorreo;

/**
 *
 * @author Lisbeth
 */
public class Ayuda implements AccionCorreo {

    private String ayudaAdministrador = Stilo.stilo
            + "<body>\n"
            + "<div class=\"container\">\n"
            + "    <h1>Ayuda</h1>\n"
            + "    <div class=\"panel panel-primary\">\n"
            + "        <div class=\"panel-heading\">Para evitar errores copie la sintaxis de los ejemplos de abajo y pegue en el asunto del email: \"Accion\" -> \"Sintaxis</div>\n"
            + "    </div>\n"
            + "    <!---------------Gestionar Usuario--------------->\n"
            + "    <div class=\"bs-callout bs-callout-primary\">\n"
            + "        <h4>Gestionar Usuario</h4>\n"
            + "        Dentro de las opciones tenemos:\n"
            + "        <ul>\n"
            + "            <li> registrarUsuario:nombre:ci:sexo:direccion:pass</li>\n"
            + "            <li> MostrarUsuario:ci</li>\n"
            + "            <li> modificarUsuario:id:nombre:ci:sexo:direccion:pass</li>\n"
            + "            <li> eliminarUsuario:ci:pass</li>\n"
            + "        </ul>\n"
            + "        </ul>\n"
            + "    </div>\n"
            + "    <!---------------Gestionar Deposito Digital--------------->\n"
            + "    <div class=\"bs-callout bs-callout-success\">\n"
            + "        <h4>Gestionar Deposito Digital</h4>\n"
            + "        Dentro de las opciones tenemos:\n"
            + "        <ul>\n"
            + "            <li> registrarDeposito:descripcion:link</li>\n"
            + "            <li> MostrarDeposito:</li>\n"
            + "            <li> modificarDeposito:id:descripcion:link:pass del administrador</li>\n"
            + "            <li> EliminarDeposito:id:pass del administrador</li>\n"
            + "        </ul>\n"
            + "    </div>\n"
            + "    <!---------------Gestion Contenido--------------->\n"
            + "    <div class=\"bs-callout bs-callout-success\">\n"
            + "        <h4>Gestion Contenido</h4>\n"
            + "        Dentro de las opciones tenemos:\n"
            + "        <ul>\n"
            + "            <li> registrarContenido:descripcion</li>\n"
            + "            <li> MostrarContenido:</li>\n"
            + "            <li> modificarContenido:id:descripcion:pass del administrador</li>\n"
            + "            <li> EliminarContenido:id:pass del administrador</li>\n"
            + "        </ul>\n"
            + "    </div>\n"
            + "    <!---------------Gestion alerta--------------->\n"
            + "    <div class=\"bs-callout bs-callout-success\">\n"
            + "        <h4>Gestion alerta</h4>\n"
            + "        <ul>\n"
            + "            <li> registrarAlerta:diaVencimiento:descripcion</li>\n"
            + "            <li> MostrarAlertas:pass del administrador</li>\n"
            + "            <li> modificarAlerta:id:diaVencimento:descripcion:pass del administrador</li>\n"
            + "            <li> EliminarAlerta:id:pass del administrador</li>\n"
            + "        </ul>\n"
            + "    </div>\n"
            + "    <!---------------Gestionar Documento--------------->\n"
            + "    <div class=\"bs-callout bs-callout-warning\">\n"
            + "        <h4>Gestionar Documento</h4>\n"
            + "        <ul>\n"
            + "            <li> registrarDocumento:titulo:editorial:autor:ejemplar:idcontenido:idalerta:idDeposito</li>\n"
            + "            <li> MostrarDocumento:</li>\n"
            + "            <li> EliminarDocumento:id:pass del administrador</li>\n"
            + "        </ul>\n"
            + "    </div>\n"
            + "    <!---------------Recursos--------------->\n"
            + "    <div class=\"bs-callout bs-callout-info\">\n"
            + "        <h4>Gestin Recursos</h4>\n"
            + "        <ul>\n"
            + "            <li> registrarRecursos:descripcion:id usuario:idDocumento 1:idDocumento 2:... N</li>\n"
            + "            <li> MostrarRecursos:ci usuario</li>\n"
            + "        </ul>\n"
            + "    </div>\n"
                + "    <!---------------Reporte--------------->\n"
            + "    <div class=\"bs-callout bs-callout-default\">\n"
            + "        <h4>Reporte</h4>\n"
            + "        Dentro de las opciones tenemos:\n"
            + "        <ul>\n"
            + "            <li>reporte:</li>\n"
            + "        </ul>\n"
            + "    </div>\n"
            
            
             + "    <div class=\"bs-callout bs-callout-default\">\n"
            + "        <h4>Estadisticas</h4>\n"
            + "        Dentro de las opciones tenemos:\n"
            + "        <ul>\n"
            + "            <li>estadistica:</li>\n"
            + "        </ul>\n"
            + "    </div>\n"
            
            + "</div>\n"
            + "</body>\n"
            + "</html>";
    private String ayudaDeposito = ayudaAdministrador;

    private String ayudaCliente = ayudaDeposito;

    @Override
    public byte[] getModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHtml(List<String> datos) {
        if (isCorreAdministrador(datos)) {
            return ayudaAdministrador;
        } else if (isCorreoDeposito(datos)) {
            return ayudaDeposito;
        } else {
            return ayudaCliente;
        }
    }

    @Override
    public String getExample() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean isCorreAdministrador(List<String> datos) {
        if (datos.get(0).equals(Constantes.CORREO_ADMINISTRADOR + Constantes.DOMINIO)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCorreoDeposito(List<String> datos) {
        if (datos.get(0).equals(Constantes.CORREO_DEPOSITO + Constantes.DOMINIO)) {
            return true;
        } else {
            return false;
        }
    }

}
