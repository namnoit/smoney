package com.example.smoney;

import android.support.v4.app.Fragment;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class noti_item {
    private String time;
    private String action;
    private String sk;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public noti_item() {
    }

    public noti_item(String time, String action, String sk) {
        this.time = time;
        this.action = action;
        this.sk = sk;


    }

    public noti_item(String action, String sk) {
        this.action = action;
        this.sk = sk;
    }

}
