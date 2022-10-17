package com.teraninja.mohamy.Model;

public class Salarys {
    public int id;
    public int user_id;
    public String salary;
    public Object allowances;
    public Object benefits;
    public Object deductions;
    public String net_salary;
    public int gress_salary;

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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Object getAllowances() {
        return allowances;
    }

    public void setAllowances(Object allowances) {
        this.allowances = allowances;
    }

    public Object getBenefits() {
        return benefits;
    }

    public void setBenefits(Object benefits) {
        this.benefits = benefits;
    }

    public Object getDeductions() {
        return deductions;
    }

    public void setDeductions(Object deductions) {
        this.deductions = deductions;
    }

    public String getNet_salary() {
        return net_salary;
    }

    public void setNet_salary(String net_salary) {
        this.net_salary = net_salary;
    }

    public int getGress_salary() {
        return gress_salary;
    }

    public void setGress_salary(int gress_salary) {
        this.gress_salary = gress_salary;
    }
}
