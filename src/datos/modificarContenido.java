/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import negocio.Tipo;
import negocio.AccionCorreo;
import negocio.Stilo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author CARLOS
 */
public class modificarContenido implements AccionCorreo{
       byte[] modelo = {Tipo.ALFABETO};

    @Override
    public byte[] getModel() {
        return modelo;
    }

    @Override
    public String getHtml(List<String> datos) {
        String id = "";
        Integer idDominio = 0;
        String html = Stilo.stilo
                + "<body>\n"
                + "<div class=\"container\">\n"
                + "    <h1>Modificacion de Datos</h1>\n"
                + "\n"
                + "    <!---------------Confirmacion de Modificacion de Datos--------------->\n"
                + "\n"
                + "    <div class=\"bs-callout bs-callout-primary\">\n"
                + "        <h4>Se ha editado con exito</h4>\n"
                + "        <ul>\n";
        try {
            Conexion conexion = new Conexion();
           String query = "UPDATE contenido set "
                + "descripcion='" + datos.get(1) + "'"
                + "  WHERE id = " + datos.get(0) + " and 'adm' in (select nombre from usuario where pass ='" +datos.get(2) + "' );";
            System.out.println(query);
            Statement consulta;
            Connection con = conexion.getConexion();
            
            consulta = (Statement) con.createStatement();
            consulta.execute(query);
            consulta.close();
                 html += "<li>Se ha Modificado la contenido"+" "+datos.get(1)+" </li>\n";
            
        } catch (SQLException ex) {
            html += ex + "<br>" ;

        }
        html += "        </ul>\n"
                + "    </div>\n"
                + "\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";

        return html;
    }

    @Override
    public String getExample() {
        String html = Stilo.stilo
                + "<body>\n"
                + "<div class=\"container\">\n"
                + "\n"
                + "    <h1>Ayuda</h1>\n"
                + "\n"
                + "    <!---------------Administrar Dominio---------->\n"
                + "\n"
                + "    <div class=\"bs-callout bs-callout-primary\">\n"
                + "        <h4>Administrar Dominio</h4>\n"
                + "        Dentro de las opciones tenemos:\n"
                + "        <ul>\n"
                + "            <li> Registrar  -> registrar_dominio;nombre;ip;cuentaUsuario</li>\n"
                + "            <li> Editar     -> modificar_dominio;nombre;nuevonombre;nuevoip;</li>\n"
                + "            <li> Eliminar   -> eliminar_dominio;nombre;</li>\n"
                + "            <li> Reportes   -> reportes_dominio;reportes;</li>\n"
                + "            <li> Estadisticas   -> estadisticas_dominio;estadisticas;</li>\n"
                + "        </ul>\n"
                + "    </div>\n"
                + "\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";
        return html;
    }
}
