package com.usama.fyp_phr_android.Vaccination.Model;

public class AllVaccination {
    int v_id;
    String v_name;
    String v_company;

    public AllVaccination() {
    }

    public AllVaccination(int v_id, String v_name, String v_company) {
        this.v_id = v_id;
        this.v_name = v_name;
        this.v_company = v_company;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getV_company() {
        return v_company;
    }

    public void setV_company(String v_company) {
        this.v_company = v_company;
    }

}
