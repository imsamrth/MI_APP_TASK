package com.samarthappwork.mitaskassigner;

public class New_user {
    String name,is_cordie,email,password,phone,department;

    public New_user(String name, String email, String password, String phone , String department) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone ;
        this.department = department ;
        this.is_cordie = "YES" ;


    }

    public New_user() {
    }

    public New_user(String name, String is_cordie, String email, String password, String phone, String department) {
        this.name = name;
        this.is_cordie = is_cordie;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.department = department;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIs_cordie() {
        return is_cordie;
    }

    public void setIs_cordie(String is_cordie) {
        this.is_cordie = is_cordie;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

