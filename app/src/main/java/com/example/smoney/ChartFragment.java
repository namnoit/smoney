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
import java.util.Date;

public class ChartFragment extends Fragment {
    private static int CHART_TYPE_PIE = 0, CHART_TYPE_BAR = 1;
    private int chartType = CHART_TYPE_PIE;
    private Date begin;
    private Date end;
    private PieChart pieChart;
    private BarChart barChart;
    private Spinner spnChartType, spnChartTime;
    private Model model;
    private long in = 0, out = 0;

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
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDateEnd = dateFormat.format(end);
                String strDateBegin;
                ArrayList<Item> arr = new ArrayList<>();
                switch(position){
                    case 0:
                        begin = new Date(end.getTime() - 1000L*60L*60L*24L*30L);
                        strDateBegin = dateFormat.format(begin);
                        arr = model.getInOut(strDateBegin,strDateEnd);

                        break;
                    case 1:
                        begin = new Date(end.getTime() - 1000L*60L*60L*24L*365L);
                        strDateBegin = dateFormat.format(begin);
                        arr = model.getInOut(strDateBegin,strDateEnd);
                        break;
                    case 2:
                        begin = new Date(0L);
                        strDateBegin = dateFormat.format(begin);
                        arr = model.getInOut(strDateBegin,strDateEnd);
                        break;
                }
                in = 0;
                out = 0;

                for (int x = 0; x < arr.size(); x++){
                    if (arr.get(x).type < 10) in += arr.get(x).amount;
                    else out += arr.get(x).amount;
                }
                showChart();
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
        model = new Model(getActivity().getApplicationContext());

        end = new Date();
        begin = new Date(end.getTime() - 1000L*60L*60L*24L*30L);
    }

    private void showChart(){
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
        barData.setValueTextSize(20);
        barChart.setData(barData);
    }

}
