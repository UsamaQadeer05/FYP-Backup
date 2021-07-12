package com.usama.fyp_phr_android.Medication.Model;

public class Medication {
    int um_id;
    int u_id;
    int m_id;
    int um_dosage;
    String um_StartDate;
    String um_EndDate;
    String lastUpdated;
    int d_id;

    public Medication() {
    }

    public Medication(int um_id, int u_id, int m_id, int um_dosage, String um_StartDate, String um_EndDate, String lastUpdated, int d_id) {
        this.um_id = um_id;
        this.u_id = u_id;
        this.m_id = m_id;
        this.um_dosage = um_dosage;
        this.um_StartDate = um_StartDate;
        this.um_EndDate = um_EndDate;
        this.lastUpdated = lastUpdated;
        this.d_id = d_id;
    }

    public int getUm_id() {
        return um_id;
    }

    public void setUm_id(int um_id) {
        this.um_id = um_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int getUm_dosage() {
        return um_dosage;
    }

    public void setUm_dosage(int um_dosage) {
        this.um_dosage = um_dosage;
    }

    public String getUm_StartDate() {
        return um_StartDate;
    }

    public void setUm_StartDate(String um_StartDate) {
        this.um_StartDate = um_StartDate;
    }

    public String getUm_EndDate() {
        return um_EndDate;
    }

    public void setUm_EndDate(String um_EndDate) {
        this.um_EndDate = um_EndDate;
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
