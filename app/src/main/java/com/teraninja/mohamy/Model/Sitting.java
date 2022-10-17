package com.teraninja.mohamy.Model;

public class Sitting {
    public int id;
    public String sitting_no;
    public int trial_id;
    public int type_id;
    public int task_id;
    public String judge_name;
    public String court_name;
    public String circle_name;
    public String date;
    public String time;
    public String hijri;
    public String city;
    public String body;
    public int employee_id;
    public int assigned_to;
    public int postponed;
    public String court_ruling;
    public String next_discussion;
    public String next_sitting_recommendations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSitting_no() {
        return sitting_no;
    }

    public void setSitting_no(String sitting_no) {
        this.sitting_no = sitting_no;
    }

    public int getTrial_id() {
        return trial_id;
    }

    public void setTrial_id(int trial_id) {
        this.trial_id = trial_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getJudge_name() {
        return judge_name;
    }

    public void setJudge_name(String judge_name) {
        this.judge_name = judge_name;
    }

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getCircle_name() {
        return circle_name;
    }

    public void setCircle_name(String circle_name) {
        this.circle_name = circle_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHijri() {
        return hijri;
    }

    public void setHijri(String hijri) {
        this.hijri = hijri;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(int assigned_to) {
        this.assigned_to = assigned_to;
    }

    public int getPostponed() {
        return postponed;
    }

    public void setPostponed(int postponed) {
        this.postponed = postponed;
    }

    public String getCourt_ruling() {
        return court_ruling;
    }

    public void setCourt_ruling(String court_ruling) {
        this.court_ruling = court_ruling;
    }

    public String getNext_discussion() {
        return next_discussion;
    }

    public void setNext_discussion(String next_discussion) {
        this.next_discussion = next_discussion;
    }

    public String getNext_sitting_recommendations() {
        return next_sitting_recommendations;
    }

    public void setNext_sitting_recommendations(String next_sitting_recommendations) {
        this.next_sitting_recommendations = next_sitting_recommendations;
    }
}
