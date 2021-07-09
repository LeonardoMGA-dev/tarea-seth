import empleado.ContactoEmpleado;
import empleado.DireccionEmpleado;
import empleado.Empleado;
import empleado.service.EmpleadoService;
import utils.SuperScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramaPrincipal {

    private EmpleadoService empleadoService;
    private boolean isRunning;
    private SuperScanner superScanner;
    private final static int SAVE_USER_OPTION = 1;
    private final static int GET_USER_OPTION = 2;
    private final static int STOP_PROGRAM_OPTION = 3;

    public ProgramaPrincipal(EmpleadoService empleadoService){
        this.empleadoService = empleadoService;
        isRunning = true;
        superScanner = new SuperScanner(new Scanner(System.in));
    }

    public void start() {
        do {
            int option = requestOption();
            switch (option){
                case SAVE_USER_OPTION: {
                    requestEmpleado();
                    break;
                }
                case GET_USER_OPTION: {
                    showUserFromCache();
                    break;
                }
                case STOP_PROGRAM_OPTION: {
                    stopProgram();
                    break;
                }
            }
        } while (isRunning);
    }

    private int requestOption(){
        System.out.println("¿Que quieres hacer?");
        System.out.println("-----------------------------");
        System.out.println("1) Guardar usuario");
        System.out.println("2) Obtener usuario guardado");
        System.out.println("3) Cerrar programa");
        System.out.println("----------------------");
        return superScanner.nextInt(
                "Opcion: ",
                "Opcion invalida",
                SAVE_USER_OPTION, GET_USER_OPTION, STOP_PROGRAM_OPTION );
    }

    private void requestEmpleado(){
        boolean stillRequesting;
        do{
            Empleado empleado = new Empleado();
            empleado.setNombre(superScanner.nextLine("Nombre: "));
            empleado.setApellidoPaterno(superScanner.nextLine("Apellido Paterno: "));
            empleado.setApellidoMaterno(superScanner.nextLine("Apellido Materno: "));
            empleado.setFechaDeNacimiento(superScanner.nextLine("Fecha de nacimiento: "));
            System.out.println("Direccion------");
            empleado.setDireccionesEmpleado(requestDireccionesEmpleado());
            System.out.println("Contacto--------");
            ContactoEmpleado contactoEmpleado = new ContactoEmpleado();
            fillContactoEmpleado(contactoEmpleado);
            empleado.setContactoEmpleado(contactoEmpleado);
            empleadoService.saveEmpleadoInCache(empleado);
            stillRequesting = false;
        }while (stillRequesting);
    }

    private void showUserFromCache(){
        try {
            Empleado empleado = empleadoService.getEmpleadoFromCache();
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Apellido Paterno: " + empleado.getApellidoPaterno());
            System.out.println("Apellido Materno: " + empleado.getApellidoMaterno());
            System.out.println("Fecha de nacimiento: " + empleado.getFechaDeNacimiento());
            System.out.println("--------Direcciones----------");
            empleado.getDireccionesEmpleado().forEach(direccionEmpleado -> {
                System.out.println("Calle: " + direccionEmpleado.getCalle());
                System.out.println("Numero : #" + direccionEmpleado.getNumero());
                System.out.println("Colonia: " + direccionEmpleado.getColonia());
                System.out.println("Ciudad: " + direccionEmpleado.getCiudad());
                System.out.println("Estado: " + direccionEmpleado.getEstado());
                System.out.println("------------------------------------------");
            });
            System.out.println("---------Contacto--------------");
            empleado.getContactoEmpleado().getCorreos().forEach( correo -> {
                System.out.println("Correo: " + correo);
            });
        } catch (Exception e) {
            System.out.println("No hay un empleado registrado.");
        }
    }

    private List<DireccionEmpleado> requestDireccionesEmpleado(){
        List<DireccionEmpleado> empleados = new ArrayList<>();
        boolean stillRequestingInfo = true;
        do{
            DireccionEmpleado direccionEmpleado = new DireccionEmpleado();
            direccionEmpleado.setCalle(superScanner.nextLine("Calle: "));
            direccionEmpleado.setNumero(superScanner.nextInt("Numero: ", "Dato invalido"));
            direccionEmpleado.setColonia(superScanner.nextLine("Colonia: "));
            direccionEmpleado.setCiudad(superScanner.nextLine("Ciudad: "));
            direccionEmpleado.setEstado(superScanner.nextLine("Estado: "));
            empleados.add(direccionEmpleado);
            char option = superScanner
                    .nextChar("Ingresar otra direccion más? S/N: ", "Ingresa un valor valido",
                            's', 'S', 'n', 'N' );
            if(option == 'n' || option == 'N'){
                stillRequestingInfo = false;
            }
        } while (stillRequestingInfo);
        return empleados;
    }

    private void fillContactoEmpleado(ContactoEmpleado contactoEmpleado){
        boolean stillRequestingContactInfo = true;
        do{
            contactoEmpleado.getCorreos().add(superScanner.nextLine("Email: "));
            char option = superScanner
                    .nextChar(
                            "Ingresar otro email? S/N: ",
                            "Ingresa un valor valido",
                            's', 'S', 'n', 'N'
                    );
            if(option == 'n' || option == 'N'){
                stillRequestingContactInfo = false;
            }
        }while (stillRequestingContactInfo);
    }

    private void stopProgram(){
        isRunning = false;
    }

}
