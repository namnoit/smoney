package com.example.smoney;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryFragment extends Fragment {
    int[] IMAGES={R.drawable.ic_history, R.drawable.ic_notification, R.drawable.ic_pie_chart, R.drawable.ic_pie_chart, R.drawable.ic_pie_chart,R.drawable.ic_pie_chart};
    String[] DayofWeek={"Thứ hai","Thứ ba","Thứ tư",};
    int[] Dayth={1,2,3};
     Button buttonc;
     Button buttonp;
     Button buttonf ;
    View _convertview=null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_history, container, false);
        ListView lviewday = view.findViewById(R.id.itemthesameday);
        CustomAdapterOneDay customAdapterOneDay = new CustomAdapterOneDay();
        lviewday.setAdapter(customAdapterOneDay);

        buttonc = (Button) view.findViewById(R.id.current);
        buttonp = (Button) view.findViewById(R.id.previous);
        buttonf = (Button) view.findViewById(R.id.following);
        buttonp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setButtonChange(true);
            }
        });
        buttonf.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                setButtonChange(false);
            }
        });
        System.out.print("THC");

        if(customAdapterOneDay._convertviews!=null) {
            buttonp.setText("NOPE");
            @SuppressLint("ResourceType") View tv = (View) customAdapterOneDay._convertviews.findViewById(10);
            if(tv==null){
                TextView tvs = (TextView)view.findViewById(R.id.contentitem);
                if(tvs!=null){
                    tvs.setText("1231251");
                }
            }
        }



        return view;
    }
    public void TapClick(View Args){
            System.out.print("Hello");
    }

    public void setButtonChange(boolean isPrevious){
        if (isPrevious){
            buttonf.setText(buttonc.getText().toString());
            buttonc.setText(buttonp.getText().toString());
            buttonp.setText(getTime(buttonp.getText().toString(),true));
        }else{
            buttonp.setText(buttonc.getText().toString());
            buttonc.setText(buttonf.getText().toString());
            buttonf.setText(getTime(buttonf.getText().toString(),false));
        }
    }
    public String getTime(String timestamp,boolean decrease){
        int month=Integer.parseInt(timestamp.split(" ")[0]);
        int year = Integer.parseInt(timestamp.split(" ")[1]);
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
        return Integer.toString(month)+" "+Integer.toString(year);
    }
    class ModelHistory{
        public void UpdatePage(String timestamp){
            if (timestamp=="12 2018"){
                System.out.print(timestamp);
            }
        }
    }
    class CustomAdapterOneDay extends BaseAdapter{
        public int height = 0;
        public View _convertviews;
        public View getConvertView() {
            View convertView = getLayoutInflater().inflate(R.layout.row_item,null);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.avt);
            TextView contentitem = (TextView)convertView.findViewById(R.id.contentitem);
            TextView price = (TextView)convertView.findViewById(R.id.price);
            imageView.setImageResource(IMAGES[1]);
            contentitem.setText("Dieu hoa");
            price.setText("456");
            return convertView;
        }
        @Override
        public int getCount() {
            return DayofWeek.length;
        }

        @Override
        public Object getItem(int position) {
            System.out.print(DayofWeek[position]);return null;
        }

        @Override
        public long getItemId(int position) {
            System.out.print(DayofWeek[position]);
            return 0;
        }

        @SuppressLint("ResourceType")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /*convertView = getLayoutInflater().inflate(R.layout.column,null);
            TextView Dayname= (TextView)convertView.findViewById(R.id.Dayname);
            TextView Daynumber = (TextView)convertView.findViewById(R.id.Daynumber);
            TextView Totalcost = (TextView)convertView.findViewById(R.id.Totalcost);
            Dayname.setText(DayofWeek[position]);
            Daynumber.setText(Integer.toString(Dayth[position]));
            Totalcost.setText("5.000.000");
            LinearLayout linearLayout = (LinearLayout)convertView.findViewById(R.id.listitem);
            View view = null;
            for(int i =0;i<4;i++){

                view=getConvertView();
                view.setId(i*10);
                linearLayout.addView(view);
            }
            view = (View)convertView.findViewById(10);
            TextView textView = (TextView) view.findViewById(R.id.contentitem);
            _convertviews=convertView;*/
            convertView = getLayoutInflater().inflate(R.layout.column,null);
            TextView Dayname= (TextView)convertView.findViewById(R.id.Dayname);
            TextView Daynumber = (TextView)convertView.findViewById(R.id.Daynumber);
            TextView Totalcost = (TextView)convertView.findViewById(R.id.Totalcost);
            Dayname.setText(DayofWeek[position]);
            Daynumber.setText(Integer.toString(Dayth[position]));
            Totalcost.setText("5.000.000");
            ListView lviewday = convertView.findViewById(R.id.listitem);

            CustomAdapter customAdapter = new CustomAdapter();
            lviewday.getLayoutParams().height=customAdapter.getCount()*170;
            lviewday.setAdapter(customAdapter);
            return convertView;
        }
    }
    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            System.out.print(DayofWeek[position]);return null;
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }

        @SuppressLint("ResourceType")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.row_item,null);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.avt);
            TextView contentitem = (TextView)convertView.findViewById(R.id.contentitem);
            TextView price = (TextView)convertView.findViewById(R.id.price);
            imageView.setImageResource(IMAGES[position]);
            contentitem.setText("Dieu hoa");
            price.setText("456");
            return convertView;
        }
    }
}
