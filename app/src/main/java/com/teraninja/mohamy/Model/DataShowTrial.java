package com.teraninja.mohamy.Model;

public class DataShowTrial {
    public int status;
    public int code;
    public String message;
    public ShowTrial data;

    public DataShowTrial(int status, String message) {
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

    public ShowTrial getData() {
        return data;
    }

    public void setData(ShowTrial data) {
        this.data = data;
    }
}
