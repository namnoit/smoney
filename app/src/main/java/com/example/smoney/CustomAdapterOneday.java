package com.example.smoney;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

    @SuppressLint("ResourceType")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i("Thucnull",String.valueOf(dataHistory.historyFragment));
        convertView = dataHistory.historyFragment.getLayoutInflater().inflate(R.layout.column,null);
        TextView Dayname= (TextView)convertView.findViewById(R.id.Dayname);
        TextView Totalout = (TextView)convertView.findViewById(R.id.Daynumber);

        final TextView Totalcost = (TextView)convertView.findViewById(R.id.Totalcost);
        Dayname.setText(dataHistory.historyFragment.itemLinkedList.get(position).get(0).date.split("/")[2]);

        int totalpos=0;
        int totalneg=0;
        for(int i=0;i<dataHistory.historyFragment.itemLinkedList.get(position).size();i++){
            if (dataHistory.historyFragment.itemLinkedList.get(position).get(i).type<=4) {
                totalpos += dataHistory.historyFragment.itemLinkedList.get(position).get(i).amount;
            }else{
                totalneg +=dataHistory.historyFragment.itemLinkedList.get(position).get(i).amount;
            }
        }


        Totalcost.setText(dataHistory.convert(String.valueOf(totalneg)));
        //Totalcost.setTextColor(itemLinkedList.get(position).get(0).setColor(1));
        Totalout.setText(dataHistory.convert(String.valueOf(totalpos)));
        Totalout.setTextColor(dataHistory.historyFragment.itemLinkedList.get(position).get(0).setColor(0));
        ListView lviewday = convertView.findViewById(R.id.listitem);

        CustomAdapterView customAdapterView = new CustomAdapterView(dataHistory.historyFragment.itemLinkedList.get(position),dataHistory);
        final List<Item> itemListcur=dataHistory.historyFragment.itemLinkedList.get(position);
        //customAdapterView.getCount()*
        final float scale = dataHistory.historyFragment.getResources().getDisplayMetrics().density;
        lviewday.getLayoutParams().height=(int)((customAdapterView.getCount()+1)*(61*scale));
        lviewday.setAdapter(customAdapterView);
        lviewday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int icount, long id) {
                final Dialog mydialog =new Dialog(dataHistory.historyFragment.getContext());
                ImageView edittask;
                ImageView deletetask;
                final detailFragment _detailFragment=new detailFragment(dataHistory.historyFragment.getContext());
                View view1 = dataHistory.historyFragment.getLayoutInflater().inflate(R.layout.fragment_detail,null);
                mydialog.setContentView(view1);

                TextView TextType = (TextView) view1.findViewById(R.id.type);
                TextView TextAmount = (TextView) view1.findViewById(R.id.amount);
                TextView TextDate = (TextView)view1.findViewById(R.id.date);
                TextView TextComment = (TextView)view1.findViewById(R.id.comment);

                //Log.i("Types",String.valueOf(itemListcur.get(icount)));

                TextType.setText(String.valueOf(itemListcur.get(icount).TypeToEnty()));
                edittask = (ImageView)view1.findViewById(R.id.edittask);
                deletetask =(ImageView)view1.findViewById(R.id.deletetask);
                TextAmount.setText(dataHistory.convert(String.valueOf(itemListcur.get(icount).amount)));
                TextDate.setText(itemListcur.get(icount).date);
                TextComment.setText(itemListcur.get(icount).commment);
                edittask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //detailFragment.onTap(0,itemListcur.get(icount).ID);
                        dataHistory.historyFragment.openDialog1(itemListcur.get(icount));

                    }
                });
                deletetask.setOnClickListener(new View.OnClickListener(){
                                                  @Override
                                                  public void onClick(View v) {
                                                      _detailFragment.onTap(1,itemListcur.get(icount).ID);
                                                      write_noti_data WriteNoti= new write_noti_data();
                                                      WriteNoti.create_noti("xoa",String.valueOf(itemListcur.get(icount)),dataHistory.historyFragment.getContext());
                                                      dataHistory.ProcessAccess(dataHistory.historyFragment.buttonc.getText().toString());
                                                      mydialog.dismiss();
                                                  }
                                              }
                );
                mydialog.show();
                //loadFragment(new detailFragment(itemListcur.get(icount)),R.id.containerItem);
            }
        });
        return convertView;
    }
}
