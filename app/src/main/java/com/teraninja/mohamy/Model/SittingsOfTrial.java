package com.teraninja.mohamy.Model;

import java.util.ArrayList;

public class SittingsOfTrial {
    public int id;
    public int type_id;
    public String title;
    public String description;
    public String trial_code;
    public String year;
    public String hijri;
    public String no_of_trial;
    public ArrayList<LastSiting> last_sitings;
    public ArrayList<nextsitings> next_sitings;
    public TypesData type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<LastSiting> getLast_sitings() {
        return last_sitings;
    }

    public void setLast_sitings(ArrayList<LastSiting> last_sitings) {
        this.last_sitings = last_sitings;
    }

    public ArrayList<nextsitings> getNext_sitings() {
        return next_sitings;
    }

    public void setNext_sitings(ArrayList<nextsitings> next_sitings) {
        this.next_sitings = next_sitings;
    }

    public TypesData getType() {
        return type;
    }

    public void setType(TypesData type) {
        this.type = type;
    }
}
