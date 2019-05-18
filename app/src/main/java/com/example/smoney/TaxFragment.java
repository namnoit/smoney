package com.example.smoney;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaxFragment extends Fragment {
    private static final String TAG = "TaxFragment";
    private Dialog dialog;
    public float _tongthunhap_Start_ = 0;
    EditText text_khoangiamtru;
    EditText text_nguoiphuthuoc;
    EditText text_tongthunhap;
    EditText text_thueTNCN;
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
    Button btnStart;
    float _tongthunhapData_ = 0;
    float _nguoiphuthuocData_ = 0;
    float _khoangiamtruData_ = 0;
    float ketqua = 0;
    String temp ;
    private Date begin;
    private Date end;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tax, container, false);
//        text_thueTNCN = view.findViewById(R.id.thueTNCN);

        getData(view);//truyen het data vao du lieu
        //processData(view);
        // Inflate the layout for this fragment
        return view;
    }

    public void getData(View view) {
        text_khoangiamtru = view.findViewById(R.id.khoangiamtru);
        text_nguoiphuthuoc = view.findViewById(R.id.nguoiphuthuoc);
        text_tongthunhap = view.findViewById(R.id.tongthunhap);
        text_thueTNCN = view.findViewById(R.id.thueTNCN);
        btnt1 = view.findViewById(R.id.btnthang1);
        btnt2 = view.findViewById(R.id.btnthang2);
        btnt3 = view.findViewById(R.id.btnthang3);
        btnt4 = view.findViewById(R.id.btnthang4);
        btnt5 = view.findViewById(R.id.btnthang5);
        btnt6 = view.findViewById(R.id.btnthang6);
        btnt7 = view.findViewById(R.id.btnthang7);
        btnt8 = view.findViewById(R.id.btnthang8);
        btnt9 = view.findViewById(R.id.btnthang9);
        btnt10 = view.findViewById(R.id.btnthang10);
        btnt11 = view.findViewById(R.id.btnthang11);
        btnt12 = view.findViewById(R.id.btnthang12);
        btnStart = view.findViewById(R.id.btnStart);

        btnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/01/01","2019/01/31");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 1 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");
                                    text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/02/01","2019/02/28");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 2 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/03/01","2019/03/31");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 3 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/04/01","2019/04/30");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 4 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/05/01","2019/05/31");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                    if (in != 0){
                        _tongthunhap_Start_ = in;
                        if (in >= 1000000){
                            float _round_ = (float) in/1000000;
                            String output = Float.toString(_round_);
                            output += " triệu ";
                            text_tongthunhap.setText(output);
                        }
                        else {
                            text_tongthunhap.setText(Float.toString(in));
                        }
                    }
                    else {
                        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                        builderSingle.setIcon(R.mipmap.ic_launcher);
                        builderSingle.setTitle("Thông báo");
                        builderSingle.setMessage("Tháng 5 chưa có thu nhập");
                        builderSingle.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        text_tongthunhap.setText("");text_thueTNCN.setText("");
                                    }
                                });
                        builderSingle.show();
                    }
            }
        });

        btnt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/06/01","2019/06/30");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 6 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/07/01","2019/07/31");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 7 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/08/01","2019/08/31");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 8 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }


            }
        });

        btnt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/09/01","2019/09/30");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 9 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/10/01","2019/10/31");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 10 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/11/01","2019/11/30");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 11 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(view.getContext());
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                arr = model.getInOut("2019/12/01","2019/12/31");
                float in = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                }
                if (in != 0){
                    _tongthunhap_Start_ = in;
                    if (in >= 1000000){
                        float _round_ = (float) in/1000000;
                        String output = Float.toString(_round_);
                        output += " triệu ";
                        text_tongthunhap.setText(output);
                    }
                    else {
                        text_tongthunhap.setText(Float.toString(in));
                    }
                }
                else {
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 12 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");text_thueTNCN.setText("");
                                }
                            });
                    builderSingle.show();
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_tongthunhap_Start_ == 0 || TextUtils.isEmpty(text_tongthunhap.getText().toString())){
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Nhắc nhở");
                    builderSingle.setMessage("Vui lòng chọn tháng");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_tongthunhap.setText("");
                                }
                            });
                    builderSingle.show();
                }
                else if(TextUtils.isEmpty(text_nguoiphuthuoc.getText().toString())) {
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Nhắc nhở");
                    builderSingle.setMessage("Vui lòng nhập mục Người phụ thuộc");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_nguoiphuthuoc.setText("");
                                }
                            });
                    builderSingle.show();
                }
                else if(TextUtils.isEmpty(text_khoangiamtru.getText().toString())) {
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Nhắc nhở");
                    builderSingle.setMessage("Vui lòng nhập mục Khoản giảm trừ");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    text_khoangiamtru.setText("");
                                }
                            });
                    builderSingle.show();
                }
                else {
                    float hi = generateTNCN(_tongthunhap_Start_, _nguoiphuthuocData_, _khoangiamtruData_);
                    temp = Float.toString(hi);
                    text_thueTNCN.setText(temp);
                }
            }
        });
    }

    public float generateTNCN(float tongthunhap, float nguoiphuthuoc, float khoangiamtru){
        //Buoc 1: thu nhap chiu thue = tong thu nhap - cac khoan mien thue
        //Buoc 2: thu nhap tinh thue = thue nhap chiu thue - cac khoan giam tru
        //Buoc 3: thu TNCN phai nop = thu nhap tinh thue * thue suat
        float thunhapchiuthue = 0;
        float thunhaptinhthue = 0;
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
            thuesuat = (float) 5/100;
        }
        else if (thunhaptinhthue >= 5000000 && thunhapchiuthue < 10000000){
            thuesuat = (float)10/100;
        }
        else if (thunhaptinhthue >= 10000000 &&  thunhaptinhthue < 18000000){
            thuesuat = (float) 15/100;
        }
        else if (thunhaptinhthue >= 18000000 && thunhaptinhthue < 32000000){
            thuesuat = (float) 20/100;
        }
        else  if (thunhaptinhthue >= 32000000 && thunhaptinhthue < 52000000){
            thuesuat = (float) 25/100;
        }
        else if (thunhaptinhthue >= 52000000 && thunhaptinhthue < 80000000){
            thuesuat = (float) 30/100;
        }
        else {
            thuesuat = (float) 35/100;
        }
        thueTNCNphainop = thunhaptinhthue * thuesuat;

        return thueTNCNphainop - nguoiphuthuoc * 150000;
    }
}
