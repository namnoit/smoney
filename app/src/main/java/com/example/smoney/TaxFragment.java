package com.example.smoney;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class TaxFragment extends Fragment {

    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;
    Button btnt1;
    Button btnt2;
    Button btnt3;
    Button btnt4;
    Button btnt5:
    Button btnt6;
    Button btnt7;
    Button btnt8;
    Button btnt9;
    Button btnt10;
    Button btnt11;
    Button btnt12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tax, container, false);
        getData(view);

        // Inflate the layout for this fragment
        return view;
    }

    public getData(View view){
        text1 = view.findViewById(R.id.khoangiamtru);
        text2 = view.findViewById(R.id.nguoiphuthuoc);
        text3 = view.findViewById(R.id.tongthunhap);
        btnt1 = view.findViewById(R.id.btnthang1);
        btnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 =
            }
        });
    }
}
