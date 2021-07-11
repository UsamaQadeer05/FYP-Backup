package com.usama.fyp_phr_android.Disease.Model;

public class Disease {
    int ud_id;
    int u_id;
    int d_id;
    String ud_level;
    String ud_Status;
    String ud_StartDate;
    String ud_EndDate;
    String lastUpdated;

    public Disease() {
    }

    public Disease(int ud_id, int u_id, int d_id, String ud_level, String ud_Status, String ud_StartDate, String ud_EndDate, String lastUpdated) {
        this.ud_id = ud_id;
        this.u_id = u_id;
        this.d_id = d_id;
        this.ud_level = ud_level;
        this.ud_Status = ud_Status;
        this.ud_StartDate = ud_StartDate;
        this.ud_EndDate = ud_EndDate;
        this.lastUpdated = lastUpdated;
    }

    public int getUd_id() {
        return ud_id;
    }

    public void setUd_id(int ud_id) {
        this.ud_id = ud_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getUd_level() {
        return ud_level;
    }

    public void setUd_level(String ud_level) {
        this.ud_level = ud_level;
    }

    public String getUd_Status() {
        return ud_Status;
    }

    public void setUd_Status(String ud_Status) {
        this.ud_Status = ud_Status;
    }

    public String getUd_StartDate() {
        return ud_StartDate;
    }

    public void setUd_StartDate(String ud_StartDate) {
        this.ud_StartDate = ud_StartDate;
    }

    public String getUd_EndDate() {
        return ud_EndDate;
    }

    public void setUd_EndDate(String ud_EndDate) {
        this.ud_EndDate = ud_EndDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
