package getronics.qiot.edge_service.service.sensor;
import java.io.Serializable;

public abstract class Sensor implements Serializable{
    private static final long serialVersionUID = 1L;
    protected Integer stationId;

    

    public Integer getStationId(){
        return this.stationId;
    }

    public void setStationId(Integer stId){
        this.stationId = stId;
    }


    public Sensor() {
    }

    public Sensor(Integer stationId) {
        this.stationId = stationId;
    }

    public Sensor stationId(Integer stationId) {
        this.stationId = stationId;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " stationId='" + getStationId() + "'" +
            "}";
    }


}