package com.example.smoney;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HistoryFragment extends Fragment {
    String[] DayofWeek={"Thứ hai","Thứ ba","Thứ tư",};
    List listitme= new ArrayList();
    LinkedList<List<Item>> itemLinkedList = new LinkedList<>();

    int[] Dayth={1,2,3};
     static Button buttonc;
     static boolean Statusbefore=false;
     static String timestamp="";
     ImageView buttonp;
     ImageView buttonf ;
     TextView moneyout;
     TextView moneyin;
     TextView moneyremain;
    ListView lviewday=null;
    View _convertview=null;
    FloatingActionButton fab;

    Context context;
    public HistoryFragment(){
        ListView lviewday=null;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @SuppressLint("ResourceType")
    public void loadFragment(Fragment fragment,int id){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public String getTimeCurrent(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return String.valueOf(dateFormat.format(date)).split(" ")[0]; //2016/11/16 12:08:43
    }
    public void openDialog1(Item item){
        EditinginfoDialog editinginfodialog = new EditinginfoDialog(item);
        editinginfodialog.showNow(getChildFragmentManager(),  " ");
    }
    public void openDialog(){
        AddinginfoDialog addinginfoDialog = new AddinginfoDialog();
        addinginfoDialog.showNow(getChildFragmentManager(),  " ");
    }
    public void onInit(){
        if (Statusbefore==false){
            String times=String.valueOf(getTimeCurrent().split("/")[1])+"/"+getTimeCurrent().split("/")[2];
            buttonc.setText(times);
        }else
        buttonc.setText(timestamp);
    }
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {



        View view;
        view = inflater.inflate(R.layout.fragment_history, container, false);



        buttonc = (Button) view.findViewById(R.id.current);
        buttonp = (ImageView) view.findViewById(R.id.previous);
        buttonf = (ImageView) view.findViewById(R.id.following);
        onInit();
        lviewday = view.findViewById(R.id.itemthesameday);
        moneyout=(TextView)view.findViewById(R.id.money_out);
        moneyin = (TextView)view.findViewById(R.id.money_in);
        moneyremain=(TextView)view.findViewById(R.id.money_remain);
        fab = view.findViewById(R.id.fab);
        buttonp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setButtonChange(true);
            }
        });
        buttonf.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                setButtonChange(false);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        ProcessAccess(buttonc.getText().toString());
        System.out.print("THC");


        return view;
    }
    public void upgradeinfo(String code,String info){
        info.lastIndexOf(info);
        switch (code){
            case "0":
                moneyout.setText(info);
                break;
            case "1":
                moneyin.setText(info);
                break;
            case "2":
                moneyremain.setText(info);
                break;
        }
    }
    public static String convert(String str){
        int value=Integer.parseInt(str);
        //kiem tra so <0
        if (str.length()<4)
            return str;
        if (value<0){
            str=str.split("-")[1];
        }
        //dem so cum 3
        int numb=str.length();
        int dart = numb / 3;
        if (numb%3!=0)dart+=1;
        int i=0;
        String strnew="";
        while(i<dart){
            int k=str.length()-3*(i+1);
            if(k<0){break;}
            if(strnew!=""){
                strnew=str.substring(k,k+3)+"."+strnew;
                System.out.print(strnew+"\n");
            }else{
                strnew=str.substring(k,k+3);
                System.out.print(strnew+"\n");
            }
            ++i;
        }
        if (numb%3!=0)
        strnew = str.substring(0,str.length()-3*(i))+"."+strnew;
        //kiem tra so am
        if (value<0)
            return "-"+strnew;
        return strnew;
    }
    //Buntton vao theo thoi diem
    public void setButtonChange(boolean isPrevious){
        if (isPrevious){
            String daterate=getTime(buttonc.getText().toString(),true);
            ProcessAccess(daterate);
            buttonc.setText(daterate);
            timestamp=daterate;
            Statusbefore=true;
        }else{
            String daterate=getTime(buttonc.getText().toString(),false);
            ProcessAccess(daterate);
            buttonc.setText(daterate);
            timestamp=daterate;
            Statusbefore=true;
        }

    }
    public void ProcessAccess(String durationtime){
        String year = durationtime.split("/")[1];
        String month= durationtime.split("/")[0];
        Model model = new Model(getContext());
       // Model model = new Model(getContext());


        //model.updateInOut(0,0,200000,"2019/05/10","LV");
        //System.out.print(model.getInOut("2019//05//01","2019//05//31"));

        itemLinkedList=new LinkedList<>();
        itemLinkedList.clear();
        int totalinmonth=0;
        int totaloutmont=0;
        String startdate= year+"/"+month+"/01";
        String endate= year+"/"+month+"/31";

        //listterm
        List<Item>itemList=new ArrayList<Item>();
        for(int i=0;i<19;i++){
            if(i!=5 && i!=6 && i!=7&& i!=8&&i!=9) {
                Item item = new Item(i, i, i * 2000000, "2019/04/03", "Gucci");
                //model.addInOut(i, i * 2000000, "2019/04/03", "Gucci");
            }

        }
        for(int i=0;i<19;i++){
            if(i!=5 && i!=6 && i!=7&& i!=8&&i!=9) {
                Item item = new Item(i, i, i * 2000000, "2019/05/01", "LV");
                //model.addInOut(i, i * 2000000, "2019/05/03", "Gucci");
            }

        }
        Log.i("datedata",startdate);
        itemList=model.getInOut(startdate,endate);
        //Log.i("countNote",String.valueOf(model.getItem(0).size()));
        String currentdate;
        int i=0;

        while(true){
            if (i==itemList.size())break;
            List<Item>itemListbygroup=new ArrayList<Item>();
            itemListbygroup.add(itemList.get(i));
            while(i<=itemList.size()-1){
                if (itemList.get(i).type<=4){
                    totalinmonth += itemList.get(i).amount;
                }else{
                    totaloutmont += itemList.get(i).amount;
                }
                if(i+1<=itemList.size()-1 && itemList.get(i).date.equals(itemList.get(i+1).date)){
                    itemListbygroup.add(itemList.get(i+1));
                    i++;
                }else {
                    i++;
                    break;
                }
            }
            itemLinkedList.add(itemListbygroup);
        }
        upgradeinfo("0",convert(String.valueOf(totaloutmont)));
        upgradeinfo("1",convert(String.valueOf(totalinmonth)));
        upgradeinfo("2",convert(String.valueOf(totalinmonth-totaloutmont)));
        if(totalinmonth !=0 ||totaloutmont!=0) {
            CustomAdapterOneDay customAdapterOneDay = new CustomAdapterOneDay(1, 2);
            lviewday.setAdapter(customAdapterOneDay);
            Log.i("setnull1","OKe");
        }else{
            lviewday.setAdapter(null);
            Log.i("setnull","OKe");
        }

    }
    public String getTime(String timestamp,boolean decrease){
        int month=Integer.parseInt(timestamp.split("/")[0]);
        int year = Integer.parseInt(timestamp.split("/")[1]);
        if (decrease){
            if (month-1==0){
                month=12;
                year=year-1;
            }else{
                month=month-1;
                year=year;
            }

        }else{
            if (month+1>12){
                month=1;
                year=year+1;
            }else{
                month=month+1;
                year=year;
            }
        }
        if(month<10)
        return "0"+Integer.toString(month)+"/"+Integer.toString(year);
        return Integer.toString(month)+"/"+Integer.toString(year);
    }
    class CustomAdapterOneDay extends BaseAdapter{
        CustomAdapterOneDay(int month,int year){

        }
        public int height = 0;
        public View _convertviews;

        @Override
        public int getCount() {
            return itemLinkedList.size();
        }

        @Override
        public Object getItem(int position) {
            System.out.print(DayofWeek[position]);return null;
        }

        @Override
        public long getItemId(int position) {
            System.out.print(DayofWeek[position]);
            return 0;
        }

        @SuppressLint("ResourceType")
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Log.i("setnull","listview");
            convertView = getLayoutInflater().inflate(R.layout.column,null);
            TextView Dayname= (TextView)convertView.findViewById(R.id.Dayname);
            TextView Totalout = (TextView)convertView.findViewById(R.id.Daynumber);
            final TextView Totalcost = (TextView)convertView.findViewById(R.id.Totalcost);
            Dayname.setText(itemLinkedList.get(position).get(0).date.split("/")[2]);
            int totalpos=0;
            int totalneg=0;
            for(int i=0;i<itemLinkedList.get(position).size();i++){
                if (itemLinkedList.get(position).get(i).type<=4) {
                    totalpos += itemLinkedList.get(position).get(i).amount;
                }else{
                    totalneg +=itemLinkedList.get(position).get(i).amount;
                }
            }
            Totalcost.setText(convert(String.valueOf(totalneg)));
            //Totalcost.setTextColor(itemLinkedList.get(position).get(0).setColor(1));
            Totalout.setText(convert(String.valueOf(totalpos)));
            Totalout.setTextColor(itemLinkedList.get(position).get(0).setColor(0));
            ListView lviewday = convertView.findViewById(R.id.listitem);

            CustomAdapterView customAdapterView = new CustomAdapterView(itemLinkedList.get(position));
            final List<Item>itemListcur=itemLinkedList.get(position);
            //customAdapterView.getCount()*
            final float scale = getResources().getDisplayMetrics().density;
            lviewday.getLayoutParams().height=(int)((customAdapterView.getCount()+1)*(61*scale));
            lviewday.setAdapter(customAdapterView);
            lviewday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int icount, long id) {
                    final Dialog mydialog =new Dialog(getContext());
                    ImageView edittask;
                    ImageView deletetask;
                    final detailFragment _detailFragment=new detailFragment(getContext());
                    View view1 = getLayoutInflater().inflate(R.layout.fragment_detail,null);
                    mydialog.setContentView(view1);

                    TextView TextType = (TextView) view1.findViewById(R.id.type);
                    TextView TextAmount = (TextView) view1.findViewById(R.id.amount);
                    TextView TextDate = (TextView)view1.findViewById(R.id.date);
                    TextView TextComment = (TextView)view1.findViewById(R.id.comment);

                    //Log.i("Types",String.valueOf(itemListcur.get(icount)));

                    TextType.setText(String.valueOf(itemListcur.get(icount).TypeToEnty()));
                    edittask = (ImageView)view1.findViewById(R.id.edittask);
                    deletetask =(ImageView)view1.findViewById(R.id.deletetask);
                    TextAmount.setText(HistoryFragment.convert(String.valueOf(itemListcur.get(icount).amount)));
                    TextDate.setText(itemListcur.get(icount).date);
                    TextComment.setText(itemListcur.get(icount).commment);
                    edittask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                             //detailFragment.onTap(0,itemListcur.get(icount).ID);
                            openDialog1(itemListcur.get(icount));
                            ProcessAccess(buttonc.getText().toString());
                        }
                    });
                    deletetask.setOnClickListener(new View.OnClickListener(){
                                                      @Override
                                                      public void onClick(View v) {
                                                          _detailFragment.onTap(1,itemListcur.get(icount).ID);
                                                          write_noti_data WriteNoti= new write_noti_data();
                                                          WriteNoti.create_noti("xoa",String.valueOf(itemListcur.get(icount)),getContext());
                                                          ProcessAccess(buttonc.getText().toString());
                                                          mydialog.dismiss();
                                                      }
                                                  }
                    );
                    mydialog.show();
                    //loadFragment(new detailFragment(itemListcur.get(icount)),R.id.containerItem);
                }
            });
            return convertView;
        }
    }

    class CustomAdapterView extends BaseAdapter{
        private List<Item>  ItemListPrivate;
        CustomAdapterView(List<Item> itemList){
            ItemListPrivate=itemList;
        }
        @Override
        public int getCount() {
            return ItemListPrivate.size();
        }

        @Override
        public Object getItem(int position) {
            System.out.print(DayofWeek[position]);return null;
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }

        @SuppressLint("ResourceType")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.row_item,null);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.avt);
            TextView contentitem = (TextView)convertView.findViewById(R.id.contentitem);
            TextView price = (TextView)convertView.findViewById(R.id.price);
            //Picasso.with(getContext()).load(ItemListPrivate.get(position).TypeToImage[ItemListPrivate.get(position).type]).into( imageView);

            contentitem.setTextColor(ItemListPrivate.get(position).setColor());
            price.setTextColor(ItemListPrivate.get(position).setColor());
            Log.i("heighta",String.valueOf(convertView.getMeasuredHeight()));
            imageView.setImageResource(ItemListPrivate.get(position).TypeToImage[ItemListPrivate.get(position).type]);
            contentitem.setText(String.valueOf(ItemListPrivate.get(position).TypeToEnty()));
            price.setText(convert(String.valueOf(ItemListPrivate.get(position).amount)));
            return convertView;
        }
    }
}