package com.usama.fyp_phr_android.Symptom.Model;

public class Symptom {
    int us_id;
    int u_id;
    int s_id;
    String us_StartDate;
    String us_EndDate;
    String lastUpdated;
    int d_id;

    public Symptom() {
    }

    public Symptom(int us_id, int u_id, int s_id, String us_StartDate, String us_EndDate, String lastUpdated, int d_id) {
        this.us_id = us_id;
        this.u_id = u_id;
        this.s_id = s_id;
        this.us_StartDate = us_StartDate;
        this.us_EndDate = us_EndDate;
        this.lastUpdated = lastUpdated;
        this.d_id = d_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getUs_StartDate() {
        return us_StartDate;
    }

    public void setUs_StartDate(String us_StartDate) {
        this.us_StartDate = us_StartDate;
    }

    public String getUs_EndDate() {
        return us_EndDate;
    }

    public void setUs_EndDate(String us_EndDate) {
        this.us_EndDate = us_EndDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

}
