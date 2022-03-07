package com.samarthappwork.mitaskassigner;

import android.app.Application;

public class LoginedUser extends Application {


        private static int current_tasks = 0;
        private static int notification = 0 ;
        private static int islogined = 0;
        private static String is_CG = "";
        private static String name = "" ;
        private static String email = "" ;
        private static String MI_NUM = "1" ;
        private static String phone = "" ;
        private static String Department_ID = "" ;

    public static int getCurrent_tasks() {
        return current_tasks;
    }

    public static void setCurrent_tasks(int current_tasks) {
        LoginedUser.current_tasks = current_tasks;
    }

    public static int getNotification() {
        return notification;
    }

    public static void setNotification(int notification) {
        LoginedUser.notification = notification;
    }

    public static int getIslogined() {
        return islogined;
    }

    public static void setIslogined(int islogined) {
        LoginedUser.islogined = islogined;
    }

    public static String getIs_CG() {
        return is_CG;
    }

    public static void setIs_CG(String is_CG) {
        LoginedUser.is_CG = is_CG;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        LoginedUser.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        LoginedUser.email = email;
    }

    public static String getMiNum() {
        return MI_NUM;
    }

    public static void setMiNum(String miNum) {
        MI_NUM = miNum;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        LoginedUser.phone = phone;
    }

    public static String getDepartment_ID() {
        return Department_ID;
    }

    public static void setDepartment_ID(String department_ID) {
        Department_ID = department_ID;
    }
}


