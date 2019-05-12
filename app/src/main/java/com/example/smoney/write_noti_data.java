package com.example.smoney;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class write_noti_data {
    public String a = "ok";
    //File file = new File("NDBS.txt");
    String filename = "NDBS.txt";
    String filepath = "myDBS";
    public File mfile;

    //File mfile = new File(getFilesDir(),filename);
    //public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/noDBS";
    public void create_noti(String action,String event,Context context){
        ContextWrapper contextWrapper = new ContextWrapper(context);
        write_noti_data adc = new write_noti_data();
        String filename = "internalStorage";
        String filepath = "ThuMucCuaToi";
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        adc.mfile = new File(directory, filename);
        adc.wr_data(action, event);
    }
    public void wr_data(String action, String event){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String tm = "\n" + dateFormat.format(calendar.getTime()) + "\n";
        if(!mfile.exists())
            mfile.mkdirs();
        try {
            a = mfile.getAbsolutePath();
            if(mfile.isDirectory())
                a = mfile.getPath();

            FileOutputStream fileOutputStream = new FileOutputStream(mfile + "/TextFile.txt", true);
            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            fileOutputStream.write(tm.getBytes());
            switch (action){
                case "chi":
                    String chi = "thêm một khoản chi chi tiêu";
                    fileOutputStream.write(action.getBytes());
                    fileOutputStream.write(event.getBytes());
                    break;
                case "thu":
                    String thu = "thêm một khoản thu nhập";
                    fileOutputStream.write(action.getBytes());
                    fileOutputStream.write(event.getBytes());
                    break;
                case "xoa":
                    String x = "xóa một dòng trong lịch sử";
                    fileOutputStream.write(action.getBytes());
                    fileOutputStream.write(event.getBytes());
                    break;
                case "sua":
                    String s = "Sửa một dòng trong lịch sử";
                    fileOutputStream.write(action.getBytes());
                    fileOutputStream.write(event.getBytes());
                    break;
            }
            fileOutputStream.close();
        } catch (IOException e) {
                e.printStackTrace();
        }


    }
    public void re_data(){
        if (!mfile.exists())
            a = "kcf";
        //a = "read";
        try {
            FileInputStream fileInputStream = new FileInputStream(mfile + "/TextFile.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                a = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
