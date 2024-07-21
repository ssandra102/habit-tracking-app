package com.example.myapplication;

// Import the required libraries
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class performance extends AppCompatActivity{

    TextView tvR, tvPython, tvCPP, tvJava;
    PieChart pieChart;
    private DatabaseHelper dbHelper;
    StreakInfo streakInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        tvR = findViewById(R.id.tvR);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);

        dbHelper = new DatabaseHelper(this);
        streakInfo = new StreakInfo();

        getDataFromSQLite();
    }


//    private void setData()
//    {
//
//        // Set the percentage of language used
//        tvR.setText(Integer.toString(40));
//        tvPython.setText(Integer.toString(30));
//        tvCPP.setText(Integer.toString(5));
//        tvJava.setText(Integer.toString(25));
//
//        // Set the data and color to the pie chart
//        pieChart.addPieSlice(
//                new PieModel(
//                        "R",
//                        Integer.parseInt(tvR.getText().toString()),
//                        Color.parseColor("#FFA726")));
//
//        // To animate the pie chart
//        pieChart.startAnimation();
//    }

    private void getDataFromSQLite() {
        List<StreakInfo> streakInfoList = dbHelper.getTodayStreakInfo();
        HashMap<String, Integer> subjectTimeMap = new HashMap<>();

        for (StreakInfo streakInfo : streakInfoList) {
            String subjectName = streakInfo.getSubjectName();
            int timeInMinutes = Integer.parseInt(streakInfo.getTimeInMinutes());

            if (subjectTimeMap.containsKey(subjectName)) {
                subjectTimeMap.put(subjectName, subjectTimeMap.get(subjectName) + timeInMinutes);
            } else {
                subjectTimeMap.put(subjectName, timeInMinutes);
            }
        }

        pieChart.clearChart(); // Clear existing slices

        for (String subjectName : subjectTimeMap.keySet()) {
            int totalTimeInMinutes = subjectTimeMap.get(subjectName);
            float percentageOfDay = (float) totalTimeInMinutes / 1440.0f;

            pieChart.addPieSlice(
                    new PieModel(
                            subjectName,
                            percentageOfDay,
                            Color.parseColor("#FFA726"))); // You can use different colors for different subjects
        }
        pieChart.startAnimation();
    }


}
