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
    Button btnt5;
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
        processData(view);
        // Inflate the layout for this fragment
        return view;
    }

    public void getData(View view) {
        text1 = view.findViewById(R.id.khoangiamtru);
        text2 = view.findViewById(R.id.nguoiphuthuoc);
        text3 = view.findViewById(R.id.tongthunhap);
        text4 = view.findViewById(R.id.thueTNCN);

        btnt1 = view.findViewById(R.id.btnthang1);
        //btnt1.setOnClickListener(new View.OnClickListener() {
        String khoangiamtru = text1.getText().toString();
        String nguoiphuthuoc = text2.getText().toString();
        String tongthunhap = text3.getText().toString();
        String thueTNCN = text4.getText().toString();

        text4.setText(khoangiamtru);
    }

    public void generateTNCNt1(View view){
        text1 = view.findViewById(R.id.khoangiamtru);
        text2 = view.findViewById(R.id.nguoiphuthuoc);
        text3 = view.findViewById(R.id.tongthunhap);
        text4 = view.findViewById(R.id.thueTNCN);
        //btnt1.setOnClickListener(new View.OnClickListener() {
        String khoangiamtru = text1.getText().toString();
        String nguoiphuthuoc = text2.getText().toString();
        String tongthunhap = text3.getText().toString();
        String thueTNCN = text4.getText().toString();
        if (khoangiamtru == "" || nguoiphuthuoc == "" || tongthunhap == ""){
            text4.setText("unvalid input");
        }
        float kgt = Float.valueOf(khoangiamtru);
        float npt = Float.valueOf(nguoiphuthuoc);
        float ttn = Float.valueOf(thueTNCN);
        float result = generateTNCN(ttn, npt, kgt);

        String hi = Float.toString(result);
        text4.setText(hi);
        //Model model = new Model(this.getContext());
    }



    public void processData(View view){

    }

    public float generateTNCN(float tongthunhap, int nguoiphuthuoc, float khoangiamtru){
        //Buoc 1: thu nhap chiu thue = tong thu nhap - cac khoan mien thue
        //Buoc 2: thu nhap tinh thue = thue nhap chiu thue - cac khoan giam tru
        //Buoc 3: thu TNCN phai nop = thu nhap tinh thue * thue suat
        float thunhapchiuthue = 0;
        float cackhoanmienthue = 0;
        float thunhaptinhthue = 0;
        float cackhoangiamtru = 0;
        float thueTNCNphainop = 0;
        float thuesuat = 0;
        //Buoc 1: tinh tong thu nhap
        //Buoc 2: xac dinh cac khoan duoc mien thue TNCN
        //Buoc 3: tinh thu nhap chiu thue theo ct1
        //Buoc 4: xac dinh cac khoan giam tru
        //Buoc 5: tinh thu nhap tinh thue theo ct2
        //Buoc 6: tinh thue TNCN phai nop theo ct3
        thunhapchiuthue = tongthunhap - khoangiamtru;
        thunhaptinhthue = thunhapchiuthue - khoangiamtru;
        if (thunhaptinhthue < 5000000){
            thuesuat = 5/100;
        }
        else if (thunhaptinhthue >= 5000000 && thunhapchiuthue < 10000000){
            thuesuat = 10/100;
        }
        else if (thunhaptinhthue >= 10000000 &&  thunhaptinhthue < 18000000){
            thuesuat = 15/100;
        }
        else if (thunhaptinhthue >= 18000000 && thunhaptinhthue < 32000000){
            thuesuat = 20/100;
        }
        else  if (thunhaptinhthue >= 32000000 && thunhaptinhthue < 52000000){
            thuesuat = 25/100;
        }
        else if (thunhaptinhthue >= 52000000 && thunhaptinhthue < 80000000){
            thuesuat = 30/100;
        }
        else {
            thuesuat = 35/100;
        }
        thueTNCNphainop = thunhaptinhthue * thuesuat;

        return thueTNCNphainop;
    }
}
