import empleado.Empleado;
import empleado.service.EmpleadoService;
import utils.SuperScanner;

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
                    Empleado empleado = new Empleado().capture(superScanner);
                    empleadoService.saveEmpleadoInDB(empleado);
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
            System.out.println("=====================================");
        } while (isRunning);
    }

    private int requestOption(){
        System.out.println("¿Que quieres hacer?");
        System.out.println("-----------------------------");
        System.out.println("1) Guardar usuario");
        System.out.println("2) Consultar usuario");
        System.out.println("3) Cerrar programa");
        System.out.println("----------------------");
        return superScanner.nextInt(
                "Opcion: ",
                "Opcion invalida",
                SAVE_USER_OPTION, GET_USER_OPTION, STOP_PROGRAM_OPTION );
    }

    private void showUserFromCache(){
        try {
            String id = superScanner.nextLine("Id de empleado: ");
            Empleado empleado = empleadoService.getEmpleado(id);
            if(empleado == null){
                System.out.println("No se encontró el usuario...");
                return;
            }
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

    private void stopProgram(){
        isRunning = false;
    }

}
