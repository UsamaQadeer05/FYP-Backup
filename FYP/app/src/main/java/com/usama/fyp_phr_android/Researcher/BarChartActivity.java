package com.usama.fyp_phr_android.Researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.usama.fyp_phr_android.R;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        barChart = findViewById(R.id.barchart);


        ArrayList<BarEntry> info = new ArrayList<>();
        info.add(new BarEntry(2010, 1000));
        info.add(new BarEntry(2011, 1200));
        info.add(new BarEntry(2012, 1400));
        info.add(new BarEntry(2013, 1700));
        info.add(new BarEntry(2014, 1400));

        BarDataSet barDataSet = new BarDataSet(info, "Report");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(20f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("barData demo");
        barChart.animateY(2000);
    }
}