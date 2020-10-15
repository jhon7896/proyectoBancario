/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;


import java.util.Objects;
/**
 *
 * @author ZERO
 */
public class Empleado {
    private String emplcodigo;
    private String emplpaterno;
    private String emplmaterno;
    private String emplnombre;
    private String emplciudad;
    private String empldireccion;
    private String emplusuario;
    private String emplclave;

    public Empleado() {
    }

    public Empleado(String emplcodigo, String emplpaterno, String emplmaterno, String emplnombre, String emplciudad, String empldireccion, String emplusuario, String emplclave) {
        this.emplcodigo = emplcodigo;
        this.emplpaterno = emplpaterno;
        this.emplmaterno = emplmaterno;
        this.emplnombre = emplnombre;
        this.emplciudad = emplciudad;
        this.empldireccion = empldireccion;
        this.emplusuario = emplusuario;
        this.emplclave = emplclave;
    }

    public String getEmplcodigo() {
        return emplcodigo;
    }

    public void setEmplcodigo(String emplcodigo) {
        this.emplcodigo = emplcodigo;
    }

    public String getEmplpaterno() {
        return emplpaterno;
    }

    public void setEmplpaterno(String emplpaterno) {
        this.emplpaterno = emplpaterno;
    }

    public String getEmplmaterno() {
        return emplmaterno;
    }

    public void setEmplmaterno(String emplmaterno) {
        this.emplmaterno = emplmaterno;
    }

    public String getEmplnombre() {
        return emplnombre;
    }

    public void setEmplnombre(String emplnombre) {
        this.emplnombre = emplnombre;
    }

    public String getEmplciudad() {
        return emplciudad;
    }

    public void setEmplciudad(String emplciudad) {
        this.emplciudad = emplciudad;
    }

    public String getEmpldireccion() {
        return empldireccion;
    }

    public void setEmpldireccion(String empldireccion) {
        this.empldireccion = empldireccion;
    }

    public String getEmplusuario() {
        return emplusuario;
    }

    public void setEmplusuario(String emplusuario) {
        this.emplusuario = emplusuario;
    }

    public String getEmplclave() {
        return emplclave;
    }

    public void setEmplclave(String emplclave) {
        this.emplclave = emplclave;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.emplcodigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.emplcodigo, other.emplcodigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado{" + "emplcodigo=" + emplcodigo + ", emplpaterno=" + emplpaterno + ", emplmaterno=" + emplmaterno + ", emplnombre=" + emplnombre + ", emplciudad=" + emplciudad + ", empldireccion=" + empldireccion + ", emplusuario=" + emplusuario + ", emplclave=" + emplclave + '}';
    }
    
}
