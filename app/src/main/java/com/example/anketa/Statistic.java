package com.example.anketa;

import android.app.Activity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;





public class Statistic extends Activity {
    private BarChart chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        chart = findViewById(R.id.chart);
    }

}
