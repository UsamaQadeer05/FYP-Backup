package com.usama.fyp_phr_android.Allergy.Model;

public class Allergy {
    int ua_id;
    int u_id;
    int a_id;
    String ua_Level;
    String ua_StartDate;
    String lastUpdated;

    public Allergy() {
    }

    public Allergy(int ua_id, int u_id, int a_id, String ua_Level, String ua_StartDate, String lastUpdated) {
        this.ua_id = ua_id;
        this.u_id = u_id;
        this.a_id = a_id;
        this.ua_Level = ua_Level;
        this.ua_StartDate = ua_StartDate;
        this.lastUpdated = lastUpdated;
    }

    public int getUa_id() {
        return ua_id;
    }

    public void setUa_id(int ua_id) {
        this.ua_id = ua_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getUa_Level() {
        return ua_Level;
    }

    public void setUa_Level(String ua_Level) {
        this.ua_Level = ua_Level;
    }

    public String getUa_StartDate() {
        return ua_StartDate;
    }

    public void setUa_StartDate(String ua_StartDate) {
        this.ua_StartDate = ua_StartDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
