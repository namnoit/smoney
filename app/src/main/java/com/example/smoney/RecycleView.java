
package com.example.smoney;
/*
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class RecycleView extends RecyclerView.Adapter<RecycleView.ViewHolder>
{
    LinkedList<List<Item>> itemLinkedList;
    Context context;
    RecycleItem rItem;
    RecycleView rView;
    RecyclerView recyclerView;

    public RecycleView(LinkedList<List<Item>> _itemLinkedList,Context _context) {
        Log.i("ContextThuc",String.valueOf(Context.MODE_APPEND));
        itemLinkedList=_itemLinkedList;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void InitAdapterArea(List<Item> item){
        rItem = new RecycleItem(item);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rItem);
        recyclerView.setHasFixedSize(false);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Dayname;
        TextView Totalout;
        TextView Totalcost;
        public ViewHolder(View itemView){
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.listitem);
            Dayname= (TextView)itemView.findViewById(R.id.Dayname);
            Totalout = (TextView)itemView.findViewById(R.id.Daynumber);
            Totalcost = (TextView)itemView.findViewById(R.id.Totalcost);
        }
    }

    @NonNull
    @Override
    public RecycleView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.column,viewGroup,false);
        setContext(viewGroup.getContext());


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView.ViewHolder viewHolder, int position) {
       viewHolder.Dayname.setText(itemLinkedList.get(position).get(0).date.split("/")[2]);;
        int totalpos=0;
        int totalneg=0;
        for(int i=0;i<itemLinkedList.get(position).size();i++){
            if (itemLinkedList.get(position).get(i).type<=4) {
                totalpos += itemLinkedList.get(position).get(i).amount;
            }else{
                totalneg +=itemLinkedList.get(position).get(i).amount;
            }
        }
        viewHolder.Totalcost.setText(HistoryFragment.convert(String.valueOf(totalneg)));
        //Totalcost.setTextColor(itemLinkedList.get(position).get(0).setColor(1));
        viewHolder.Totalout.setText(HistoryFragment.convert(String.valueOf(totalpos)));
        viewHolder.Totalout.setTextColor(itemLinkedList.get(position).get(0).setColor(0));
        InitAdapterArea(itemLinkedList.get(position));
    }
    @Override
    public int getItemCount() {
        return itemLinkedList.size();
    }


}
*/