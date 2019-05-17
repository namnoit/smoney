package com.example.smoney;

/*
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecycleItem extends RecyclerView.Adapter<RecycleItem.ViewHolder>
{
    List<Item> ItemListPrivate = new ArrayList<>();
    Context context;
    public RecycleItem(List<Item> item) {
        ItemListPrivate=item;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView contentitem ;
        TextView price;
        public ViewHolder(View itemView){
            super(itemView);
           imageView = (ImageView)itemView.findViewById(R.id.avt);
           contentitem = (TextView)itemView.findViewById(R.id.contentitem);
           price = (TextView)itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,contentitem.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public RecycleItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.row_item,viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.contentitem.setTextColor(ItemListPrivate.get(position).setColor());
        viewHolder.price.setTextColor(ItemListPrivate.get(position).setColor());
        viewHolder.imageView.setImageResource(ItemListPrivate.get(position).TypeToImage[ItemListPrivate.get(position).type]);
        viewHolder.contentitem.setText(String.valueOf(ItemListPrivate.get(position).TypeToEnty()));
        viewHolder.price.setText(HistoryFragment.convert(String.valueOf(ItemListPrivate.get(position).amount)));
    }


    @Override
    public int getItemCount() {
        return ItemListPrivate.size();
    }


}*/
