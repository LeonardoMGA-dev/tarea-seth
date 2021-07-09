package empleado;


import utils.PartialCapturable;
import utils.SuperScanner;

import java.util.ArrayList;
import java.util.List;

public class ContactoEmpleado implements PartialCapturable<ContactoEmpleado> {
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

    @Override
    public ContactoEmpleado partialCapture(SuperScanner superScanner) {
        ContactoEmpleado contactoEmpleado = new ContactoEmpleado();
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
        return contactoEmpleado;
    }
}
