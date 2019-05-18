package com.example.smoney;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

@SuppressLint("ValidFragment")
public class EditinginfoDialog extends AppCompatDialogFragment {
    Model model;
    Item item;
    EditText editTextMoney, editTextDate, editTextComment;

    Spinner spinner;
    int numcate;
    String [] array_cate = {"[Thu] Khác","[Thu] Lương", "[Thu] Buôn bán", "[Thu] Lotteria", "[Thu] Sở thích", "[Chi] Khác", "[Chi] Ăn uống", "[Chi] Sức khỏe", "[Chi] Lotteria", "[Chi] Di chuyển", "[Chi] Mua sắm", "[Chi] Hóa đơn", "[Chi] Điện thoại", "[Chi] Gas"};

    long money;
    int type;
    String comment;
    String date;
    ImageButton date_button;
    DatePickerDialog datePickerDialog;
    @SuppressLint("ValidFragment")

    public EditinginfoDialog(Item items){
        item=items;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_info, null);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, array_cate);
        spinner = view.findViewById(R.id.choose_cate);
        spinner.setAdapter(arrayAdapter);
        switch (spinner.getSelectedItemPosition()){
            case(5):
                numcate = 10;
                break;
            case(6):
                numcate = 11;
                break;
            case(7):
                numcate = 12;
                break;
            case(8):
                numcate = 13;
                break;
            case(9):
                numcate = 14;
            case(10):
                numcate = 15;
            case(11):
                numcate = 16;
            case(12):
                numcate = 17;
                break;
            case(13):
                numcate = 18;
            default:
                numcate = spinner.getSelectedItemPosition();
                break;
        }

        date_button = view.findViewById(R.id.id_date_button);
        adddate(date_button);

        money = item.amount;
        type = item.type;
        comment = item.commment;
        date = item.date;

        model = new Model(getActivity().getApplication());

        editTextMoney = view.findViewById(R.id.edit_numofmoney);
        String stmoney = Long.toString(money);
        editTextMoney.setText(stmoney);

        editTextDate = view.findViewById(R.id.edit_date);
        editTextDate.setText(date);

        editTextComment = view.findViewById(R.id.edit_note);
        editTextComment.setText(comment);

        builder.setView(view)
                .setTitle("Chỉnh sử thông tin")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ////////////////////////////////////////// id khi bam vao nut tren lich su
                        Log.i("iddangtim",String.valueOf(model.getInOut(item.ID)));
                        Log.i("iddangtim",String.valueOf(item.date));
                        model.updateInOut(item.ID, numcate, Long.parseLong(editTextMoney.getText().toString()), editTextDate.getText().toString(), editTextComment.getText().toString());
                        Log.i("idupdate",String.valueOf(model.getInOut(item.ID)));
                    }
                });
        return  builder.create();
    }
    void adddate(ImageButton date_button){
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar =Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                month = month +1;
                                String datec = year + "/" + month + "/" + dayOfMonth;
                                if(month < 10){
                                    if (dayOfMonth <10){
                                        datec = year + "/" + "0" + month + "/" + "0"+ dayOfMonth;
                                    }
                                    else{
                                        datec = year + "/" + "0" + month + "/" + dayOfMonth;
                                    }
                                }
                                editTextDate.setText(datec);
                            }
                        },year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
    }
}