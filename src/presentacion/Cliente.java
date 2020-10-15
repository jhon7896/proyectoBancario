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
public class Cliente {

    private String cliecodigo;
    private String cliepaterno;
    private String cliematerno;
    private String clienombre;
    private String cliedni;
    private String clieciudad;
    private String cliedireccion;
    private String clietelefono;
    private String clieemail;

    public Cliente() {
    }

    public Cliente(String cliecodigo, String cliepaterno, String cliematerno, String clienombre, String cliedni, String clieciudad, String cliedireccion, String clietelefono, String clieemail) {
        this.cliecodigo = cliecodigo;
        this.cliepaterno = cliepaterno;
        this.cliematerno = cliematerno;
        this.clienombre = clienombre;
        this.cliedni = cliedni;
        this.clieciudad = clieciudad;
        this.cliedireccion = cliedireccion;
        this.clietelefono = clietelefono;
        this.clieemail = clieemail;
    }

    public String getCliecodigo() {
        return cliecodigo;
    }

    public void setCliecodigo(String cliecodigo) {
        this.cliecodigo = cliecodigo;
    }

    public String getCliepaterno() {
        return cliepaterno;
    }

    public void setCliepaterno(String cliepaterno) {
        this.cliepaterno = cliepaterno;
    }

    public String getCliematerno() {
        return cliematerno;
    }

    public void setCliematerno(String cliematerno) {
        this.cliematerno = cliematerno;
    }

    public String getClienombre() {
        return clienombre;
    }

    public void setClienombre(String clienombre) {
        this.clienombre = clienombre;
    }

    public String getCliedni() {
        return cliedni;
    }

    public void setCliedni(String cliedni) {
        this.cliedni = cliedni;
    }

    public String getClieciudad() {
        return clieciudad;
    }

    public void setClieciudad(String clieciudad) {
        this.clieciudad = clieciudad;
    }

    public String getCliedireccion() {
        return cliedireccion;
    }

    public void setCliedireccion(String cliedireccion) {
        this.cliedireccion = cliedireccion;
    }

    public String getClietelefono() {
        return clietelefono;
    }

    public void setClietelefono(String clietelefono) {
        this.clietelefono = clietelefono;
    }

    public String getClieemail() {
        return clieemail;
    }

    public void setClieemail(String clieemail) {
        this.clieemail = clieemail;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.cliecodigo);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.cliecodigo, other.cliecodigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente: " + 
                "\nCodigo: " + cliecodigo + 
                "\nApellido Paterno: " + cliepaterno + 
                "\nApellido Materno: " + cliematerno + 
                "\nNombres: " + clienombre + 
                "\nDNI: " + cliedni + 
                "\nCiudad: " + clieciudad + 
                "\nDirección: " + cliedireccion + 
                "\nTelefono: " + clietelefono + 
                "\nEmail: " + clieemail;
    }
    

}
