/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author Lisbeth
 */
public class Conexion {

    private static String cadenaConexion = "jdbc:postgresql://virtual.fcet.uagrm.edu.bo:5432/db_grupo08sa";
//    private static String cadenaConexion = "jdbc:postgresql://mail.ficct.uagrm.edu.bo:5432/db_grupo06sc";
    //private static String cadenaConexion = "jdbc:postgresql://localhost:5432/biblioteca";
    public Connection connection;

    public Connection getConexion() {
        connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(cadenaConexion, "grupo08sa", "grupo08grupo08");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }    
        public LinkedList<String> Lista(String query){
        Connection con = getConexion();
        LinkedList<String> model = new LinkedList<>();
        Statement Consulta;
        ResultSet resultado = null;
        try {
         Consulta = (Statement) con.createStatement();
      resultado = Consulta.executeQuery(query);
            while (resultado.next()) {
                String nombre = resultado.getString(1);
                model.push(nombre);
            }
            Consulta.close();
        } catch (Exception e) {
            System.out.println("no se pudo CARGAR LOS DATOS TABLA Cargo");
        }
        return model;
    }
}
