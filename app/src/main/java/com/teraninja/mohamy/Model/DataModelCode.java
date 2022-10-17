package com.teraninja.mohamy.Model;

public class DataModelCode {
    public int status;
    public int code;
    public String message;
    public Clientes data;

    public DataModelCode(int status, String message) {
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

    public Clientes getData() {
        return data;
    }

    public void setData(Clientes data) {
        this.data = data;
    }
}
