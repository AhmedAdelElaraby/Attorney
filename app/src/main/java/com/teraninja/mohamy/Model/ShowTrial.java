package com.teraninja.mohamy.Model;

public class ShowTrial {
    public int id;
    public int user_id;
    public int type_id;
    public String title;
    public String description;
    public String trial_code;
    public String year;
    public String hijri;
    public String no_of_trial;
    public ClientTrial client;
    public TypesData type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrial_code() {
        return trial_code;
    }

    public void setTrial_code(String trial_code) {
        this.trial_code = trial_code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHijri() {
        return hijri;
    }

    public void setHijri(String hijri) {
        this.hijri = hijri;
    }

    public String getNo_of_trial() {
        return no_of_trial;
    }

    public void setNo_of_trial(String no_of_trial) {
        this.no_of_trial = no_of_trial;
    }

    public ClientTrial getClient() {
        return client;
    }

    public void setClient(ClientTrial client) {
        this.client = client;
    }

    public TypesData getType() {
        return type;
    }

    public void setType(TypesData type) {
        this.type = type;
    }
}
