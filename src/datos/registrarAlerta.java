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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author CARLOS
 */
public class registrarAlerta implements AccionCorreo {
       byte[] modelo = {Tipo.ALFABETO};

    @Override
    public byte[] getModel() {
        return modelo;
    }

    @Override
    public String getHtml(List<String> datos) {
        ResultSet setGrupo = null;
        Statement statementGrupo = null;
        String idGrupo = "";
        String html = Stilo.stilo
                + "<body>\n"
                + "<div class=\"container\">\n"
                + "    <h1>Registro de Datos</h1>\n"
                + "\n"
                + "    <!---------------Confirmacion de Registro--------------->\n"
                + "\n"
                + "    <div class=\"bs-callout bs-callout-primary\">\n"
                + "        <h4>Se ha registrado con exito</h4>\n"
                + "        <ul>\n";
        try {
            Conexion conexion = new Conexion();
            String query = "INSERT INTO alerta ( diaVence, descripcion)\n" +
                           "VALUES ("+datos.get(0)+" ,' "+datos.get(1)+"');";
   
            Statement consulta;
            Connection con = conexion.getConexion();
        
            consulta = (Statement) con.createStatement();
            consulta.execute(query);
            consulta.close();
            
        
            html += "<li>Se ha registrado la Alerta "+" "+datos.get(1)+"</li>\n";

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
                + "    <!---------------Administrar Usuario---------->\n"
                + "\n"
                + "    <div class=\"bs-callout bs-callout-primary\">\n"
                + "        <h4>Administrar Usuario</h4>\n"
                + "        Dentro de las opciones tenemos:\n"
                + "        <ul>\n"
                + "            <li>Registrar -> registrar_usuario;cuentaUsuario;password;</li>\n"
                + "            <li>Editar -> modificar_usuario;cuentaUsuario;cuentaUsusarioM;password;passwordM;</li>\n"
                + "            <li>Eliminar -> eliminar_usuario;cuentaUsuario;password;</li>\n"
                + "        </ul>\n"
                + "    </div>\n"
                + "\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";
        return html;
    }
}
