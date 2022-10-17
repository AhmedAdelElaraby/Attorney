package com.teraninja.mohamy.Model;

public class TrialData {
    public int id;
    public String title;
    public int user_id;
    public String trial_code;
    public int branch_id;
    public Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTrial_code() {
        return trial_code;
    }

    public void setTrial_code(String trial_code) {
        this.trial_code = trial_code;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
