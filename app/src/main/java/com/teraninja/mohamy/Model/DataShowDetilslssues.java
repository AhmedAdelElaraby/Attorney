package com.teraninja.mohamy.Model;

import java.util.ArrayList;

public class DataShowDetilslssues {
    public int status;
    public int code;
    public String message;
    public ArrayList<DetilseLssues> data;

    public DataShowDetilslssues(int status, String message) {
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

    public ArrayList<DetilseLssues> getData() {
        return data;
    }

    public void setData(ArrayList<DetilseLssues> data) {
        this.data = data;
    }
}
