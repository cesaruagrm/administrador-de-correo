/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import protocolos.LeerCorreo;
import negocio.AccionCorreo;
import negocio.Stilo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS
 */
public class reporte implements AccionCorreo{
     @Override
    public byte[] getModel() {
        return new byte[0];
    }

    @Override
    public String getHtml(List<String> datos) {
        Statement statement = null;
        ResultSet result = null;
        String html = Stilo.stilo
                + "<body>\n"
                + "<div class=\"container\">\n"
                + "<table><thead>";
        try {
            String id = "";
            Conexion cn = new Conexion();
            statement = (Statement) cn.getConexion().createStatement();;
            result = statement.executeQuery("select * from documento ;");
            html += "<tr><th>id</th><th>Titulo</th>" +"<th>editorial</th><th>autor</th><th>ejemplar</th></tr></thead><tbody>";
            while (result.next()) {

                html += "<tr><td>"
                        + result.getString(1) + "</td>";
                html += "<td>"
                        + result.getString(2) + "</td>";
              
                html += "<td>"
                        + result.getString(3) + "</td>";
                html += "<td>"
                        + result.getString(4) + "</td>";
                html += "<td>"
                        + result.getString(5) + "</td>";
            }
            result.close();
        } catch (SQLException ex) {
            html += ex + "<br>" ;
        }
        return html + "</tr></tbody></table>"
                + "</body>"
                + "</html>";
    }

    @Override
    public String getExample() {

        String html = Stilo.stilo
                + "<body>\n"
                + "<div class=\"container\">\n"
                + "\n"
                + "    <h1>Ayuda</h1>\n"
                + "\n"
                + "    <!---------------Reporte---------->\n"
                + "\n"
                + "    <div class=\"bs-callout bs-callout-primary\">\n"
                + "        <h4>Reporte</h4>\n"
                + "        Dentro de las opciones tenemos:\n"
                + "        <ul>\n"
                + "            <li>Reporte -> reporte;reporte;</li>\n"
                + "        </ul>\n"
                + "    </div>\n"
                + "\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";
        return html;
    }

}
