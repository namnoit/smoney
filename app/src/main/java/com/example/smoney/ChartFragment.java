package com.example.smoney;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;

public class ChartFragment extends Fragment {
    private Date begin;
    private Date end;
    private PieChart pieChart;
    private BarChart barChart;
    private Spinner spnChartType, spnChartTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        addControls(view);
        showPieChart();
        spnChartType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) showPieChart();
                else showBarChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }




    private void addControls(View view) {
        pieChart = view.findViewById(R.id.pie_chart);
        barChart = view.findViewById(R.id.bar_chart);
        spnChartTime = view.findViewById(R.id.spinner_chart_time);
        spnChartType = view.findViewById(R.id.spinner_chart_type);
    }

    private void showPieChart(){
        barChart.setVisibility(View.INVISIBLE);
        pieChart.setVisibility(View.VISIBLE);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(25,"Thu"));
        yValues.add(new PieEntry(75,"Chi"));
        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
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
//        barChart.getAxisLeft().setAxisMinimum(0);
        ArrayList<BarEntry> yValues = new ArrayList<>();
        yValues.add(new BarEntry(1,50));
        yValues.add(new BarEntry(2,100));
        yValues.add(new BarEntry(3,50));
        BarDataSet dataSet = new BarDataSet(yValues,"");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setDrawValues(true);
        BarData barData = new BarData(dataSet);
        barData.setValueTextSize(20);
        barChart.setData(barData);
    }

}
