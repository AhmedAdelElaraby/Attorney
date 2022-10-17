package com.teraninja.mohamy.Model;

public class Tasks {
    public int id;
    public int tag_id;
    public String start_date;
    public String end_date;
    public Tage tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
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

    public Tage getTag() {
        return tag;
    }

    public void setTag(Tage tag) {
        this.tag = tag;
    }
}
