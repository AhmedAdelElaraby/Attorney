package com.teraninja.mohamy.Model;

public class DataSessionDetilse {
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
    public Employee employee;
    public Trial trial;
    public Taskssessions task;

    public int getId() {
        return id;
    }

    public String getSitting_no() {
        return sitting_no;
    }

    public int getTrial_id() {
        return trial_id;
    }

    public int getType_id() {
        return type_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getJudge_name() {
        return judge_name;
    }

    public String getCourt_name() {
        return court_name;
    }

    public String getCircle_name() {
        return circle_name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHijri() {
        return hijri;
    }

    public String getCity() {
        return city;
    }

    public String getBody() {
        return body;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public int getAssigned_to() {
        return assigned_to;
    }

    public int getPostponed() {
        return postponed;
    }

    public String getCourt_ruling() {
        return court_ruling;
    }

    public String getNext_discussion() {
        return next_discussion;
    }

    public String getNext_sitting_recommendations() {
        return next_sitting_recommendations;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Trial getTrial() {
        return trial;
    }

    public Taskssessions getTask() {
        return task;
    }
}
