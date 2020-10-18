/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;



/**
 *
 * @author BIGZERO
 */
public class Movimiento {
    private String cuencodigo;
    private int movinumero;
    private String movifecha;
    private String emplcodigo;
    private String tipocodigo;
    private float moviimporte;
    private String cuenreferencia;

    public Movimiento() {
    }

    public Movimiento(String cuencodigo, int movinumero, String movifecha, String emplcodigo, String tipocodigo, float moviimporte, String cuenreferencia) {
        this.cuencodigo = cuencodigo;
        this.movinumero = movinumero;
        this.movifecha = movifecha;
        this.emplcodigo = emplcodigo;
        this.tipocodigo = tipocodigo;
        this.moviimporte = moviimporte;
        this.cuenreferencia = cuenreferencia;
    }

    public String getCuencodigo() {
        return cuencodigo;
    }

    public void setCuencodigo(String cuencodigo) {
        this.cuencodigo = cuencodigo;
    }

    public int getMovinumero() {
        return movinumero;
    }

    public void setMovinumero(int movinumero) {
        this.movinumero = movinumero;
    }

    public String getMovifecha() {
        return movifecha;
    }

    public void setMovifecha(String movifecha) {
        this.movifecha = movifecha;
    }

    public String getEmplcodigo() {
        return emplcodigo;
    }

    public void setEmplcodigo(String emplcodigo) {
        this.emplcodigo = emplcodigo;
    }

    public String getTipocodigo() {
        return tipocodigo;
    }

    public void setTipocodigo(String tipocodigo) {
        this.tipocodigo = tipocodigo;
    }

    public float getMoviimporte() {
        return moviimporte;
    }

    public void setMoviimporte(float moviimporte) {
        this.moviimporte = moviimporte;
    }

    public String getCuenreferencia() {
        return cuenreferencia;
    }

    public void setCuenreferencia(String cuenreferencia) {
        this.cuenreferencia = cuenreferencia;
    }

}
