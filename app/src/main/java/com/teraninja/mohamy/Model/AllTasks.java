package com.teraninja.mohamy.Model;

public class AllTasks {
    public int id;
    public int trial_id;
    public String start_date;
    public String end_date;
    public String hijri_start;
    public String hijri_end;
    public String status;
    public Trial trial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrial_id() {
        return trial_id;
    }

    public void setTrial_id(int trial_id) {
        this.trial_id = trial_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getHijri_start() {
        return hijri_start;
    }

    public void setHijri_start(String hijri_start) {
        this.hijri_start = hijri_start;
    }

    public String getHijri_end() {
        return hijri_end;
    }

    public void setHijri_end(String hijri_end) {
        this.hijri_end = hijri_end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Trial getTrial() {
        return trial;
    }

    public void setTrial(Trial trial) {
        this.trial = trial;
    }
}
