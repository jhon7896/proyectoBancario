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
import java.util.ArrayList;

/**
 *
 * @author ZERO
 */
public class MovimientoDAO {
    private Connection cnn = null;
    private ResultSet rs=null;

    private static MovimientoDAO instancia;

    public static MovimientoDAO getInstancia()
    {
        if(instancia==null)
            instancia=new MovimientoDAO();
        return instancia;
    }

    public void insertar(String cuencodigo, int movinumero, String movifecha, String emplcodigo,
            String tipocodigo, float moviimporte, String cuenreferencia) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        try {
            ps = cnn.prepareStatement("INSERT INTO movimiento " +
                    "(cuencodigo" +
                    ", movinumero" +
                    ", movifecha" +
                    ", emplcodigo" +
                    ", tipocodigo" +
                    ", moviimporte" +
                    ", cuenreferencia)" +
                    "VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, cuencodigo);
            ps.setInt(2, movinumero);
            ps.setString(3, movifecha);
            ps.setString(4, emplcodigo);
            ps.setString(5, tipocodigo);
            ps.setFloat(6, moviimporte);
            ps.setString(7, cuenreferencia);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }
    
//    public String numeroMovimiento() throws SQLException{
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        String serie="";
//        try {
//            ps = cnn.prepareStatement("call Generar_Numero_Movimiento() ");
//            rs=ps.executeQuery();
//            while (rs.next()) {
//                serie=rs.getString(1);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//        return serie;
//    }


//    public Cliente buscarCliente(String dni) throws SQLException {
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        Cliente cli=null;
//        try {
//            ps = cnn.prepareStatement("SELECT * FROM cliente " +
//                   "WHERE dni=?");
//            ps.setString(1, dni);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                String apellidos=rs.getString("apellidos");
//                String nombres=rs.getString("nombres");
//                String telefono=rs.getString("telefono");
//                String direccion=rs.getString("direccion");
//
//                cli= new Cliente(dni,apellidos,nombres,telefono,direccion);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//        return cli;
//    }
//
//    public void actualizar(String dni, String apellidos, String nombres, String telefono, String direccion) throws SQLException {
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        try {
//            ps = cnn.prepareStatement("UPDATE cliente " +
//                    "SET apellidos = ?, " +
//                    "nombres = ?, " +
//                    "telefono = ?, " +
//                    "direccion = ? " +
//                    "WHERE dni=?");
//            ps.setString(5, dni);
//            ps.setString(1, apellidos);
//            ps.setString(2, nombres);
//            ps.setString(3, telefono);
//            ps.setString(4, direccion);
//
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//    }
//
//    public void eliminar(String dni) throws SQLException {
//
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        try {
//            ps = cnn.prepareStatement("DELETE FROM cliente " +
//                   "WHERE dni=?");
//            ps.setString(1, dni);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//
//    }
//
//    public ArrayList<Cliente> mostrarClientes() throws SQLException {
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        ArrayList<Cliente> lista = new ArrayList<Cliente>();
//        try {
//            ps=cnn.prepareStatement("SELECT * FROM cliente");
//            rs=ps.executeQuery();
//            while (rs.next()) {
//                String dni=rs.getString("dni");
//                String apellidos=rs.getString("apellidos");
//                String nombres=rs.getString("nombres");
//                String telefono=rs.getString("telefono");
//                String direccion=rs.getString("direccion");
//                Cliente cli= new Cliente(dni,apellidos,nombres,telefono,direccion);
//                lista.add(cli);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//        return lista;
//    }
//    
//    public ArrayList<Cliente> buscarPorApellidos(String ape) throws SQLException {
//        cnn = Conexion.getInstancia().miConexion();
//        PreparedStatement ps=null;
//        ArrayList<Cliente> lista = new ArrayList<Cliente>();
//        try {
//            ps=cnn.prepareStatement("SELECT * FROM cliente where apellidos like ?");
//            ps.setString(1, ape+"%");
//            rs=ps.executeQuery();
//            while (rs.next()) {
//                String dni=rs.getString("dni");
//                String apellidos=rs.getString("apellidos");
//                String nombres=rs.getString("nombres");
//                String telefono=rs.getString("telefono");
//                String direccion=rs.getString("direccion");
//                Cliente cli= new Cliente(dni,apellidos,nombres,telefono,direccion);
//                lista.add(cli);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        } finally {
//            cnn.close();
//            ps.close();
//        }
//        return lista;
//    }
}
