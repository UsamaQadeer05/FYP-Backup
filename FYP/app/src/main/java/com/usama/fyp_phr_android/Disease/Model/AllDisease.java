package com.usama.fyp_phr_android.Disease.Model;

public class AllDisease {
    int d_id;
    String d_name;

    public AllDisease() {
    }

    public AllDisease(int d_id, String d_name) {
        this.d_id = d_id;
        this.d_name = d_name;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }
}
