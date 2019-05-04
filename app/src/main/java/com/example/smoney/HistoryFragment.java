package com.example.smoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class HistoryFragment extends Fragment {
    ImageButton add;
    Button editdemo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        add = view.findViewById(R.id.addButton);
        addNewRecord(add);
        editdemo = view.findViewById(R.id.exampleedit);
        addNewRecord1(editdemo);
        return view;
    }
    void addNewRecord(ImageButton add ){

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
    }
    public void openDialog(){
        AddinginfoDialog addinginfodialog = new AddinginfoDialog();
        addinginfodialog.showNow(getChildFragmentManager(),  " ");
    }
    void addNewRecord1(Button editdemo){

        editdemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog1();

            }
        });
    }
    public void openDialog1(){
        EditinginfoDialog editinginfodialog = new EditinginfoDialog();
        editinginfodialog.showNow(getChildFragmentManager(),  " ");
    }
}
