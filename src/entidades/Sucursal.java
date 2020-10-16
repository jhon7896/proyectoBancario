package entidades;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author BIGZERO
 */
public class Sucursal {
    private Connection cnn = null;
    private ResultSet rs = null;
    private Statement st = null;
    private String sucucodigo;
    private String sucunombre;
    private String sucuciudad;
    private String sucudireccion;
    private int sucucontcuenta;

    public Sucursal() {
    }

    public Sucursal(String sucucodigo, String sucunombre, String sucuciudad, String sucudireccion, int sucucontcuenta) {
        this.sucucodigo = sucucodigo;
        this.sucunombre = sucunombre;
        this.sucuciudad = sucuciudad;
        this.sucudireccion = sucudireccion;
        this.sucucontcuenta = sucucontcuenta;
    }
    
    public Sucursal(String sucucodigo, String sucunombre) {
        this.sucucodigo = sucucodigo;
        this.sucunombre = sucunombre;
    }

    public String getSucucodigo() {
        return sucucodigo;
    }

    public void setSucucodigo(String sucucodigo) {
        this.sucucodigo = sucucodigo;
    }

    public String getSucunombre() {
        return sucunombre;
    }

    public void setSucunombre(String sucunombre) {
        this.sucunombre = sucunombre;
    }

    public String getSucuciudad() {
        return sucuciudad;
    }

    public void setSucuciudad(String sucuciudad) {
        this.sucuciudad = sucuciudad;
    }

    public String getSucudireccion() {
        return sucudireccion;
    }

    public void setSucudireccion(String sucudireccion) {
        this.sucudireccion = sucudireccion;
    }

    public int getSucucontcuenta() {
        return sucucontcuenta;
    }

    public void setSucucontcuenta(int sucucontcuenta) {
        this.sucucontcuenta = sucucontcuenta;
    }
    
    public void llenarComboSucursal(JComboBox<Sucursal> cboSucursal) {
        PreparedStatement ps = null;
        cnn = Conexion.getInstancia().miConexion();
        try {
            ps = cnn.prepareStatement("Select * from Sucursal");
            rs = ps.executeQuery();
            while (rs.next()) {
                cboSucursal.addItem(new Sucursal(
                        rs.getString("sucucodigo"), rs.getString("sucunombre")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return sucunombre;
    }
}
