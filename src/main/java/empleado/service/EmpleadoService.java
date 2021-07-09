package empleado.service;

import core.Cache;
import empleado.Empleado;

public class EmpleadoService {
    private Cache cache;
    private static final String CACHE_KEY = "empleado";

    public EmpleadoService(Cache cache) {
        this.cache = cache;
    }

    public void saveEmpleadoInCache(Empleado empleado){
        cache.setData(CACHE_KEY, empleado);
    }

    public Empleado getEmpleadoFromCache(Cache cache) {
        return (Empleado) cache.getData(CACHE_KEY);
    }

    public Empleado getEmpleadoFromCache() {
        return getEmpleadoFromCache(cache);
    }
}
