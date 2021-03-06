package empleado;

import utils.Capturable;
import utils.PartialCapturable;
import utils.SuperScanner;

public class DireccionEmpleado implements PartialCapturable<DireccionEmpleado> {
    private String calle;
    private int numero;
    private String colonia;
    private String ciudad;
    private String estado;


    public DireccionEmpleado(){ }

    public DireccionEmpleado(String calle, int numero, String colonia, String ciudad, String estado) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public DireccionEmpleado partialCapture(SuperScanner scanner) {
        setCalle(scanner.nextLine("Calle: "));
        setNumero(scanner.nextInt("Numero: ", "Dato invalido"));
        setColonia(scanner.nextLine("Colonia: "));
        setCiudad(scanner.nextLine("Ciudad: "));
        setEstado(scanner.nextLine("Estado: "));
        return this;
    }
}
