package com.teraninja.mohamy.Model;

public class Trial {
    public int id;
    public String title;
    public int type_id;
    public String trial_code;
    public String no_of_trial;
    public TypesData type;

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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTrial_code() {
        return trial_code;
    }

    public void setTrial_code(String trial_code) {
        this.trial_code = trial_code;
    }

    public String getNo_of_trial() {
        return no_of_trial;
    }

    public void setNo_of_trial(String no_of_trial) {
        this.no_of_trial = no_of_trial;
    }

    public TypesData getType() {
        return type;
    }

    public void setType(TypesData type) {
        this.type = type;
    }
}
