package com.example.smoney;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaxFragment extends Fragment {
    private static final String TAG = "TaxFragment";
    private Dialog dialog;
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
    Button btnStart;
    float tongthunhap1 = 0;
    int nguoiphuthuoc1 = 0;
    float khoangiamtru1 = 0;
    float ketqua = 0;
    String temp ;
    private Date begin;
    private Date end;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tax, container, false);
        text4 = view.findViewById(R.id.thueTNCN);

        getData(view);//truyen het data vao du lieu
        //processData(view);
        // Inflate the layout for this fragment
        return view;
    }

    public void getData(View view) {
        text1 = view.findViewById(R.id.khoangiamtru);
        text2 = view.findViewById(R.id.nguoiphuthuoc);
        text3 = view.findViewById(R.id.tongthunhap);
        text4 = view.findViewById(R.id.thueTNCN);
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
        //btnt1 = view.findViewById(R.id.btnthang1);
        btnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Model model = new Model(view.getContext());
                begin = new Date(2019/01/01);
                end = new Date(2019/01/31);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();

                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {

                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 1 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/02/01);
                end = new Date(2019/01/28);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }

                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 2 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/03/01);
                end = new Date(2019/03/31);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }

                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 3 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/04/01);
                end = new Date(2019/04/30);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 4 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/05/01);
                end = new Date(2019/05/31);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 5 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/06/01);
                end = new Date(2019/06/30);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 6 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/07/01);
                end = new Date(2019/07/31);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 7 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/8/01);
                end = new Date(2019/8/31);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 8 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/9/01);
                end = new Date(2019/9/30);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 9 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/10/01);
                end = new Date(2019/10/30);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 10 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/11/01);
                end = new Date(2019/8/30);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 11 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

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
                begin = new Date(2019/12/01);
                end = new Date(2019/12/31);
                ArrayList<Item> arr = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                strDateBegin = dateFormat.format(begin);
                arr = model.getInOut(strDateBegin,strDateEnd);
                int in = 0;
                int out = 0;
                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }

                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                if (in != 0){
                    text1.setText(Float.toString(in));
                    float hi = generateTNCN(in, nguoiphuthuoc1, khoangiamtru1);
                    temp = Float.toString(hi);
                    text4.setText(temp);
                }
                else {
                    //thong bao la thang nay chua co thu nhap
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Thông báo");
                    builderSingle.setMessage("Tháng 12 chưa có thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    builderSingle.show();
                }


            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String khoangiamtru = text1.getText().toString();
                String nguoiphuthuoc = text2.getText().toString();
                String tongthunhap = text3.getText().toString();
                if(TextUtils.isEmpty(text1.getText().toString()))
                {
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

                                }
                            });
                    builderSingle.show();

                    khoangiamtru1 = 0;
                } else {
                    khoangiamtru1 = Float.valueOf(khoangiamtru);
                }
                if(TextUtils.isEmpty(text2.getText().toString()))
                {
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

                                }
                            });
                    builderSingle.show();

                    nguoiphuthuoc1 = 0;
                } else {
                    nguoiphuthuoc1 = Integer.parseInt(nguoiphuthuoc);
                }
                if(TextUtils.isEmpty(text3.getText().toString()))
                {
                    //neu de trong thi xu ly nhu the nao
                    final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(view.getContext());
                    builderSingle.setIcon(R.mipmap.ic_launcher);
                    builderSingle.setTitle("Nhắc nhở");
                    builderSingle.setMessage("Vui lòng nhập mục Tổng thu nhập");
                    builderSingle.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    builderSingle.show();

                    tongthunhap1 = 0;
                } else {
                    tongthunhap1 = Float.valueOf(tongthunhap);
                }
                float hi = generateTNCN(tongthunhap1, nguoiphuthuoc1, khoangiamtru1);
                temp = Float.toString(hi);
                text4.setText(temp);

            }
        });


        //float ttn = Float.valueOf(thueTNCN);
        ketqua = generateTNCN(tongthunhap1, nguoiphuthuoc1, khoangiamtru1);


        //text4.setText(khoangiamtru);
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

        return thueTNCNphainop;
    }
}
