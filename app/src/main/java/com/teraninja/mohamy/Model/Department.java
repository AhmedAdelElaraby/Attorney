package com.teraninja.mohamy.Model;

public class Department {
    public int id;
    public String name;
    public String color_code;
    public int is_deletedable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public int getIs_deletedable() {
        return is_deletedable;
    }

    public void setIs_deletedable(int is_deletedable) {
        this.is_deletedable = is_deletedable;
    }
}
