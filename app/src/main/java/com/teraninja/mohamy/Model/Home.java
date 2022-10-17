package com.teraninja.mohamy.Model;

import java.util.ArrayList;

public class Home {
    public int id;
    public String name;
    public String email;
    public String mobile;
    public String photo;
    public int branch_id;
    public String role;
    public ArrayList<Tasks> tasks;
    public Branch branch;
    public String building_number;
    public String street_name;
    public String district;
    public String city;
    public String postal_code;
    public String additional_number;
    public String type_of_job;
    public String tax_number;
    public int approved_mobile;

    public String getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(String building_number) {
        this.building_number = building_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getAdditional_number() {
        return additional_number;
    }

    public void setAdditional_number(String additional_number) {
        this.additional_number = additional_number;
    }

    public String getType_of_job() {
        return type_of_job;
    }

    public void setType_of_job(String type_of_job) {
        this.type_of_job = type_of_job;
    }

    public String getTax_number() {
        return tax_number;
    }

    public void setTax_number(String tax_number) {
        this.tax_number = tax_number;
    }

    public int getApproved_mobile() {
        return approved_mobile;
    }

    public void setApproved_mobile(int approved_mobile) {
        this.approved_mobile = approved_mobile;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
