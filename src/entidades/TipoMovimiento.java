/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.   
 */
package entidades;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author BIGZERO
 */
public class TipoMovimiento {
    private Connection cnn = null;
    private ResultSet rs = null;
    private String tipocodigo;
    private String tipodescripcion;
    private String tipoaccion;
    private String tipoestado;

    public TipoMovimiento() {
    }

    public TipoMovimiento(String tipocodigo, String tipodescripcion, String tipoaccion, String tipoestado) {
        this.tipocodigo = tipocodigo;
        this.tipodescripcion = tipodescripcion;
        this.tipoaccion = tipoaccion;
        this.tipoestado = tipoestado;
    }

    public TipoMovimiento(String tipocodigo, String tipodescripcion) {
        this.tipocodigo = tipocodigo;
        this.tipodescripcion = tipodescripcion;
    }

    public String getTipocodigo() {
        return tipocodigo;
    }

    public void setTipocodigo(String tipocodigo) {
        this.tipocodigo = tipocodigo;
    }

    public String getTipodescripcion() {
        return tipodescripcion;
    }

    public void setTipodescripcion(String tipodescripcion) {
        this.tipodescripcion = tipodescripcion;
    }

    public String getTipoaccion() {
        return tipoaccion;
    }

    public void setTipoaccion(String tipoaccion) {
        this.tipoaccion = tipoaccion;
    }

    public String getTipoestado() {
        return tipoestado;
    }

    public void setTipoestado(String tipoestado) {
        this.tipoestado = tipoestado;
    }
    
    public void llenarComboTipoMovimiento(JComboBox<TipoMovimiento> cboTipoMovimiento) {
        PreparedStatement ps = null;
        cnn = Conexion.getInstancia().miConexion();
        try {
            ps = cnn.prepareStatement("Select * from tipomovimiento");
            rs = ps.executeQuery();
            while (rs.next()) {
                cboTipoMovimiento.addItem(new TipoMovimiento(
                        rs.getString("tipocodigo"), rs.getString("tipodescripcion")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return tipodescripcion;
    }
    
}
