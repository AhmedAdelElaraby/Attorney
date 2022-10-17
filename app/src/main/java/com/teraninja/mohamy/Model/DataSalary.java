package com.teraninja.mohamy.Model;

import com.teraninja.mohamy.Salary;

public class DataSalary {
    public int status;
    public int code;
    public String message;
    public Salarys data;

    public DataSalary(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Salarys getData() {
        return data;
    }

    public void setData(Salarys data) {
        this.data = data;
    }
}
