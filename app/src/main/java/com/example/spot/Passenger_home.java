package com.example.spot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Passenger_home extends AppCompatActivity {
    Button sendcomplaint,view_bus,feedback,track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);
        sendcomplaint = findViewById(R.id.button4);
        view_bus = findViewById(R.id.button6);
        feedback = findViewById(R.id.button5);
        track = findViewById(R.id.button11);




        sendcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Passenger_view_reply.class));

            }
        });

        view_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),View_bus.class));

            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Passenger_send_feedback.class));

            }
        });




    }
}