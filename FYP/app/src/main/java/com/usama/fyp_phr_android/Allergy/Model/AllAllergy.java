package com.usama.fyp_phr_android.Allergy.Model;

public class AllAllergy {
    int a_id;
    String a_name;

    public AllAllergy() {
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public AllAllergy(int a_id, String a_name) {
        this.a_id = a_id;
        this.a_name = a_name;
    }

}
