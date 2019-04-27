package com.example.smoney;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationsFragment extends Fragment {

    ImageButton back_btn;
    ImageButton help;
    static ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);




        back_btn = v.findViewById(R.id.backbtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        help = v.findViewById(R.id.thacmac);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ArrayList<noti_item> notiItems = new ArrayList<noti_item>();
        //ArrayAdapter<noti_item> adapter = new ArrayAdapter<noti_item>(getContext(),R.layout.row_item_dknoti,notiItems);
        ArAdapter adapter = new ArAdapter(getContext(),R.layout.row_item_dknoti,notiItems);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String tm = dateFormat.format(calendar.getTime());
        noti_item a = new noti_item(tm,"chi 50000 VND","mua gạo","1000000 VND");
        noti_item b = new noti_item(tm,"chi 4000 VND","mua kẹo","323536 VND");
        noti_item c = new noti_item(tm,"chi 45645 VND","hớt tóc","3564656 VND");
        noti_item d = new noti_item(tm,"chi 564 VND","ăn sáng","6854456 VND");
        notiItems.add(a);
        notiItems.add(b);
        notiItems.add(c);
        notiItems.add(d);
        notiItems.add(new noti_item(tm,"chi 4000 VND","mua kẹo","323536 VND"));
        notiItems.add(new noti_item(tm,"chi 4000 VND","mua kẹo","323536 VND"));
        notiItems.add(new noti_item(tm,"chi 4000 VND","mua kẹo","323536 VND"));
        notiItems.add(new noti_item(tm,"chi 4000 VND","mua kẹo","323536 VND"));
        notiItems.add(new noti_item(tm,"chi 4000 VND","mua kẹo","323536 VND"));
        lv = v.findViewById(R.id.lview);
        lv.setAdapter(adapter);

        return v;
    }

    public static class noti_item{
        private String time;
        private String action;
        private String sk;
        private String money;

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public noti_item(String time, String action, String sk, String money) {
            this.time = time;
            this.action = action;
            this.sk = sk;
            this.money = money;
        }
    }

    public static class ArAdapter extends ArrayAdapter<noti_item> {
        private Context context;
        private int resource;
        private ArrayList<noti_item> arNoti;

        public ArAdapter( Context context, int resource, ArrayList<noti_item> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.arNoti = objects;
        }



        @Override
        public View getView(int position,  View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item_dknoti,parent,false);
            TextView action_no = (TextView) convertView.findViewById(R.id.action_noti);
            TextView time = (TextView) convertView.findViewById(R.id.time_t);
            TextView skien = (TextView) convertView.findViewById(R.id.sk);
            TextView money = (TextView) convertView.findViewById(R.id.money);
            noti_item notiItem = arNoti.get(position);

            action_no.setText(notiItem.getAction());
            time.setText(notiItem.getTime());
            skien.setText(notiItem.getSk());
            money.setText(notiItem.getMoney());
            return convertView;
        }
    }
}
