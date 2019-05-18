package com.example.smoney.ContainClass;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smoney.Item;
import com.example.smoney.R;
import com.example.smoney.write_noti_data;

public class DialogIDE {
    DataHistory dataHistory;
    public  DialogIDE(DataHistory _dataHistory){
        dataHistory=_dataHistory;
    }
    public void EventForButton(ImageView edittask, ImageView deletetask, final Item ItemIsPointed, final Dialog dialogOfDelAndEditFunc){
        edittask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //detailFragment.onTap(0,itemListcur.get(icount).ID);
                dataHistory.historyFragment.openDialog1(ItemIsPointed);

            }
        });
        deletetask.setOnClickListener(new View.OnClickListener(){
                                          @Override
                                          public void onClick(View v) {
                                              final detailFragment _detailFragment=new detailFragment(dataHistory.historyFragment.getContext());
                                              _detailFragment.onTap(1,ItemIsPointed.ID);
                                              write_noti_data WriteNoti= new write_noti_data();
                                              WriteNoti.create_noti("xoa", String.valueOf(ItemIsPointed.TypeToEnty()), dataHistory.historyFragment.getContext());
                                              dataHistory.ProcessAccess(dataHistory.historyFragment.buttonc.getText().toString());
                                              dialogOfDelAndEditFunc.dismiss();
                                          }
                                      }
        );

    }
    public void OpenDialog(Item ItemIsPointed){
        final Dialog dialogOfDelAndEditFunc =new Dialog(dataHistory.historyFragment.getContext());
        ImageView edittask;
        ImageView deletetask;
        final detailFragment _detailFragment=new detailFragment(dataHistory.historyFragment.getContext());
        View viewOfDetail = dataHistory.historyFragment.getLayoutInflater().inflate(R.layout.fragment_detail,null);
        dialogOfDelAndEditFunc.setContentView(viewOfDetail);

        TextView TextType = (TextView) viewOfDetail.findViewById(R.id.type);
        TextView TextAmount = (TextView) viewOfDetail.findViewById(R.id.amount);
        TextView TextDate = (TextView)viewOfDetail.findViewById(R.id.date);
        TextView TextComment = (TextView)viewOfDetail.findViewById(R.id.comment);

        //Log.i("Types",String.valueOf(itemListcur.get(icount)));

        TextType.setText(String.valueOf(ItemIsPointed.TypeToEnty()));
        edittask = (ImageView)viewOfDetail.findViewById(R.id.edittask);
        deletetask =(ImageView)viewOfDetail.findViewById(R.id.deletetask);
        EventForButton(edittask,deletetask,ItemIsPointed,dialogOfDelAndEditFunc);

        TextAmount.setText(dataHistory.convert(String.valueOf(ItemIsPointed.amount)));
        TextDate.setText(ItemIsPointed.date);
        TextComment.setText(ItemIsPointed.commment);
        dialogOfDelAndEditFunc.show();

    }
}
