package com.example.smoney;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.smoney.ContainClass.DataHistory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HistoryFragment extends Fragment {
    String[] DayofWeek={"Thứ hai","Thứ ba","Thứ tư",};
    List listitme= new ArrayList();
    public LinkedList<List<Item>> itemLinkedList = new LinkedList<>();

    int[] Dayth={1,2,3};
     public static Button buttonc;
     static boolean Statusbefore=false;
     static String timestamp="";
     ImageView buttonp;
     ImageView buttonf ;
     TextView moneyout;
     TextView moneyin;
     TextView moneyremain;
     RecyclerView recyclerView;
    //RecycleItem rItem;
     //RecycleView rView;
    public ListView LViewDay=null;
    View _convertview=null;
    FloatingActionButton fab;
    public DataHistory dataHistory;

    Context context;
    public HistoryFragment(){
        ListView lviewday=null;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @SuppressLint("ResourceType")
    public void loadFragment(Fragment fragment,int id){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public String getTimeCurrent(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return String.valueOf(dateFormat.format(date)).split(" ")[0]; //2016/11/16 12:08:43
    }
    public void openDialog1(Item item){
        EditinginfoDialog editinginfodialog = new EditinginfoDialog(item,this);
        editinginfodialog.showNow(getChildFragmentManager(),  " ");
    }
    public void openDialog(){
        AddinginfoDialog addinginfoDialog = new AddinginfoDialog(this);
        addinginfoDialog.showNow(getChildFragmentManager(),  " ");
        dataHistory.ProcessAccess(buttonc.getText().toString());

    }
    public void onInit(){
        if (Statusbefore==false){
            String times=String.valueOf(getTimeCurrent().split("/")[1])+"/"+getTimeCurrent().split("/")[2];
            buttonc.setText(times);
        }else
        buttonc.setText(timestamp);
    }
    @SuppressLint("WrongViewCast")
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_history, container, false);
        context=getContext();
        buttonc = (Button) view.findViewById(R.id.current);
        buttonp = (ImageView) view.findViewById(R.id.previous);
        buttonf = (ImageView) view.findViewById(R.id.following);
        dataHistory=new DataHistory(this);
        onInit();
        moneyout=(TextView)view.findViewById(R.id.money_out);
        moneyin = (TextView)view.findViewById(R.id.money_in);
        moneyremain=(TextView)view.findViewById(R.id.money_remain);
        fab = view.findViewById(R.id.fab);
        buttonp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setButtonClick(true);
            }
        });
        buttonf.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                setButtonClick(false);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        LViewDay = (ListView) view.findViewById(R.id.itemthesameday);
        dataHistory.ProcessAccess(buttonc.getText().toString());

        return view;
    }
    public void upgradeAgrument(String code,String info){
        info.lastIndexOf(info);
        switch (code){
            case "0":
                moneyout.setText(info);
                break;
            case "1":
                moneyin.setText(info);
                break;
            case "2":
                moneyremain.setText(info);
                break;
        }
    }

    //Buntton vao theo thoi diem
    public void setButtonClick(boolean isPrevious){
        if (isPrevious){
            String daterate=dataHistory.getTime(buttonc.getText().toString(),true);
            dataHistory.ProcessAccess(daterate);
            buttonc.setText(daterate);
            timestamp=daterate;
            Statusbefore=true;
        }else{
            String daterate=dataHistory.getTime(buttonc.getText().toString(),false);
            dataHistory.ProcessAccess(daterate);
            buttonc.setText(daterate);
            timestamp=daterate;
            Statusbefore=true;
        }

    }
}