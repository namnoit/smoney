package com.example.smoney;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class detailFragment{
        static Context _context;
        static ImageView edit;
        public  detailFragment(Context context){
            _context=context;
        }
        public static void onTap(int code, int id){
            switch (code){
                case 0:

                    break;
                case 1:
                    Model databaseHelper = new Model(_context);
                    databaseHelper.deleteInOut(id);
                    break;
            }
        }

    private Context getContext() {
            return this.getContext();
    }

}
