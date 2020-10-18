/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import formularios.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Empleado;

/**
 *
 * @author BIGZERO
 */
public class EmpleadoDAO {
    private Connection cnn = null;
    private ResultSet rs = null;

    private static EmpleadoDAO instancia;

    public static EmpleadoDAO getInstancia() {
        if (instancia == null) {
            instancia = new EmpleadoDAO();
        }
        return instancia;
    }

    public void insertar(String emplcodigo, String emplpaterno, String emplmaterno, 
            String emplnombre, String emplciudad, String empldireccion, String emplusuario, 
            String emplclave) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call REGISTRAR_EMPLEADO (?,?,?,?,?,?,?)");
//            ps.setString(1, emplcodigo);
            ps.setString(1, emplpaterno);
            ps.setString(2, emplmaterno);
            ps.setString(3, emplnombre);
            ps.setString(4, emplciudad);
            ps.setString(5, empldireccion);
            ps.setString(6, emplusuario);
            ps.setString(7, emplclave);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }
    
    public String codigoEmpleado() throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        String serie="";
        try {
            ps = cnn.prepareStatement("call Generar_Codigo_Empleado() ");
            rs=ps.executeQuery();
            while (rs.next()) {
                serie=rs.getString(1);
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

    public Empleado buscarEmpleado(String emplcodigo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Empleado emp = null;
        try {
            ps = cnn.prepareStatement("call Buscar_Empleado(?)");
            ps.setString(1, emplcodigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String emplpaterno = rs.getString("emplpaterno");
                String emplmaterno = rs.getString("emplmaterno");
                String emplnombre = rs.getString("emplnombre");
                String emplciudad = rs.getString("emplciudad");
                String empldireccion = rs.getString("empldireccion");
                String emplusuario = rs.getString("emplusuario");
                String emplclave = rs.getString("emplclave");

                emp = new Empleado(emplcodigo, emplpaterno, emplmaterno, emplnombre, emplciudad, 
                        empldireccion, emplusuario, emplclave);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return emp;
    }

    public void actualizar(String emplcodigo, String emplpaterno, String emplmaterno, 
            String emplnombre, String emplciudad, String empldireccion, 
            String emplusuario, String emplclave) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call Actualizar_Empleado(?,?,?,?,?,?,?,?)");
            ps.setString(8, emplcodigo);
            ps.setString(1, emplpaterno);
            ps.setString(2, emplmaterno);
            ps.setString(3, emplnombre);
            ps.setString(4, emplciudad);
            ps.setString(5, empldireccion);
            ps.setString(6, emplusuario);
            ps.setString(7, emplclave);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public void eliminar(String codigo) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call Eliminar_Empleado(?)");
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }

    }

    public ArrayList<Empleado> mostrarEmpleados() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try {
            ps = cnn.prepareStatement("SELECT * FROM Empleado");
            rs = ps.executeQuery();
            while (rs.next()) {
                String emplcodigo = rs.getString("emplcodigo");
                String emplpaterno = rs.getString("emplpaterno");
                String emplmaterno = rs.getString("emplmaterno");
                String emplnombre = rs.getString("emplnombre");
                String emplciudad = rs.getString("emplciudad");
                String empldireccion = rs.getString("empldireccion");
                String emplusuario = rs.getString("emplusuario");
                String emplclave = rs.getString("emplclave");
                Empleado emp = new Empleado(emplcodigo, emplpaterno, emplmaterno, emplnombre, emplciudad, 
                        empldireccion, emplusuario, emplclave);
                lista.add(emp);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
    
    public ArrayList<Empleado> buscarPorApellidos(String ape) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try {
            ps=cnn.prepareStatement("SELECT * FROM empleado where emplpaterno like ?");
            ps.setString(1, ape+"%");
            rs=ps.executeQuery();
            while (rs.next()) {
                String emplcodigo = rs.getString("emplcodigo");
                String emplpaterno = rs.getString("emplpaterno");
                String emplmaterno = rs.getString("emplmaterno");
                String emplnombre = rs.getString("emplnombre");
                String emplciudad = rs.getString("emplciudad");
                String empldireccion = rs.getString("empldireccion");
                String emplusuario = rs.getString("emplusuario");
                String emplclave = rs.getString("emplclave");
                Empleado emp = new Empleado(emplcodigo, emplpaterno, emplmaterno, emplnombre, emplciudad, 
                        empldireccion, emplusuario, emplclave);
                lista.add(emp);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
