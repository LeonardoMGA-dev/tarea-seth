import core.Cache;
import empleado.service.EmpleadoService;

public class Main {

    private static final Cache CACHE = new Cache();
    private static final EmpleadoService EMPLEADO_SERVICE = new EmpleadoService(CACHE);

    public static void main(String[] args) {

        // Programa principal tiene un EmpleadoService como dependencia
        ProgramaPrincipal programaPrincipal = new ProgramaPrincipal(EMPLEADO_SERVICE);
        // Empieza el programa
        programaPrincipal.start();

    }

}
