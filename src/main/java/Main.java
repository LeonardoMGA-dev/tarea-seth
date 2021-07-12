import core.Cache;
import empleado.repository.EmpleadoRepository;
import empleado.service.EmpleadoService;

public class Main {

    private static final EmpleadoService EMPLEADO_SERVICE =
            new EmpleadoService(Cache.getInstance(), new EmpleadoRepository());

    public static void main(String[] args) {

        // Programa principal tiene un EmpleadoService como dependencia
        ProgramaPrincipal programaPrincipal = new ProgramaPrincipal(EMPLEADO_SERVICE);
        // Empieza el programa
        programaPrincipal.start();

    }

}
