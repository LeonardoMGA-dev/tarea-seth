package core;

import empleado.Empleado;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private Map<String, Empleado> data;

    private static Cache instance;

    private Cache(){
        this.data = new HashMap<>();
    }

    public void setEmpleado(Empleado empleado){
        this.data.put(empleado.getId(), empleado);
    }

    public Empleado getEmpleado(String id){
        return data.get(id);
    }

    public static Cache getInstance(){
        if(instance == null ){
            instance = new Cache();
        }
        return instance;
    }

}
