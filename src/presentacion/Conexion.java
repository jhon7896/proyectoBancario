/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentacion;
import java.sql.*;
/**
 *
 * @author BIGZERO
 *
 */
public class Conexion {
    private static Conexion instancia;
    
    public static Conexion getInstancia()
    {
        if(instancia==null)
            instancia= new Conexion();
        return instancia;
    }

    Conexion() {
    }

    public Connection miConexion() {

        Connection cn =null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Error no se puede cargar el driver:" + e.getMessage());
        }

        try {
            String url = "jdbc:mysql://localhost:3306/eurekabank?useSSL=false";
            String user = "root";
            String password = "12345";
            cn= DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println("Error no se establecer la conexion:" + e.getMessage());
        }
        return cn;
    }
}
