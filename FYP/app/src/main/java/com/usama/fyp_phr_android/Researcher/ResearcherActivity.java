package com.usama.fyp_phr_android.Researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.usama.fyp_phr_android.R;

public class ResearcherActivity extends AppCompatActivity {
    Button btnBarChart, btnPieChart, btnLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_researcher);
        btnBarChart = findViewById(R.id.btnBarChart);
        btnPieChart = findViewById(R.id.btnPieChart);
        btnLineChart = findViewById(R.id.btnLineChart);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBarChart:
                startActivity(new Intent(ResearcherActivity.this, BarChartActivity.class));
                break;
            case R.id.btnPieChart:
                startActivity(new Intent(ResearcherActivity.this, PieChartActivity.class));
                break;
            case R.id.btnLineChart:
                startActivity(new Intent(ResearcherActivity.this, LineChartActivity.class));
                break;

        }
    }
}