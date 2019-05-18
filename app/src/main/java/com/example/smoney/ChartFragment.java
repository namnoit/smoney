package com.example.smoney;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChartFragment extends Fragment {
    private static int CHART_TYPE_PIE = 0, CHART_TYPE_BAR = 1;
    private int chartType = CHART_TYPE_PIE;
    private String begin, end;
    private PieChart pieChart;
    private BarChart barChart;
    private ImageButton btnDateBegin, btnDateEnd;
    private TextView txtBegin, txtEnd;
    private Spinner spnChartType, spnChartTime;
    private DatePickerDialog.OnDateSetListener beginDateSetListener, endDateSetListener;
    private Model model;
    private long in = 0, out = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        addControls(view);
        showChart();

        spnChartType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    chartType = CHART_TYPE_PIE;
                    showChart();
                }
                else {
                    chartType = CHART_TYPE_BAR;
                    showChart();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnChartTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                switch(position){
                    case 0:
                        end = intDateToString(year,month,31);
                        begin = intDateToString(year,month,1);
                        break;
                    case 1:
                        end = intDateToString(year,12,31);
                        begin = intDateToString(year,1,1);
                        break;
                    case 2:
                        showDateDialog();
                        break;
                }
                showChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        beginDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                begin = intDateToString(year,month,dayOfMonth);
                txtBegin.setText(begin);
            }
        };

        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                end = intDateToString(year,month,dayOfMonth);
                txtEnd.setText(end);
            }
        };

        return view;
    }

    private void showDateDialog() {
        final AlertDialog.Builder distanceDialog = new AlertDialog.Builder(getContext());
        distanceDialog.setTitle("Chọn ngày");
        LayoutInflater inflater = this.getLayoutInflater();
        View distanceDialogView = inflater.inflate(R.layout.select_date_dialog, null);
        btnDateBegin = distanceDialogView.findViewById(R.id.btn_begin_date);
        btnDateEnd = distanceDialogView.findViewById(R.id.btn_end_date);
        txtBegin = distanceDialogView.findViewById(R.id.txt_begin_date);
        txtEnd = distanceDialogView.findViewById(R.id.txt_end_date);
        btnDateBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        beginDateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        endDateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // User clicked the Yes button
                        showChart();
                        dialog.dismiss();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        distanceDialog.setPositiveButton("OK", dialogClickListener);
        distanceDialog.setNegativeButton("Cancel", dialogClickListener);

        distanceDialog.setView(distanceDialogView);
        distanceDialog.show();
    }


    private void addControls(View view) {
        pieChart = view.findViewById(R.id.pie_chart);
        barChart = view.findViewById(R.id.bar_chart);
        spnChartTime = view.findViewById(R.id.spinner_chart_time);
        spnChartType = view.findViewById(R.id.spinner_chart_type);
        model = new Model(getActivity().getApplicationContext());

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        end = intDateToString(year,month,31);
        begin = intDateToString(year,month,1);
    }

    private void showChart(){
        in = 0;
        out = 0;
        ArrayList<Item> arr;
        arr = model.getInOut(begin,end);
        for (int x = 0; x < arr.size(); x++){
            if (arr.get(x).type < 10) in += arr.get(x).amount;
            else out += arr.get(x).amount;
        }
        if (chartType == CHART_TYPE_BAR) showBarChart();
        else showPieChart();
    }

    private void showPieChart(){
        barChart.setVisibility(View.INVISIBLE);
        pieChart.setVisibility(View.VISIBLE);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(in,"Thu"));
        yValues.add(new PieEntry(out,"Chi"));
        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(20);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart.setData(pieData);
    }

    private void showBarChart(){
        pieChart.setVisibility(View.INVISIBLE);
        barChart.setVisibility(View.VISIBLE);
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.animateY(1000);
        barChart.setScaleEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        long min = 0;
        if (in - out < 0) min = in - out;
        barChart.getAxisLeft().setAxisMinimum(min);
        ArrayList<BarEntry> yValues = new ArrayList<>();
        yValues.add(new BarEntry(0, in));
        yValues.add(new BarEntry(1, out));
        yValues.add(new BarEntry(2,in - out));

        ArrayList<String> label = new ArrayList<>();
        label.add("Thu");
        label.add("Chi");
        label.add("Thặng dư");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(label));

        barChart.getXAxis().setTextSize(15);
        BarDataSet dataSet = new BarDataSet(yValues,"");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setDrawValues(true);
        BarData barData = new BarData(dataSet);
        barData.setValueTextSize(15);
        barChart.setData(barData);
    }

    private String intDateToString(int year, int month, int day){
        month += 1;
        String nMonth = "", nDay ="";
        if (month < 10) nMonth = "0" + month;
        else nMonth = Integer.toString(month);
        if (day < 10) nDay = "0" + day;
        else nDay = Integer.toString(day);

        return year + "/" + nMonth + "/" + nDay;
    }

}