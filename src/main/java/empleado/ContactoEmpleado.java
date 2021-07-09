package empleado;


import java.util.ArrayList;
import java.util.List;

public class ContactoEmpleado {
    private List<String> correos;

    public ContactoEmpleado(String... correos){
        this.correos = new ArrayList<>();
        for(String correo : correos){
            this.correos.add(correo);
        }
    }

    public List<String> getCorreos() {
        return correos;
    }

    public void setCorreos(List<String> correos) {
        this.correos = correos;
    }
}
