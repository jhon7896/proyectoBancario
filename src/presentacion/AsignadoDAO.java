/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Date;
/**
 *
 * @author BIGZERO
 */
public class AsignadoDAO {

    private Connection cnn = null;
    private ResultSet rs = null;

    private static AsignadoDAO instancia;

    public static AsignadoDAO getInstancia() {
        if (instancia == null) {
            instancia = new AsignadoDAO();
        }
        return instancia;
    }

    public void insertar(String asigcodigo, String sucucodigo, String emplcodigo,
            String asigfechaalta, String asigfechabaja) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call REGISTRAR_ASIGNADO (?,?,?,?)");
//            ps.setString(1, cliecodigo);
            ps.setString(1, sucucodigo);
            ps.setString(2, emplcodigo);
            ps.setString(3, asigfechaalta);
            ps.setString(4, asigfechabaja);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public String codigoAsignado() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        String serie = "";
        try {
            ps = cnn.prepareStatement("call Generar_Codigo_Asignado() ");
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
    
    public void actualizar(String asigcodigo, String sucucodigo, String emplcodigo, String asigfechaalta, String asigfechabaja) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call Actualizar_Asignado(?,?,?)");
            ps.setString(5, asigcodigo);
            ps.setString(1, sucucodigo);
            ps.setString(2, emplcodigo);
            ps.setString(3, asigfechaalta);
            ps.setString(4, asigfechabaja);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }
    
//    public void actualizar(String asigcodigo, String sucucodigo, String emplcodigo,
//            String asigfechaalta, String asigfechabaja) throws SQLException {
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        try {
//            ps = cnn.prepareStatement("UPDATE asignado " +
//                    "SET sucucodigo = ?, " +
//                    "emplcodigo = ?, " +
//                    "asigfechaalta = ?, " +
//                    "asigfechabaja = ? " +
//                    "WHERE asigcodigo=?");
//            ps.setString(5, asigcodigo);
//            ps.setString(1, sucucodigo);
//            ps.setString(2, emplcodigo);
//            ps.setString(3, asigfechaalta);
//            ps.setString(4, asigfechabaja);
//
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//    }
    
    public Asignado buscarAsignado(String asigcodigo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        Asignado asig=null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM asignado " +
                   "WHERE asigcodigo=?");
            ps.setString(1, asigcodigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sucucodigo=rs.getString("sucucodigo");
                String emplcodigo=rs.getString("emplcodigo");
                String asigfechaalta=rs.getString("asigfechaalta");
                String asigfechabaja=rs.getString("asigfechabaja");

                asig= new Asignado(asigcodigo,sucucodigo,emplcodigo,asigfechaalta,asigfechabaja);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return asig;
    }
    
    public void eliminar(String asigcodigo) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("DELETE FROM asignado " +
                   "WHERE asigcodigo=?");
            ps.setString(1, asigcodigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }

    }
    
}
