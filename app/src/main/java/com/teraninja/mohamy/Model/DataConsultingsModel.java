package com.teraninja.mohamy.Model;

public class DataConsultingsModel {
    public int status;
    public int code;
    public String message;
    public Consultingss data;

    public DataConsultingsModel(int status, String message) {
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

    public Consultingss getData() {
        return data;
    }

    public void setData(Consultingss data) {
        this.data = data;
    }
}
