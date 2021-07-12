package com.usama.fyp_phr_android.Medication.Model;

public class AllMedication {
    int m_id;
    String m_name;
    String m_company;

    public AllMedication(int m_id, String m_name, String m_company) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_company = m_company;
    }

    public AllMedication() {
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_company() {
        return m_company;
    }

    public void setM_company(String m_company) {
        this.m_company = m_company;
    }

}
