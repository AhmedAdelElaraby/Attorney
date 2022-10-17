package com.teraninja.mohamy.Model;

public class DetilseTaskes {
    public int id;
    public int trial_id;
    public int department_id;
    public int tag_id;
    public String descritpion;
    public int employee_id;
    public int assigned_to;
    public int type_id;
    public String view_type;
    public String priority;
    public String start_date;
    public String end_date;
    public int points;
    public int rating;
    public int is_done;
    public int is_complete;
    public String task_place;
    public String status;
    public String hijri_start;
    public String hijri_end;
    public Tages tag;
    public Employee employee;
    public Assigned assigned;
    public Typees type;
    public Meeting meeting;
    public Department department;
    public Sitting sitting;

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

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getView_type() {
        return view_type;
    }

    public void setView_type(String view_type) {
        this.view_type = view_type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIs_done() {
        return is_done;
    }

    public void setIs_done(int is_done) {
        this.is_done = is_done;
    }

    public int getIs_complete() {
        return is_complete;
    }

    public void setIs_complete(int is_complete) {
        this.is_complete = is_complete;
    }

    public String getTask_place() {
        return task_place;
    }

    public void setTask_place(String task_place) {
        this.task_place = task_place;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Tages getTag() {
        return tag;
    }

    public void setTag(Tages tag) {
        this.tag = tag;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Assigned getAssigned() {
        return assigned;
    }

    public void setAssigned(Assigned assigned) {
        this.assigned = assigned;
    }

    public Typees getType() {
        return type;
    }

    public void setType(Typees type) {
        this.type = type;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Sitting getSitting() {
        return sitting;
    }

    public void setSitting(Sitting sitting) {
        this.sitting = sitting;
    }
}
