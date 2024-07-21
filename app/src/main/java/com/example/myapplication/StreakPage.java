package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class StreakPage extends AppCompatActivity {

    private EditText subjectNameEdt, contentEdt, timeInMinutesEdt;
    private Button sendDatabtn, viewPerformance;
    Date d1 = new Date();
    StreakInfo streakInfo;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streak_page);
        dbHelper = new DatabaseHelper(this);
        streakInfo = new StreakInfo();

        // initializing our edittext and button
        subjectNameEdt = findViewById(R.id.idEdtSubjectName);
        contentEdt = findViewById(R.id.idEdtContent);
        timeInMinutesEdt = findViewById(R.id.idEdtTimeInMinutes);
        sendDatabtn = findViewById(R.id.idBtnSendData);
        viewPerformance = (Button) findViewById(R.id.idBtnViewPerformance);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String subjectName = subjectNameEdt.getText().toString();
                String content = contentEdt.getText().toString();
                String timeInMinutes = timeInMinutesEdt.getText().toString();

                if (TextUtils.isEmpty(subjectName) && TextUtils.isEmpty(content) && TextUtils.isEmpty(timeInMinutes)) {
                    Toast.makeText(StreakPage.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    String currentTime = d1.toString(); // Convert d1 to a string representation
                    addDataToSQLite(subjectName, content, timeInMinutes, currentTime);

                }
            }
        });


        viewPerformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }

    private void addDataToSQLite(String subjectName, String content, String timeInMinutes, String currentTime) {
        // Insert data into SQLite
        dbHelper.addStreakInfo(subjectName, content, timeInMinutes, currentTime);
        Toast.makeText(StreakPage.this, "Data added to SQLite database", Toast.LENGTH_SHORT).show();
        verifyDataInSQLite();
    }


    private void verifyDataInSQLite() {
        List<StreakInfo> streakInfoList = dbHelper.getAllStreakInfo();

        for (StreakInfo streakInfo : streakInfoList) {
            Log.d("SQLite Data", "Subject: " + streakInfo.getSubjectName() +
                    ", Content: " + streakInfo.getContent() +
                    ", Time: " + streakInfo.getTimeInMinutes() +
                    ", Current Time: " + streakInfo.getCurrentTime());
        }
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, performance.class);
        startActivity(intent);
    }


}