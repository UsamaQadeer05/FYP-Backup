package com.usama.fyp_phr_android.User.Model;

public class User {
    int u_id;
    String u_name;
    String u_gender;
    String u_dob;
    String u_country;
    String u_province;
    String u_city;
    String u_password;
    String u_waterfac;
    String u_area;
    String u_home;
    String u_food;
    String u_ventilation;
    String u_cnic;


    public User() {
    }

    public User(int u_id, String u_name, String u_cnic, String u_gender, String u_dob, String u_country, String u_province, String u_city, String u_password, String u_waterfac, String u_area, String u_home, String u_food, String u_ventilation) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_cnic = u_cnic;
        this.u_gender = u_gender;
        this.u_dob = u_dob;
        this.u_country = u_country;
        this.u_province = u_province;
        this.u_city = u_city;
        this.u_password = u_password;
        this.u_waterfac = u_waterfac;
        this.u_area = u_area;
        this.u_home = u_home;
        this.u_food = u_food;
        this.u_ventilation = u_ventilation;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_cnic() {
        return u_cnic;
    }

    public void setU_cnic(String u_cnic) {
        this.u_cnic = u_cnic;
    }

    public String getU_gender() {
        return u_gender;
    }

    public void setU_gender(String u_gender) {
        this.u_gender = u_gender;
    }

    public String getU_dob() {
        return u_dob;
    }

    public void setU_dob(String u_dob) {
        this.u_dob = u_dob;
    }

    public String getU_country() {
        return u_country;
    }

    public void setU_country(String u_country) {
        this.u_country = u_country;
    }

    public String getU_province() {
        return u_province;
    }

    public void setU_province(String u_province) {
        this.u_province = u_province;
    }

    public String getU_city() {
        return u_city;
    }

    public void setU_city(String u_city) {
        this.u_city = u_city;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_waterfac() {
        return u_waterfac;
    }

    public void setU_waterfac(String u_waterfac) {
        this.u_waterfac = u_waterfac;
    }

    public String getU_area() {
        return u_area;
    }

    public void setU_area(String u_area) {
        this.u_area = u_area;
    }

    public String getU_home() {
        return u_home;
    }

    public void setU_home(String u_home) {
        this.u_home = u_home;
    }

    public String getU_food() {
        return u_food;
    }

    public void setU_food(String u_food) {
        this.u_food = u_food;
    }

    public String getU_ventilation() {
        return u_ventilation;
    }

    public void setU_ventilation(String u_ventilation) {
        this.u_ventilation = u_ventilation;
    }
}
