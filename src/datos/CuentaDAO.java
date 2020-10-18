/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entidades.Cuenta;

/**
 *
 * @author BIGZERO
 */
public class CuentaDAO {
    private Connection cnn = null;
    private ResultSet rs = null;

    private static CuentaDAO instancia;

    public static CuentaDAO getInstancia() {
        if (instancia == null) {
            instancia = new CuentaDAO();
        }
        return instancia;
    }

    public void insertar(String cuencodigo, String monecodigo, String sucucodigo,
            String emplcreacuenta, String cliecodigo, float cuensaldo, String cuenfechacreacion,
            String cuenestado, int cuencontmov, String cuenclave) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call REGISTRAR_CUENTA (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, monecodigo);
            ps.setString(2, sucucodigo);
            ps.setString(3, emplcreacuenta);
            ps.setString(4, cliecodigo);
            ps.setFloat(5, cuensaldo);
            ps.setString(6, cuenfechacreacion);
            ps.setString(7, cuenestado);
            ps.setInt(8, cuencontmov);
            ps.setString(9, cuenclave);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public String codigoCuenta() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        String serie = "";
        try {
            ps = cnn.prepareStatement("call Generar_Codigo_Cuenta() ");
            rs = ps.executeQuery();
            while (rs.next()) {
                serie = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            cnn.close();
            ps.close();
        }
        return serie;
    }
    
    public void actualizar(String cuencodigo, String monecodigo, String sucucodigo,
            String emplcreacuenta, String cliecodigo, float cuensaldo, String cuenfechacreacion,
            String cuenestado, int cuencontmov, String cuenclave) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call Actualizar_CUENTA(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(10, cuencodigo);
            ps.setString(1, monecodigo);
            ps.setString(2, sucucodigo);
            ps.setString(3, emplcreacuenta);
            ps.setString(4, cliecodigo);
            ps.setFloat(5, cuensaldo);
            ps.setString(6, cuenfechacreacion);
            ps.setString(7, cuenestado);
            ps.setInt(8, cuencontmov);
            ps.setString(9, cuenclave);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }
        
    public Cuenta buscarCuenta(String cuencodigo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        Cuenta cuenta=null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM cuenta " +
                   "WHERE cuencodigo=?");
            ps.setString(1, cuencodigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String monecodigo=rs.getString("monecodigo");
                String sucucodigo=rs.getString("sucucodigo");
                String emplcodigo=rs.getString("emplcreacuenta");
                String cliecodigo=rs.getString("cliecodigo");
                float cuensaldo=rs.getFloat("cuensaldo");
                String cuenfechacreacion=rs.getString("cuenfechacreacion");
                String cuenestado=rs.getString("cuenestado");
                int cuencontmov=rs.getInt("cuencontmov");
                String cuenclave=rs.getString("cuenclave");
                

                cuenta= new Cuenta(cuencodigo,monecodigo,sucucodigo,emplcodigo,cliecodigo,cuensaldo,cuenfechacreacion,cuenestado,cuencontmov,cuenclave);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return cuenta;
    }
    
    public void eliminar(String cuencodigo) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("DELETE FROM cuenta " +
                   "WHERE cuencodigo=?");
            ps.setString(1, cuencodigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }

    }
}
