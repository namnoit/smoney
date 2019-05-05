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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationsFragment extends Fragment {


    static ListView lv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);









        ArrayList<noti_item> notiItems = new ArrayList<noti_item>();
        //ArrayAdapter<noti_item> adapter = new ArrayAdapter<noti_item>(getContext(),R.layout.row_item_dknoti,notiItems);
        ArAdapter adapter = new ArAdapter(getContext(),R.layout.row_item_dknoti,notiItems);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String tm = dateFormat.format(calendar.getTime());
        noti_item a = new noti_item(tm,"chi 50000 VND","sức khỏe");
        noti_item b = new noti_item(tm,"chi 4000 VND","ăn uống");
        noti_item c = new noti_item(tm,"chi 45645 VND","xổ số");
        noti_item d = new noti_item(tm,"chi 564 VND","ăn uống");
        notiItems.add(a);
        notiItems.add(b);
        notiItems.add(c);
        notiItems.add(d);
        notiItems.add(new noti_item(tm,"chi 4000 VND","ăn uống"));
        notiItems.add(new noti_item(tm,"chi 4000 VND","sức khỏe"));
        notiItems.add(new noti_item(tm,"chi 4000 VND","ăn uống"));
        notiItems.add(new noti_item(tm,"chi 4000 VND","ăn uống"));
        notiItems.add(new noti_item(tm,"chi 4000 VND","ăn uống"));
        lv = v.findViewById(R.id.lview);
        lv.setAdapter(adapter);
        add_not(lv);
        return v;
    }

    public void add_not(ListView lv){
        try {

            InputStream in = getActivity().getAssets().open("notifi_dbs.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(in,"Unicode");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ArrayList<noti_item> notiItems = new ArrayList<noti_item>();
            ArAdapter adapter = new ArAdapter(getContext(),R.layout.row_item_dknoti,notiItems);
            String line = bufferedReader.readLine();
            String timet, action, sk;
            while (line != null){
                timet = line;
                action = bufferedReader.readLine();
                sk = bufferedReader.readLine();
                notiItems.add(new noti_item(timet,action,sk));
                line = bufferedReader.readLine();
            }

            lv.setAdapter(adapter);
        }
        catch (IOException ex){
            ex.printStackTrace();
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

            noti_item notiItem = arNoti.get(position);

            action_no.setText(notiItem.getAction());
            time.setText(notiItem.getTime());
            skien.setText(notiItem.getSk());

            return convertView;
        }
    }
}
