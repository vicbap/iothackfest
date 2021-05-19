package getronics.qiot.edge_service.service.sensor;

import java.io.Serializable;
import java.util.HashMap;

public class Result implements Serializable{
    
    public HashMap<String,Object> result;

    public Result() {
    }

    public Result(HashMap<String,Object> result) {
        this.result = result;
    }

    public HashMap<String,Object> getResult() {
        return this.result;
    }

    public void setResult(HashMap<String,Object> result) {
        this.result = result;
    }

    public Result result(HashMap<String,Object> result) {
        this.result = result;
        return this;
    }

    
    @Override
    public String toString() {
        return "{" +
            " result='" + getResult() + "'" +
            "}";
    }


}