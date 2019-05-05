package com.example.smoney;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class EditinginfoDialog extends AppCompatDialogFragment {
    Model model;
    Item item;
    EditText editTextMoney, editTextDate, editTextComment, editTextType, editTextWallet;
    long money;
    int type;
    String comment;
    String date;
    @SuppressLint("ValidFragment")

    public EditinginfoDialog(Item items){
        item=items;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_info, null);

        money = item.amount;
        type = item.type;
        comment = item.commment;
        date = item.date;

        model = new Model(getActivity().getApplication());
        editTextMoney = view.findViewById(R.id.edit_numofmoney);
        String stmoney = Long.toString(money);
        editTextMoney.setText(stmoney);

        editTextType = view.findViewById(R.id.edit_category);
        String sttype = Integer.toString(type);
        editTextType.setText(sttype);

        editTextDate = view.findViewById(R.id.edit_date);
        editTextDate.setText(date);
        editTextComment = view.findViewById(R.id.edit_note);
        editTextComment.setText(comment);
        builder.setView(view)
                .setTitle("Thông tin")
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
                        model.updateInOut(item.ID, Integer.parseInt(editTextType.getText().toString()), Long.parseLong(editTextMoney.getText().toString()), editTextDate.getText().toString(), editTextComment.getText().toString());
                        Log.i("idupdate",String.valueOf(model.getInOut(item.ID)));
                    }
                });



        return  builder.create();
    }
}
