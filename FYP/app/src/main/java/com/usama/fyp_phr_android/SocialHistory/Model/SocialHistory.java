package com.usama.fyp_phr_android.SocialHistory.Model;

public class SocialHistory {
    int sh_id;
    int u_id;
    int d_id;
    String s_level;
    String s_relation;
    String lastUpdated;

    public SocialHistory() {
    }

    public SocialHistory(int sh_id, int u_id, int d_id, String s_level, String s_relation, String lastUpdated) {
        this.sh_id = sh_id;
        this.u_id = u_id;
        this.d_id = d_id;
        this.s_level = s_level;
        this.s_relation = s_relation;
        this.lastUpdated = lastUpdated;
    }

    public int getSh_id() {
        return sh_id;
    }

    public void setSh_id(int sh_id) {
        this.sh_id = sh_id;
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

    public String getS_level() {
        return s_level;
    }

    public void setS_level(String s_level) {
        this.s_level = s_level;
    }

    public String getS_relation() {
        return s_relation;
    }

    public void setS_relation(String s_relation) {
        this.s_relation = s_relation;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
