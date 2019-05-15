package com.example.smoney;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class Model {
    public static final int TYPE_IN_OTHER = 0;
    public static final int TYPE_IN_SALARY = 1;
    public static final int TYPE_IN_TRADE = 2;
    public static final int TYPE_IN_LOTTERIA = 3;
    public static final int TYPE_IN_INTEREST_RATE = 4;
    public static final int TYPE_OUT_OTHER = 10;
    public static final int TYPE_OUT_EATING = 11;
    public static final int TYPE_OUT_HEALTH = 12;
    public static final int TYPE_OUT_LOTTERIA = 13;
    public static final int TYPE_OUT_TRANSPORT = 14;
    public static final int TYPE_OUT_SHOPPING = 15;
    public static final int TYPE_OUT_BILL = 16;
    public static final int TYPE_OUT_PHONE = 17;
    public static final int TYPE_OUT_GASOLINE = 18;

    public static final String TABLE_IN_OUT = "Table_In_Out";
    public static final String FIELD_ID = "ID";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_COMMENT = "comment";
    private String DATABASE_NAME = "sMoneyDatabase";
    private String DB_PATH_SUFFIX = "/databases/";
    private SQLiteDatabase database;
    private Context context;

    public Model(Context ct) {
        this.context = ct;
        processCopy();
        database = openOrCreateDatabase(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME,null);
    }

    private void processCopy(){
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getDatabasePath() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }


    public void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = context.getAssets().open(DATABASE_NAME);
            // Path to the just created empty db
            String outFileName = getDatabasePath();
            // if the path doesn't exist first, create it
            File f = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) f.mkdir();
            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }        // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public long addInOut(int type, long amount, String date, String cmt){
        ContentValues values = new ContentValues();
        values.put(FIELD_TYPE, type);
        values.put(FIELD_AMOUNT,amount);
        values.put(FIELD_DATE,date);
        values.put(FIELD_COMMENT,cmt);
        return database.insert(TABLE_IN_OUT,null,values);
    }

<<<<<<< HEAD
    public ArrayList<Item> getInOut(){
        Cursor c = database.rawQuery("select * from " + TABLE_IN_OUT, null);

        ArrayList<Item> arrItem = new ArrayList<Item>();
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    int id = c.getInt(c.getColumnIndex(FIELD_ID));
                    int type = c.getInt(c.getColumnIndex(FIELD_TYPE));
                    long amount = c.getLong(c.getColumnIndex(FIELD_AMOUNT));
                    String date = c.getString(c.getColumnIndex(FIELD_DATE));
                    String cmt = c.getString(c.getColumnIndex(FIELD_COMMENT));
                    arrItem.add(new Item(id,type,amount,date,cmt));
                }while (c.moveToNext());
            }
        }
        return arrItem;
    }
    public Item getInOut(int id){
        Cursor c = database.rawQuery("select * from " + TABLE_IN_OUT+" WHERE "+FIELD_ID+"= '"+String.valueOf(id)+"'", null);

        ArrayList<Item> arrItem = new ArrayList<Item>();
        if (c != null ) {
            if  (c.moveToFirst()) {
                    id = c.getInt(c.getColumnIndex(FIELD_ID));
                    int type = c.getInt(c.getColumnIndex(FIELD_TYPE));
                    long amount = c.getLong(c.getColumnIndex(FIELD_AMOUNT));
                    String date = c.getString(c.getColumnIndex(FIELD_DATE));
                    String cmt = c.getString(c.getColumnIndex(FIELD_COMMENT));
                    return new Item(id,type,amount,date,cmt);
            }
        }
       return null;
    }
    public ArrayList<Item> getInOut(String begin, String end){
        Cursor c = database.rawQuery("select * from " + TABLE_IN_OUT +" WHERE "+FIELD_DATE+" >= '"+begin+"' AND "+FIELD_DATE+" <='"+end+"' ORDER BY "+FIELD_DATE, null);
=======
    public Item getInOut(int id){
        Cursor c = database.rawQuery("select * from " + TABLE_IN_OUT+" WHERE "+FIELD_ID+"= '"+String.valueOf(id)+"'", null);
        if (c != null ) {
            if  (c.moveToFirst()) {
                id = c.getInt(c.getColumnIndex(FIELD_ID));
                int type = c.getInt(c.getColumnIndex(FIELD_TYPE));
                long amount = c.getLong(c.getColumnIndex(FIELD_AMOUNT));
                String date = c.getString(c.getColumnIndex(FIELD_DATE));
                String cmt = c.getString(c.getColumnIndex(FIELD_COMMENT));
                return new Item(id,type,amount,date,cmt);
            }
        }
        return null;
    }

    public ArrayList<Item> getInOut(String begin, String end){
        Cursor c = database.rawQuery("select * from " + TABLE_IN_OUT +" WHERE "+FIELD_DATE+" >= '"+begin+"' AND "+FIELD_DATE+" <='"+end+"'", null);
>>>>>>> origin/feature_chart

        ArrayList<Item> arrItem = new ArrayList<Item>();
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    int id = c.getInt(c.getColumnIndex(FIELD_ID));
                    int type = c.getInt(c.getColumnIndex(FIELD_TYPE));
                    long amount = c.getLong(c.getColumnIndex(FIELD_AMOUNT));
                    String date = c.getString(c.getColumnIndex(FIELD_DATE));
                    String cmt = c.getString(c.getColumnIndex(FIELD_COMMENT));
                    arrItem.add(new Item(id,type,amount,date,cmt));
                }while (c.moveToNext());
            }
        }
        return arrItem;
    }

    public void updateInOut(int id, int type, long amount, String date, String cmt){
        ContentValues values = new ContentValues();
        values.put(FIELD_ID, id);
        values.put(FIELD_TYPE, type);
        values.put(FIELD_AMOUNT,amount);
        values.put(FIELD_DATE,date);
        values.put(FIELD_COMMENT,cmt);
        database.update(TABLE_IN_OUT,values,"ID=?", new String[]{Integer.toString(id)});
    }

    public void deleteInOut(int id){
        database.delete(TABLE_IN_OUT, "ID=?", new String[]{Integer.toString(id)});
    }
<<<<<<< HEAD
}
=======
}

>>>>>>> origin/feature_chart
