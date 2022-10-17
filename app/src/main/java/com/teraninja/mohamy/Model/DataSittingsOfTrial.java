package com.teraninja.mohamy.Model;

public class DataSittingsOfTrial {

    public int status;
    public int code;
    public String message;
    public SittingsOfTrial data;

    public DataSittingsOfTrial(int status, String message) {
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

    public SittingsOfTrial getData() {
        return data;
    }

    public void setData(SittingsOfTrial data) {
        this.data = data;
    }
}
