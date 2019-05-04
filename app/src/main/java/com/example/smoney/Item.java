package com.example.smoney;

import android.graphics.Color;
import android.provider.MediaStore;

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
    public int setColor(int code){
        if (code == 0){
            return Color.parseColor("#289645");
        }
        return Color.parseColor("#ad341f");
    }
    public int setColor(){
        if (type<=4){
            return Color.parseColor("#289645");
        }
        return Color.parseColor("#ad341f");
    }
    public int TypeToImage[]={R.drawable.ic_inother,R.drawable.ic_salary_web,R.drawable.ic_trade_web,R.drawable.ic_inlotto,R.drawable.ic_interest,0,0,0,0,0,R.drawable.ic_outother,R.drawable.ic_outeating,R.drawable.ic_outhealth,R.drawable.ic_inlotto,R.drawable.ic_outtransport,R.drawable.ic_inshopping,R.drawable.ic_inbuy,R.drawable.ic_outphone,R.drawable.ic_outgar};
    public String TypeToEnty(){
        switch (type){
            case 0:
                return "Khoản thu khác";

            case 1:
                return "Khoản lương";

            case 2:
                return "Buôn bán";

            case 3:
                return "Trúng xổ";

            case 4:
                return "Thu lãi";

            case 10:
                return "Khoản chi khác";

            case 11:
                return "Ăn uống";

            case 12:
                return "Sức khỏe";

            case 13:
                return "Xổ số";

            case 14:
                return "Chuyển tiền";

            case 15:
                return "Shopping";

            case 16:
                return "Thanh toán";

            case 17:
                return "Điện thoại";

            case 18:
                return "Nhiên liệu";

            default: return null;
        }
    }
}
