package com.usama.fyp_phr_android.Researcher;

public class DiseaseCases {
    String d_name;
    int DISEASE_COUNT;

    public DiseaseCases(String d_name, int DISEASE_COUNT) {
        this.d_name = d_name;
        this.DISEASE_COUNT = DISEASE_COUNT;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public int getDISEASE_COUNT() {
        return DISEASE_COUNT;
    }

    public void setDISEASE_COUNT(int DISEASE_COUNT) {
        this.DISEASE_COUNT = DISEASE_COUNT;
    }
}
