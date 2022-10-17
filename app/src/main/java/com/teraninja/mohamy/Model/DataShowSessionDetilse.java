package com.teraninja.mohamy.Model;

public class DataShowSessionDetilse {
    public int status;
    public int code;
    public String message;
    public DataSessionDetilse data;

    public DataShowSessionDetilse(int status, String message) {
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

    public DataSessionDetilse getData() {
        return data;
    }

    public void setData(DataSessionDetilse data) {
        this.data = data;
    }
}
