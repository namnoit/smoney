package com.example.smoney.ContainClass;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smoney.HistoryFragment;
import com.example.smoney.Item;
import com.example.smoney.R;

import java.util.List;

public class CustomAdapterView extends BaseAdapter {
    DataHistory dataHistory;
    HistoryFragment historyFragment;
    private List<Item> ItemListPrivate;
    public CustomAdapterView(List<Item> itemList,DataHistory _dataHistory){
        ItemListPrivate=itemList;
        dataHistory=_dataHistory;
    }


    @Override
    public int getCount() {
        return ItemListPrivate.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("Thucnull",String.valueOf(dataHistory));
        convertView = dataHistory.historyFragment.getLayoutInflater().inflate(R.layout.row_item,null);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.avt);
        TextView contentitem = (TextView)convertView.findViewById(R.id.contentitem);
        TextView price = (TextView)convertView.findViewById(R.id.price);
        //Picasso.with(getContext()).load(ItemListPrivate.get(position).TypeToImage[ItemListPrivate.get(position).type]).into( imageView);

        contentitem.setTextColor(ItemListPrivate.get(position).setColor());
        price.setTextColor(ItemListPrivate.get(position).setColor());
        Log.i("heighta",String.valueOf(convertView.getMeasuredHeight()));
        imageView.setImageResource(ItemListPrivate.get(position).TypeToImage[ItemListPrivate.get(position).type]);
        contentitem.setText(String.valueOf(ItemListPrivate.get(position).TypeToEnty()));
        price.setText(dataHistory.convert(String.valueOf(ItemListPrivate.get(position).amount)));
        return convertView;
    }
}
