package empleado.repository;

import empleado.Empleado;

import java.util.HashMap;
import java.util.Map;

public class EmpleadoRepository {
    private Map<String, Empleado> db;

    public EmpleadoRepository(){
        db = new HashMap<>();
    }

    public void add(Empleado empleado){
        db.put(empleado.getId(), empleado);
    }

    public Empleado getById(String id){
        try {
            //simular llamada a la base de datos
            System.out.println("Making request....");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return db.get(id);
    }

}
