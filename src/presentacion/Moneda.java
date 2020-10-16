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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author BIGZERO
 */
public class Moneda {
    private Connection cnn = null;
    private ResultSet rs = null;
    private Statement st = null;
    public String monecodigo;
    public String monedescripcion;

    public Moneda() {
    }

    public Moneda(String monecodigo, String monedescripcion) {
        this.monecodigo = monecodigo;
        this.monedescripcion = monedescripcion;
    }

    public String getMonecodigo() {
        return monecodigo;
    }

    public void setMonecodigo(String monecodigo) {
        this.monecodigo = monecodigo;
    }

    public String getMonedescripcion() {
        return monedescripcion;
    }

    public void setMonedescripcion(String monedescripcion) {
        this.monedescripcion = monedescripcion;
    }
    
    public void llenarComboMoneda(JComboBox<Moneda> cboMoneda) {
        PreparedStatement ps = null;
        cnn = Conexion.getInstancia().miConexion();
        try {
            ps = cnn.prepareStatement("Select * from moneda");
            rs = ps.executeQuery();
            while (rs.next()) {
                cboMoneda.addItem(new Moneda(
                        rs.getString("monecodigo"), rs.getString("monedescripcion")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return monedescripcion;
    }
}
