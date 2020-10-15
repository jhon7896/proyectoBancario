/*
        sucucodigo       CHAR(3) NOT NULL,
	sucunombre       VARCHAR(50) NOT NULL,
	sucuciudad       VARCHAR(30) NOT NULL,
	sucudireccion    VARCHAR(50) NULL,
	sucucontcuenta   INTEGER NOT NULL,
 */
package presentacion;

/**
 *
 * @author BIGZERO
 */
public class Sucursal {
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

    @Override
    public String toString() {
        return "Sucursal{" + "sucucodigo=" + sucucodigo + ", sucunombre=" + sucunombre + ", sucuciudad=" + sucuciudad + ", sucudireccion=" + sucudireccion + ", sucucontcuenta=" + sucucontcuenta + '}';
    }
    
}
