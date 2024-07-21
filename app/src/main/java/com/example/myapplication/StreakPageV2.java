package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StreakPageV2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streak_page_v2);

        ////////
        // Inflate your custom layout
        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_streak_page_v2,null);

        // Set up your ActionBar
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(actionBarLayout);

        // You customization
        final int actionBarColor = getResources().getColor(R.color.action_bar);
        actionBar.setBackgroundDrawable(new ColorDrawable(actionBarColor));

        final Button actionBarTitle = (Button) findViewById(R.id.action_bar_title);
        actionBarTitle.setText("Index(2)");

        final Button actionBarSent = (Button) findViewById(R.id.action_bar_sent);
        actionBarSent.setText("Sent");

        final Button actionBarStaff = (Button) findViewById(R.id.action_bar_staff);
        actionBarStaff.setText("Staff");


        ////////////////


        // Set up the title
        TextView title = findViewById(R.id.title);
        title.setText("Solutions");

//        // Set up the first card
//        TextView fastTitle = findViewById(R.id.fastTitle);
//        fastTitle.setText("Fast");
//        TextView fastDescription = findViewById(R.id.fastDescription);
//        fastDescription.setText("Send and receive funds instantly, anytime - even during U.S. bank holidays.");
////        ImageView fastImage = findViewById(R.id.fastImage);
////        fastImage.setImageResource(R.drawable.lightning);
//
//        // Set up the second card
//        TextView convenientTitle = findViewById(R.id.convenientTitle);
//        convenientTitle.setText("Convenient");
//        TextView convenientDescription = findViewById(R.id.convenientDescription);
//        convenientDescription.setText("Deposit in the U.S., receive pesos in Mexico across banks and digital wallets.");
////        ImageView convenientImage = findViewById(R.id.convenientImage);
////        convenientImage.setImageResource(R.drawable.pie_chart);
//
//        // Set up the third card
//        TextView freeEasyTitle = findViewById(R.id.freeEasyTitle);
//        freeEasyTitle.setText("Free & Easy");
//        TextView freeEasyDescription = findViewById(R.id.freeEasyDescription);
//        freeEasyDescription.setText("Monetize P2P payments in multiple currencies, without platform fees.");
////        ImageView freeEasyImage = findViewById(R.id.freeEasyImage);
////        freeEasyImage.setImageResource(R.drawable.laptop);

        // Set up the first card

        final EditText taskDescription = findViewById(R.id.Task1Description);
        final EditText fastTime = findViewById(R.id.fastTime);
        Button fastSave = findViewById(R.id.fastSave);

        fastSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = taskDescription.getText().toString();
                String time = fastTime.getText().toString();
                if (!description.isEmpty() &&!time.isEmpty()) {
                    Toast.makeText(StreakPageV2.this, "Saved: " + description + ", Time: " + time, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StreakPageV2.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}