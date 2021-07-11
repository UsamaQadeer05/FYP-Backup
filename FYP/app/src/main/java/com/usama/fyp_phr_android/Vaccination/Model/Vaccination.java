package com.usama.fyp_phr_android.Vaccination.Model;

public class Vaccination {
    int uv_id;
    int u_id;
    int v_id;
    String uv_dosage;
    String uv_injecteddate;
    String lastUpdated;

    public Vaccination() {
    }

    public Vaccination(int uv_id, int u_id, int v_id, String uv_dosage, String uv_injecteddate, String lastUpdated) {
        this.uv_id = uv_id;
        this.u_id = u_id;
        this.v_id = v_id;
        this.uv_dosage = uv_dosage;
        this.uv_injecteddate = uv_injecteddate;
        this.lastUpdated = lastUpdated;
    }

    public int getUv_id() {
        return uv_id;
    }

    public void setUv_id(int uv_id) {
        this.uv_id = uv_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public String getUv_dosage() {
        return uv_dosage;
    }

    public void setUv_dosage(String uv_dosage) {
        this.uv_dosage = uv_dosage;
    }

    public String getUv_injecteddate() {
        return uv_injecteddate;
    }

    public void setUv_injecteddate(String uv_injecteddate) {
        this.uv_injecteddate = uv_injecteddate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
