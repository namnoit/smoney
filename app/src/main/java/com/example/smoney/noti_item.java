package com.example.smoney;

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



    public noti_item(String time, String action, String sk) {
        this.time = time;
        this.action = action;
        this.sk = sk;

    }
}
