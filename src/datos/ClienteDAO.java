/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import formularios.Conexion;
import java.sql.*;
import java.util.*;
import entidades.Cliente;

/**
 *
 * @author BIGZERO
 */
public class ClienteDAO {

    private Connection cnn = null;
    private ResultSet rs = null;

    private static ClienteDAO instancia;

    public static ClienteDAO getInstancia() {
        if (instancia == null) {
            instancia = new ClienteDAO();
        }
        return instancia;
    }

    public void insertar(String cliecodigo, String cliepaterno, String cliematerno, 
            String clienombre, String cliedni, String clieciudad, String cliedireccion, 
            String clietelefono, String clieemail) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call REGISTRAR_CLIENTE (?,?,?,?,?,?,?,?)");
//            ps.setString(1, cliecodigo);
            ps.setString(1, cliepaterno);
            ps.setString(2, cliematerno);
            ps.setString(3, clienombre);
            ps.setString(4, cliedni);
            ps.setString(5, clieciudad);
            ps.setString(6, cliedireccion);
            ps.setString(7, clietelefono);
            ps.setString(8, clieemail);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }
    
    public String codigoCliente() throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        String serie="";
        try {
            ps = cnn.prepareStatement("call Generar_Codigo_Cliente() ");
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

    public Cliente buscarCliente(String cliecodigo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Cliente cli = null;
        try {
            ps = cnn.prepareStatement("call Buscar_Cliente(?)");
            ps.setString(1, cliecodigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String cliepaterno = rs.getString("cliepaterno");
                String cliematerno = rs.getString("cliematerno");
                String clienombre = rs.getString("clienombre");
                String cliedni = rs.getString("cliedni");
                String clieciudad = rs.getString("clieciudad");
                String cliedireccion = rs.getString("cliedireccion");
                String clietelefono = rs.getString("clietelefono");
                String clieemail = rs.getString("clieemail");

                cli = new Cliente(cliecodigo, cliepaterno, cliematerno, clienombre, cliedni, 
                        clieciudad, cliedireccion, clietelefono, clieemail);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return cli;
    }

    public void actualizar(String cliecodigo, String cliepaterno, String cliematerno, 
            String clienombre, String cliedni, String clieciudad, String cliedireccion, 
            String clietelefono, String clieemail) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call Actualizar_Cliente(?,?,?,?,?,?,?,?,?)");
            ps.setString(9, cliecodigo);
            ps.setString(1, cliepaterno);
            ps.setString(2, cliematerno);
            ps.setString(3, clienombre);
            ps.setString(4, cliedni);
            ps.setString(5, clieciudad);
            ps.setString(6, cliedireccion);
            ps.setString(7, clietelefono);
            ps.setString(8, clieemail);

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
            ps = cnn.prepareStatement("call Eliminar_Cliente(?)");
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }

    }

    public ArrayList<Cliente> mostrarClientes() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try {
            ps = cnn.prepareStatement("call Mostrar_Cliente()");
            rs = ps.executeQuery();
            while (rs.next()) {
                String cliecodigo = rs.getString("cliecodigo");
                String cliepaterno = rs.getString("cliepaterno");
                String cliematerno = rs.getString("cliematerno");
                String clienombre = rs.getString("clienombre");
                String cliedni = rs.getString("cliedni");
                String clieciudad = rs.getString("clieciudad");
                String cliedireccion = rs.getString("cliedireccion");
                String clietelefono = rs.getString("clietelefono");
                String clieemail = rs.getString("clieemail");
                Cliente cli = new Cliente(cliecodigo, cliepaterno, cliematerno, clienombre, cliedni, 
                        clieciudad, cliedireccion, clietelefono, clieemail);
                lista.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }

    public ArrayList<Cliente> mostrarClientesApellidos() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try {
            ps = cnn.prepareStatement("call Mostrar_Clientes_Por_Apellidos()");
            rs = ps.executeQuery();
            while (rs.next()) {
                String cliecodigo = rs.getString("cliecodigo");
                String cliepaterno = rs.getString("cliepaterno");
                String cliematerno = rs.getString("cliematerno");
                String clienombre = rs.getString("clienombre");
                String cliedni = rs.getString("cliedni");
                String clieciudad = rs.getString("clieciudad");
                String cliedireccion = rs.getString("cliedireccion");
                String clietelefono = rs.getString("clietelefono");
                String clieemail = rs.getString("clieemail");
                Cliente cli = new Cliente(cliecodigo, cliepaterno, cliematerno, clienombre, cliedni, 
                        clieciudad, cliedireccion, clietelefono, clieemail);
                lista.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }

    public ArrayList<Cliente> mostrarClientesDNI() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try {
            ps = cnn.prepareStatement("call Mostrar_Clientes_Por_DNI()");
            rs = ps.executeQuery();
            while (rs.next()) {
                String cliecodigo = rs.getString("cliecodigo");
                String cliepaterno = rs.getString("cliepaterno");
                String cliematerno = rs.getString("cliematerno");
                String clienombre = rs.getString("clienombre");
                String cliedni = rs.getString("cliedni");
                String clieciudad = rs.getString("clieciudad");
                String cliedireccion = rs.getString("cliedireccion");
                String clietelefono = rs.getString("clietelefono");
                String clieemail = rs.getString("clieemail");
                Cliente cli = new Cliente(cliecodigo, cliepaterno, cliematerno, clienombre, cliedni, 
                        clieciudad, cliedireccion, clietelefono, clieemail);
                lista.add(cli);
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
