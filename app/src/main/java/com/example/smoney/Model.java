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
import java.util.Date;
import java.util.Locale;

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
    private SQLiteDatabase database = null;
    private Context context;

    public Model(Context ct) {
        this.context = ct;
        processCopy();
    }

    private void processCopy(){
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
//                Toast.makeText(, e.toString(), Toast.LENGTH_LONG).show();
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

    public long add(int type, long amount, String date, String cmt){
        ContentValues values = new ContentValues();
        values.put(FIELD_TYPE, type);
        values.put(FIELD_AMOUNT,amount);
        values.put(FIELD_DATE,date);
        values.put(FIELD_COMMENT,cmt);
        return database.insert(TABLE_IN_OUT,null,values);
    }

    public void getInOut(Date begin, Date end){
        Cursor c = database.rawQuery("select ID, Date, Hours from " + TABLE_IN_OUT + " where Date BETWEEN '" + begin + "' AND '" + end + "' ORDER BY Date ASC", null);

        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    int tempId = c.getInt(c.getColumnIndex("ID"));
                    long tempUnixTime = c.getLong(c.getColumnIndex("Date"));

                    //convert tempUnixTime to Date
                    java.util.Date startDateDate = new java.util.Date(tempUnixTime);

                    //create SimpleDateFormat formatter
                    SimpleDateFormat formatter1;
                    formatter1 = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);

                    //convert Date to SimpleDateFormat and convert to String
                    String tempStringStartDate = formatter1.format(startDateDate);

                    int tempHours = c.getInt(c.getColumnIndex("Hours"));
//                    results.add(+ tempId + "    Date: " + tempStringStartDate + "    Hours: " + tempHours);
                }while (c.moveToNext());
            }
        }
    }
}
