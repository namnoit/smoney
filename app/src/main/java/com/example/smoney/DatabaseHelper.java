package com.example.smoney;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    //Ten cac field
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
    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Database";


    // Tên bảng: Note.
    public static final String TABLE_IN_OUT = "Table_In_Out";
    public static final String FIELD_ID = "ID";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_COMMENT = "comment";

    public DatabaseHelper(Context context)  {

        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("123", "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_IN_OUT + "("
                +FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FIELD_TYPE + " INTEGER,"
                + FIELD_AMOUNT + " INTEGER," +FIELD_DATE+" VARCHAR(20),"+FIELD_COMMENT +" TEXT)";
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.delete(TABLE_IN_OUT,null,null);
        onCreate(db);
    }
    public void addInOut(Item item) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIELD_TYPE, item.type);
        values.put(FIELD_AMOUNT,item.amount);
        values.put(FIELD_DATE, item.date);
        values.put(FIELD_COMMENT, item.commment);


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_IN_OUT, null, values);


        // Đóng kết nối database.
        db.close();
    }
    public List<Item> getInOut(String startdate,String enddate) {
        List<Item> itemList = new ArrayList<Item>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM "+TABLE_IN_OUT+"" +
                " WHERE "+FIELD_DATE + " >= '" +startdate+ "' AND "+FIELD_DATE +" <= '" +enddate+ "'";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item(
                        Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)),
                        cursor.getString(3),
                        cursor.getString(4));
                // Thêm vào danh sách.
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return note
        return itemList;
    }
    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_IN_OUT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();

        // return count
        db.close();
        return count;
    }
    public void deleteItem(int id){
        String sql ="DELETE FROM "+TABLE_IN_OUT+" WHERE "+FIELD_ID+" ='"+String.valueOf(id)+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        db.close();
    }
}
