package com.samarthappwork.mitaskassigner;

import java.text.DateFormat;
import java.util.Calendar;

public class New_event {

        String DepartmentId;
        String event_name;
        String event_ID;
        String total_tasks;
        String timestamp;
        String imageurl;
        String Event_description ;


        //todo add time stamp availability
    public New_event( String event_name, String event_ID, String event_description) {
        this.DepartmentId = LoginedUser.getDepartment_ID();
        this.event_name = event_name;
        this.total_tasks = "0";
        this.timestamp = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        this.imageurl = " ";
        this.event_ID = event_ID;
        Event_description = event_description;
    }

        public New_event () {
        }

    public New_event(String departmentId, String event_name, String event_ID, String total_tasks, String timestamp, String imageurl, String event_description) {
        DepartmentId = departmentId;
        this.event_name = event_name;
        this.event_ID = event_ID;
        this.total_tasks = total_tasks;
        this.timestamp = timestamp;
        this.imageurl = imageurl;
        Event_description = event_description;
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

    public String getTotal_tasks() {
        return total_tasks;
    }

    public void setTotal_tasks(String total_tasks) {
        this.total_tasks = total_tasks;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getEvent_description() {
        return Event_description;
    }

    public void setEvent_description(String event_description) {
        Event_description = event_description;
    }
}


