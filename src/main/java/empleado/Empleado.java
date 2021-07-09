package empleado;

import utils.Capturable;
import utils.PartialCapturable;
import utils.SuperScanner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Empleado implements Capturable<Empleado>, PartialCapturable<Empleado> {

    private ContactoEmpleado contactoEmpleado;
    private List<DireccionEmpleado> direccionesEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaDeNacimiento;

    public Empleado(){ }

    public Empleado(ContactoEmpleado contactoEmpleado, List<DireccionEmpleado> direccionEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String fechaDeNacimiento) {
        this.contactoEmpleado = contactoEmpleado;
        this.direccionesEmpleado = direccionEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public ContactoEmpleado getContactoEmpleado() {
        return contactoEmpleado;
    }

    public Empleado setContactoEmpleado(ContactoEmpleado contactoEmpleado) {
        this.contactoEmpleado = contactoEmpleado;
        return this;
    }

    public List<DireccionEmpleado> getDireccionesEmpleado() {
        return direccionesEmpleado;
    }

    public Empleado setDireccionesEmpleado(List<DireccionEmpleado> direccionEmpleado) {
        this.direccionesEmpleado = direccionEmpleado;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Empleado setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public Empleado setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
        return this;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public Empleado setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
        return this;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public Empleado setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
        return this;
    }

    @Override
    public Empleado partialCapture(SuperScanner superScanner) {
        setNombre(superScanner.nextLine("Nombre: "));
        setApellidoPaterno(superScanner.nextLine("Apellido Paterno: "));
        setApellidoMaterno(superScanner.nextLine("Apellido Materno: "));
        setFechaDeNacimiento(superScanner.nextLine("Fecha de nacimiento: "));
        return this;
    }

    @Override
    public Empleado capture(SuperScanner superScanner) {
        partialCapture(superScanner);
        List<DireccionEmpleado> direcciones = requestDireccionesEmpleado(superScanner);
        setDireccionesEmpleado(direcciones);
        setContactoEmpleado(new ContactoEmpleado().partialCapture(superScanner));
        return this;
    }


    private List<DireccionEmpleado> requestDireccionesEmpleado(SuperScanner superScanner){
        List<DireccionEmpleado> direcciones = new ArrayList<>();
        boolean stillRequestingInfo = true;
        do{
            DireccionEmpleado direccionEmpleado = new DireccionEmpleado().partialCapture(superScanner);
            direcciones.add(direccionEmpleado);
            char option = superScanner
                    .nextChar("Ingresar otra direccion más? S/N: ",
                            "Ingresa un valor valido",
                            's', 'S', 'n', 'N'
                    );
            if(option == 'n' || option == 'N'){
                stillRequestingInfo = false;
            }
        } while (stillRequestingInfo);
        return direcciones;
    }


}
