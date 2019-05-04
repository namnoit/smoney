package com.example.smoney;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class EditinginfoDialog extends AppCompatDialogFragment {
    Model model;
    ArrayList<Item> arr;
    EditText editTextMoney, editTextDate, editTextComment, editTextType, editTextWallet;
    long money;
    int type;
    String comment;
    String date;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_info, null);

        arr = new ArrayList<>();
        // arr = model.getInOut(strDateBegin,strDateEnd);
        for (int x = 0; x < arr.size(); x++){ // id
            money = arr.get(x).amount;
            type = arr.get(x).type;
            comment = arr.get(x).commment;
            date = arr.get(x).date;
        }

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
                        model.updateInOut(1, editTextType.getInputType(), editTextMoney.getInputType(), editTextDate.toString(), editTextComment.toString());

                    }
                });


        editTextMoney = view.findViewById(R.id.edit_numofmoney);
        String stmoney = Long.toString(money);
        editTextMoney.setText(stmoney);

        editTextType = view.findViewById(R.id.edit_category);
        String sttype = Integer.toString(type);
        editTextType.setText(sttype);

        editTextWallet = view.findViewById(R.id.edit_wallet);
        editTextDate = view.findViewById(R.id.edit_date);
        editTextDate.setText(date);
        editTextComment = view.findViewById(R.id.edit_note);
        editTextComment.setText(comment);
        return  builder.create();
    }
}
