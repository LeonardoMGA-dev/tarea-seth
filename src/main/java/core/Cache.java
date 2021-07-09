package core;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private Map<String, Object> data;

    public Cache(){
        this.data = new HashMap<>();
    }

    public void setData(String key, Object data){
        this.data.put(key, data);
    }

    public Object getData(String key){
        return data.get(key);
    }

}
