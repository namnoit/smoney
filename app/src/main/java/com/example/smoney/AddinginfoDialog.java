package com.example.smoney;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class AddinginfoDialog extends AppCompatDialogFragment{
    Model model;
    EditText editTextMoney, editTextDate, editTextComment, editTextType;
    HistoryFragment historyFragment;
    @SuppressLint("ValidFragment")

    public AddinginfoDialog(HistoryFragment _historyFragment) {
        historyFragment = _historyFragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_info, null);

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
                        model.addInOut(Integer.parseInt(editTextType.getText().toString()), Long.parseLong(editTextMoney.getText().toString()), editTextDate.getText().toString(), editTextComment.getText().toString());
                        write_noti_data WriteNoti= new write_noti_data();
                        historyFragment.dataHistory.ProcessAccess(historyFragment.buttonc.getText().toString());
                        if (Integer.parseInt(editTextType.getText().toString())<10) {
                            Item itemtemp= new Item();
                            WriteNoti.create_noti("thu", String.valueOf(itemtemp.TypeToEnty(Integer.parseInt(editTextType.getText().toString()))), getContext());
                        }else{
                            Item itemtemp= new Item();
                            WriteNoti.create_noti("chi", String.valueOf(itemtemp.TypeToEnty(Integer.parseInt(editTextType.getText().toString()))), getContext());
                        }

                    }
                });
        model = new Model(getActivity().getApplicationContext());
        editTextMoney = view.findViewById(R.id.edit_numofmoney);
        editTextDate = view.findViewById(R.id.edit_date);
        editTextComment = view.findViewById(R.id.edit_note);
        editTextType = view.findViewById(R.id.edit_category);
        return  builder.create();
    }
}
