package empleado.service;

import core.Cache;
import empleado.repository.EmpleadoRepository;
import empleado.Empleado;

public class EmpleadoService {
    private Cache cache;
    private EmpleadoRepository empleadoRepository;

    public EmpleadoService(Cache cache, EmpleadoRepository empleadoRepository) {
        this.cache = cache;
        this.empleadoRepository = empleadoRepository;
    }

    public void saveEmpleadoInCache(Empleado empleado){
        cache.setEmpleado(empleado);
    }

    public void saveEmpleadoInDB(Empleado empleado){
        empleadoRepository.add(empleado);
    }

    public Empleado getEmpleadoFromCache(String id) {
        return cache.getEmpleado(id);
    }

    public Empleado getEmpleadoFromDB(String id){
        return empleadoRepository.getById(id);
    }

    public Empleado getEmpleado(String id) {
        Empleado empleadoFromCache = getEmpleadoFromCache(id);
        if(empleadoFromCache != null){
            return empleadoFromCache;
        }else {
            Empleado empleadoFromDB = getEmpleadoFromDB(id);
            saveEmpleadoInCache(empleadoFromDB);
            return empleadoFromDB;
        }
    }

}
