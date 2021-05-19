package getronics.qiot.edge_service.rest.client.registration;


import java.io.Serializable;
import org.eclipse.microprofile.config.ConfigProvider;

public class Station implements Serializable  {

    private Integer id;
    private String serial;
    private String name;
    private Double longitude;
    private Double latitude;
    public Boolean active;

    public Station() {
        this.name = ConfigProvider.getConfig().getValue("name.sensor", String.class);
        this.longitude = ConfigProvider.getConfig().getValue("longitude.sensor", Double.class);
        this.latitude = ConfigProvider.getConfig().getValue("latitude.sensor", Double.class);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return this.serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}