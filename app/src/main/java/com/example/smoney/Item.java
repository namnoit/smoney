package com.example.smoney;

public class Item{
    public int ID, type;
    public long amount;
    public String date, commment;

    public Item(int id, int typ, long am, String d, String cmt){
        this.ID = id;
        this.type = typ;
        this.amount = am;
        this.date = d;
        this.commment = cmt;
    }

    public String toString(){
        String s = "ID: " + Integer.toString(ID) + ", TYPE: " + Integer.toString(type) +
                ", AMOUNT: " + Long.toString(amount) + ", DATE: " + date + ", COMMENT: " + commment;
        return s;
    }
}
