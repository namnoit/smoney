package com.example.smoney;

import android.content.Context;
import android.content.ContextWrapper;
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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NotificationsFragment extends Fragment {


    static ListView lv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);
        //ArrayList<noti_item> notiItems = new ArrayList<noti_item>();
        //ArrayAdapter<noti_item> adapter = new ArrayAdapter<noti_item>(getContext(),R.layout.row_item_dknoti,notiItems);
        //ArAdapter adapter = new ArAdapter(getContext(),R.layout.row_item_dknoti,notiItems);
        lv = v.findViewById(R.id.lview);
        //lv.setAdapter(adapter);
        //Toast.makeText(getActivity(),adc.a,Toast.LENGTH_LONG).show();
        add_not(lv);
        return v;
    }

    public void add_not(ListView lv){
        try {
            write_noti_data adc = new write_noti_data();
            ContextWrapper contextWrapper = new ContextWrapper(getActivity().getApplicationContext());
            String filename = "internalStorage";
            String filepath = "ThuMucCuaToi";
            File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
            adc.mfile = new File(directory, filename);
            FileInputStream in = new FileInputStream(adc.mfile + "/TextFile.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(in,"UTF-8");
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
            Collections.reverse(notiItems);
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
