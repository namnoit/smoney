package com.example.smoney.ContainClass;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smoney.Item;
import com.example.smoney.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterOneday extends BaseAdapter {
    DataHistory dataHistory;
    public CustomAdapterOneday(DataHistory _dataHistory){
        dataHistory=_dataHistory;
    }
    public int height = 0;
    public View _convertviews;

    @Override
    public int getCount() {
        return dataHistory.historyFragment.itemLinkedList.size();
    }

    @Override
    public Object getItem(int position) {
        //System.out.print(DayofWeek[position]);return null;
        return 00;
    }

    @Override
    public long getItemId(int position) {
        //System.out.print(DayofWeek[position]);
        return 0;
    }
    public List calculatorTotalEachInOut(int position){
        int totalpos=0;
        int totalneg=0;
        List returnList=new ArrayList();
        for(int i=0;i<dataHistory.historyFragment.itemLinkedList.get(position).size();i++){
            if (dataHistory.historyFragment.itemLinkedList.get(position).get(i).type<=4) {
                totalpos += dataHistory.historyFragment.itemLinkedList.get(position).get(i).amount;
            }else{
                totalneg +=dataHistory.historyFragment.itemLinkedList.get(position).get(i).amount;
            }
        }
        returnList.add(totalpos);
        returnList.add(totalneg);
        return returnList;
    }
    @SuppressLint("ResourceType")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = dataHistory.historyFragment.getLayoutInflater().inflate(R.layout.column,null);
        TextView Dayname= (TextView)convertView.findViewById(R.id.Dayname);
        TextView Totalout = (TextView)convertView.findViewById(R.id.Daynumber);
        final TextView Totalcost = (TextView)convertView.findViewById(R.id.Totalcost);
        ListView ListChild = convertView.findViewById(R.id.listitem);
        CustomAdapterView customAdapterView = new CustomAdapterView(dataHistory.historyFragment.itemLinkedList.get(position),dataHistory);


        int totalpos=0;
        int totalneg=0;
        List returnList=new ArrayList();

        returnList=calculatorTotalEachInOut(position);
        totalpos=(int)returnList.get(0);
        totalneg=(int)returnList.get(1);



        Totalcost.setText(dataHistory.convert(String.valueOf(totalneg)));
        //Totalcost.setTextColor(itemLinkedList.get(position).get(0).setColor(1));
        Totalout.setText(dataHistory.convert(String.valueOf(totalpos)));
        Totalout.setTextColor(dataHistory.historyFragment.itemLinkedList.get(position).get(0).setColor(0));
        Dayname.setText(dataHistory.historyFragment.itemLinkedList.get(position).get(0).date.split("/")[2]);

        final List<Item> itemListcur=dataHistory.historyFragment.itemLinkedList.get(position);
        //customAdapterView.getCount()*
        final float scale = dataHistory.historyFragment.getResources().getDisplayMetrics().density;
        ListChild.getLayoutParams().height=(int)((customAdapterView.getCount()+1)*(61*scale));
        ListChild.setAdapter(customAdapterView);
        ListChild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int icount, long id) {
                //Mở Dialog Sửa xóa
                DialogIDE dialogIDE = new DialogIDE(dataHistory);
                dialogIDE.OpenDialog(itemListcur.get(icount));
            }
        });
        return convertView;
    }
}
