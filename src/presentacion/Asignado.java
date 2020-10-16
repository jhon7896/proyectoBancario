/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;



/**
 *
 * @author BIGZERO
 */
public class Asignado { 
    private String asigcodigo;
    private String sucucodigo;
    private String emplcodigo;
    private String asigfechaalta;
    private String asigfechabaja;

    public Asignado() {
    }

    public Asignado(String asigcodigo, String sucucodigo, String emplcodigo, String asigfechaalta, String asigfechabaja) {
        this.asigcodigo = asigcodigo;
        this.sucucodigo = sucucodigo;
        this.emplcodigo = emplcodigo;
        this.asigfechaalta = asigfechaalta;
        this.asigfechabaja = asigfechabaja;
    }

    public String getAsigcodigo() {
        return asigcodigo;
    }

    public void setAsigcodigo(String asigcodigo) {
        this.asigcodigo = asigcodigo;
    }

    public String getSucucodigo() {
        return sucucodigo;
    }

    public void setSucucodigo(String sucucodigo) {
        this.sucucodigo = sucucodigo;
    }

    public String getEmplcodigo() {
        return emplcodigo;
    }

    public void setEmplcodigo(String emplcodigo) {
        this.emplcodigo = emplcodigo;
    }

    public String getAsigfechaalta() {
        return asigfechaalta;
    }

    public void setAsigfechaalta(String asigfechaalta) {
        this.asigfechaalta = asigfechaalta;
    }

    public String getAsigfechabaja() {
        return asigfechabaja;
    }

    public void setAsigfechabaja(String asigfechabaja) {
        this.asigfechabaja = asigfechabaja;
    }
}
