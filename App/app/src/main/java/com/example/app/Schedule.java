package com.example.app;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Schedule extends AppCompatActivity {

    RadioGroup rGroup;
    int afterTime;
    String waitingTime;
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }

    public void callBtnEvent(View view){

        set=findViewById((R.id.set));
        rGroup = (RadioGroup) findViewById(R.id.rBtnGroup);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rBtnOne){
                    afterTime = 15000;
                    waitingTime = "Wait 15 Seconds";
                }
                else if (checkedId == R.id.rBtnFive){
                    afterTime = 60000;
                    waitingTime = "Wait 1 minute";
                }

                else if (checkedId == R.id.rBtntwo){
                    afterTime = 120000;
                    waitingTime = "Wait 2 minutes";
                }
                else if (checkedId == R.id.rBtnfive){
                    afterTime = 300000;
                    waitingTime = "Wait 5 minutes";
                }
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent callEvent = new Intent(Schedule.this, Caller.class);
                 Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(callEvent);
                    }
                }, afterTime);
            }
        });
    }
}