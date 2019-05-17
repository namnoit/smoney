package com.example.smoney.ContainClass;

import android.content.Context;
import android.util.Log;

import com.example.smoney.ContainClass.CustomAdapterOneday;
import com.example.smoney.HistoryFragment;
import com.example.smoney.Item;
import com.example.smoney.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataHistory {
    Context context;
    public HistoryFragment historyFragment;
    public String getTime(String timestamp,boolean decrease){
        int month=Integer.parseInt(timestamp.split("/")[0]);
        int year = Integer.parseInt(timestamp.split("/")[1]);
        if (decrease){
            if (month-1==0){
                month=12;
                year=year-1;
            }else{
                month=month-1;
                year=year;
            }

        }else{
            if (month+1>12){
                month=1;
                year=year+1;
            }else{
                month=month+1;
                year=year;
            }
        }
        if(month<10)
            return "0"+Integer.toString(month)+"/"+Integer.toString(year);
        return Integer.toString(month)+"/"+Integer.toString(year);
    }
    public DataHistory(HistoryFragment _historyFragment) {
        historyFragment=_historyFragment;
    }
    public ArrayList getData(List<Item> itemList){
        int totalinmonth=0;
        int totaloutmont=0;

        return null;
    }
    public void generatedata(){

    }

    public static String convert(String str){
        //tao dấu chấm cho số 1.000.000
        int value=Integer.parseInt(str);
        //kiem tra so <0
        if (str.length()<4)
            return str;
        if (value<0){
            str=str.split("-")[1];
        }
        //dem so cum 3
        int numb=str.length();
        int dart = numb / 3;
        if (numb%3!=0)dart+=1;
        int i=0;
        String strnew="";
        while(i<dart){
            int k=str.length()-3*(i+1);
            if(k<0){break;}
            if(strnew!=""){
                strnew=str.substring(k,k+3)+"."+strnew;
                System.out.print(strnew+"\n");
            }else{
                strnew=str.substring(k,k+3);
                System.out.print(strnew+"\n");
            }
            ++i;
        }
        if (numb%3!=0)
            strnew = str.substring(0,str.length()-3*(i))+"."+strnew;
        //kiem tra so am
        if (value<0)
            return "-"+strnew;
        return strnew;
    }
    public void setContext(Context _context){
        context=_context;
    }
    public void generateData(){
        for(int i=0;i<19;i++){
            if(i!=5 && i!=6 && i!=7&& i!=8&&i!=9) {
                Item item = new Item(i, i, i * 2000000, "2019/04/03", "Gucci");
                //model.addInOut(i, i * 2000000, "2019/04/03", "Gucci");
            }

        }
        for(int i=0;i<19;i++){
            if(i!=5 && i!=6 && i!=7&& i!=8&&i!=9) {
                Item item = new Item(i, i, i * 2000000, "2019/05/01", "LV");
                //model.addInOut(i, i * 2000000, "2019/05/03", "Gucci");
            }

        }
    }
    public List filterDataByDay(List<Item> ItemOfList){
        int totalinmonth=0;
        int totaloutmont=0;
        List listValue=new ArrayList();
        int icount=0;
        while(true){
            if (icount==ItemOfList.size())break;
            List<Item>itemListbygroup=new ArrayList<Item>();
            itemListbygroup.add(ItemOfList.get(icount));
            while(icount<=ItemOfList.size()-1){
                if (ItemOfList.get(icount).type<=4){
                    totalinmonth += ItemOfList.get(icount).amount;
                }else{
                    totaloutmont += ItemOfList.get(icount).amount;
                }
                if(icount+1<=ItemOfList.size()-1 && ItemOfList.get(icount).date.equals(ItemOfList.get(icount+1).date)){
                    itemListbygroup.add(ItemOfList.get(icount+1));
                    icount++;
                }else {
                    icount++;
                    break;
                }
            }
            historyFragment.itemLinkedList.add(itemListbygroup);
        }
        listValue.add(totalinmonth);
        listValue.add(totaloutmont);
        return listValue;
    }
   public void ProcessAccess(String durationtime){
       String year = durationtime.split("/")[1];
       String month= durationtime.split("/")[0];
       Model model = new Model(historyFragment.getContext());
       // Model model = new Model(getContext());


       //model.updateInOut(0,0,200000,"2019/05/10","LV");
       //System.out.print(model.getInOut("2019//05//01","2019//05//31"));

       historyFragment.itemLinkedList=new LinkedList<>();
       historyFragment.itemLinkedList.clear();
       List listReturn= new ArrayList();
       int totalinmonth=0;
       int totaloutmont=0;
       String startdate= year+"/"+month+"/01";
       String endate= year+"/"+month+"/31";

       //listterm
       List<Item>itemList=new ArrayList<Item>();

       Log.i("datedata",startdate);
       itemList=model.getInOut(startdate,endate);
       //Log.i("countNote",String.valueOf(model.getItem(0).size()));

       //Chia dữ liệu theo ngày
       listReturn=filterDataByDay(itemList);
        totalinmonth=(int)listReturn.get(0);
        totaloutmont=(int)listReturn.get(1);

        //Cập nhật View
           historyFragment.upgradeAgrument("0",convert(String.valueOf(totaloutmont)));
           historyFragment.upgradeAgrument("1",convert(String.valueOf(totalinmonth)));
           historyFragment.upgradeAgrument("2",convert(String.valueOf(totalinmonth-totaloutmont)));
       if(totalinmonth !=0 ||totaloutmont!=0) {
           //Gọi Apdapter cho listview cha
           CustomAdapterOneday customAdapterOneDay = new CustomAdapterOneday(historyFragment.dataHistory);
           historyFragment.LViewDay.setAdapter(customAdapterOneDay);
           Log.i("setnull1","OKe");
       }else{
           //setnull khi qua frame khác
           historyFragment.LViewDay.setAdapter(null);
           Log.i("setnull","OKe");
       }
    }
}
