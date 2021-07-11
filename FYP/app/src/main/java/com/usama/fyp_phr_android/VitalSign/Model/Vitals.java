package com.usama.fyp_phr_android.VitalSign.Model;

public class Vitals {
    int uvi_id;
    int u_id;
    String uvi_temperature;
    String uvi_pulserate;
    String uvi_respirationrate;
    String uvi_bloodpressure;
    String uvi_weight;
    String uvi_height;
    String lastUpdated;

    public Vitals() {
    }

    public Vitals(int uvi_id, int u_id, String uvi_temperature, String uvi_pulserate, String uvi_respirationrate, String uvi_bloodpressure, String uvi_weight, String uvi_height, String lastUpdated) {
        this.uvi_id = uvi_id;
        this.u_id = u_id;
        this.uvi_temperature = uvi_temperature;
        this.uvi_pulserate = uvi_pulserate;
        this.uvi_respirationrate = uvi_respirationrate;
        this.uvi_bloodpressure = uvi_bloodpressure;
        this.uvi_weight = uvi_weight;
        this.uvi_height = uvi_height;
        this.lastUpdated = lastUpdated;
    }

    public int getUvi_id() {
        return uvi_id;
    }

    public void setUvi_id(int uvi_id) {
        this.uvi_id = uvi_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getUvi_temperature() {
        return uvi_temperature;
    }

    public void setUvi_temperature(String uvi_temperature) {
        this.uvi_temperature = uvi_temperature;
    }

    public String getUvi_pulserate() {
        return uvi_pulserate;
    }

    public void setUvi_pulserate(String uvi_pulserate) {
        this.uvi_pulserate = uvi_pulserate;
    }

    public String getUvi_respirationrate() {
        return uvi_respirationrate;
    }

    public void setUvi_respirationrate(String uvi_respirationrate) {
        this.uvi_respirationrate = uvi_respirationrate;
    }

    public String getUvi_bloodpressure() {
        return uvi_bloodpressure;
    }

    public void setUvi_bloodpressure(String uvi_bloodpressure) {
        this.uvi_bloodpressure = uvi_bloodpressure;
    }

    public String getUvi_weight() {
        return uvi_weight;
    }

    public void setUvi_weight(String uvi_weight) {
        this.uvi_weight = uvi_weight;
    }

    public String getUvi_height() {
        return uvi_height;
    }

    public void setUvi_height(String uvi_height) {
        this.uvi_height = uvi_height;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
