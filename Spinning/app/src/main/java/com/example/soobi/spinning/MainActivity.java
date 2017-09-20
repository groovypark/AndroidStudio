package com.example.soobi.spinning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MainView circleView;
    Button startButton;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleView = (MainView) findViewById(R.id.customView);
        startButton = (Button)findViewById(R.id.startButton);
        resetButton = (Button)findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                resetButton.setEnabled(true);
                Random rmd = new Random();

                circleView.animate().rotation(rmd.nextInt(360)*10).setDuration(rmd.nextInt(360)*10);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                circleView.setRotation(0);
                resetButton.setEnabled(false);
                startButton.setEnabled(true);
            }
        });
    }
}
