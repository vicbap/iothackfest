package getronics.qiot.edge_service.service.sensor;

public class PollutionResult extends Sensor {

    public Integer PM10;
    public Integer PM10_atm;
    public Integer PM1_0;
    public Integer PM1_0_atm;
    public Integer PM2_5;
    public Integer PM2_5_atm;
    public Integer gt0_3um;
    public Integer gt0_5um;
    public Integer gt10um;
    public Integer gt1_0um;
    public Integer gt2_5um;
    public Integer gt5_0um;
    public String instant;
    
    public PollutionResult(Integer id, Integer PM10,Integer PM10_atm,Integer PM1_0,Integer PM1_0_atm,Integer PM2_5,Integer PM2_5_atm,Integer gt0_3um,Integer gt0_5um,Integer gt10um,Integer gt1_0um,Integer gt2_5um,Integer gt5_0um, String instant){
        this.stationId = id;
        this.PM10 = PM10;
        this.PM10_atm = PM10_atm;
        this.PM1_0 = PM1_0;
        this.PM1_0_atm = PM1_0_atm ;
        this.PM2_5 = PM2_5;
        this.PM2_5_atm = PM2_5_atm;
        this.gt0_3um = gt0_3um;
        this.gt0_5um = gt0_5um;
        this.gt10um = gt10um;
        this.gt1_0um = gt1_0um;
        this.gt2_5um = gt2_5um;
        this.gt5_0um = gt5_0um ;
        this.instant = instant;
    }


    public PollutionResult() {
    }


    

    @Override
    public String toString() {
        return "{" +
            " PM10='" + PM10 + "'" +
            ", PM10_atm='" + PM10_atm + "'" +
            ", PM1_0='" + PM1_0 + "'" +
            ", PM1_0_atm='" + PM1_0_atm + "'" +
            ", PM2_5='" + PM2_5 + "'" +
            ", PM2_5_atm='" + PM2_5_atm + "'" +
            ", gt0_3um='" + gt0_3um + "'" +
            ", gt0_5um='" + gt0_5um + "'" +
            ", gt10um='" + gt10um + "'" +
            ", gt1_0um='" + gt1_0um + "'" +
            ", gt2_5um='" + gt2_5um + "'" +
            ", gt5_0um='" + gt5_0um + "'" +
            ", instant='" + instant + "'" +
            "}";
    }

    
}