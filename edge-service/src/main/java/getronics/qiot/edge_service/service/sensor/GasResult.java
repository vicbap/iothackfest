package getronics.qiot.edge_service.service.sensor;

public class GasResult extends Sensor{
    public Double adc;
    public String instant;
    public Double nh3;
    public Double oxidising;
    public Double reducing;

    public GasResult(Integer id,Double adc, String instant, Double nh3, Double oxidising, Double reducing){
        this.stationId = id;
        this.instant = instant;
        this.nh3 = nh3;
        this.oxidising = oxidising;
        this.reducing = reducing;
        this.adc = adc;
    }


    public GasResult() {
    }


    public Double getAdc() {
        return this.adc;
    }

    public void setAdc(Double adc) {
        this.adc = adc;
    }

    public String getInstant() {
        return this.instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }

    public Double getNh3() {
        return this.nh3;
    }

    public void setNh3(Double nh3) {
        this.nh3 = nh3;
    }

    public Double getOxidising() {
        return this.oxidising;
    }

    public void setOxidising(Double oxidising) {
        this.oxidising = oxidising;
    }

    public Double getReducing() {
        return this.reducing;
    }

    public void setReducing(Double reducing) {
        this.reducing = reducing;
    }



    @Override
    public String toString() {
        return "{" +
            " adc='" + getAdc() + "'" +
            ", instant='" + getInstant() + "'" +
            ", nh3='" + getNh3() + "'" +
            ", oxidising='" + getOxidising() + "'" +
            ", reducing='" + getReducing() + "'" +
            "}";
    }



}