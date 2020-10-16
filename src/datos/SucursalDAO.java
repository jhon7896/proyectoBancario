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
import entidades.Sucursal;

/**
 *
 * @author BIGZERO
 */
public class SucursalDAO {
    private Connection cnn = null;
    private ResultSet rs = null;

    private static SucursalDAO instancia;

    public static SucursalDAO getInstancia() {
        if (instancia == null) {
            instancia = new SucursalDAO();
        }
        return instancia;
    }

    public void insertar(String sucucodigo, String sucunombre, String sucuciudad, 
            String sucudireccion, int sucucontcuenta) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call REGISTRAR_SUCURSAL (?,?,?,?)");
            ps.setString(1, sucunombre);
            ps.setString(2, sucuciudad);
            ps.setString(3, sucudireccion);
            ps.setInt(4, sucucontcuenta);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }
    
    public String codigoSucursal() throws SQLException{
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps=null;
        String serie="";
        try {
            ps = cnn.prepareStatement("call Generar_Codigo_Sucursal() ");
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

    public Sucursal buscarSucursal(String sucucodigo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Sucursal emp = null;
        try {
            ps = cnn.prepareStatement("call Buscar_Sucursal(?)");
            ps.setString(1, sucucodigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sucunombre = rs.getString("sucunombre");
                String sucuciudad = rs.getString("sucuciudad");
                String sucudireccion = rs.getString("sucudireccion");
                int sucucontcuenta = rs.getInt("sucucontcuenta");

                emp = new Sucursal(sucucodigo,sucunombre, sucuciudad, sucudireccion, sucucontcuenta);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return emp;
    }

    public void actualizar(String sucucodigo, String sucunombre, String sucuciudad, 
            String sucudireccion, int sucucontcuenta) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("call Actualizar_Sucursal(?,?,?,?,?)");
            ps.setString(5, sucucodigo);
            ps.setString(1, sucunombre);
            ps.setString(2, sucuciudad);
            ps.setString(3, sucudireccion);
            ps.setInt(4, sucucontcuenta);

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
            ps = cnn.prepareStatement("call Eliminar_Sucursal(?)");
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }

    }

    public ArrayList<Sucursal> mostrarSucursales() throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Sucursal> lista = new ArrayList<Sucursal>();
        try {
            ps = cnn.prepareStatement("SELECT * FROM Sucursal");
            rs = ps.executeQuery();
            while (rs.next()) {
                String sucucodigo = rs.getString("sucucodigo");
                String sucunombre = rs.getString("sucunombre");
                String sucuciudad = rs.getString("sucuciudad");
                String sucudireccion = rs.getString("sucudireccion");
                int sucucontcuenta = rs.getInt("sucucontcuenta");
                Sucursal emp = new Sucursal(sucucodigo,sucunombre, sucuciudad, sucudireccion, sucucontcuenta);
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
