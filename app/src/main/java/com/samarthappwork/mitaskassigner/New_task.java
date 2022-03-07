package com.samarthappwork.mitaskassigner;

import java.text.DateFormat;
import java.util.Calendar;

public class New_task {

    String DepartmentId = "";
    String event_name = "";
    String event_ID = "";
    String task_name = "";
    String deadline = "";
    String task_description = "";
    String ispending = "";


    //todo add time stamp availability


    public New_task() {
    }

    public New_task( String departmentId, String event_name, String event_ID, String task_name, String deadline, String task_description) {
        DepartmentId = departmentId;
        this.event_name = event_name;
        this.event_ID = event_ID;
        this.task_name = task_name;
        this.deadline = deadline;
        this.task_description = task_description;
        this.ispending = "Pending";
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String departmentId) {
        DepartmentId = departmentId;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_ID() {
        return event_ID;
    }

    public void setEvent_ID(String event_ID) {
        this.event_ID = event_ID;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTask_description() {
        return task_description;
    }

    public String getIspending() {
        return ispending;
    }

    public void setIspending(String ispending) {
        this.ispending = ispending;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }
}

