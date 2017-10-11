package com.example.soobi.spinning;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    final static int SELECTED = 1;
    final static int SELECTED_NON = 0;
    int lastAndgle;

    MainView circleView;
    Button startButton;
    Button resetButton;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == SELECTED) {
                selectPop();
            }
        }
    };

    class SelectedThread extends Thread {
        int threadDelay;
        public SelectedThread(int delay) {
            threadDelay = delay;
        }
        @Override
        public void run(){
            try {
                sleep(threadDelay);
                Message.obtain(handler,SELECTED).sendToTarget();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }

    public void selectPop() {
        int currentAngle = 360- (lastAndgle+90)%360;
        if (currentAngle<0) currentAngle = 360+currentAngle;

        float startAngle = 0;
        float collectAngle = 0;
        for (int i=0; i<circleView.label.length; i++){
            collectAngle = startAngle+circleView.weight[i]*circleView.unitAngle;
            if(currentAngle>=startAngle && currentAngle <= collectAngle) {
                Toast.makeText(this, "선택된 항목 : " + circleView.label[i], Toast.LENGTH_LONG).show();
                break;
            } else {
                startAngle += circleView.weight[i]*circleView.unitAngle;
            }
        }
    }

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

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                resetButton.setEnabled(true);
                Random rnd = new Random();
                int angle = rnd.nextInt(360)*10;
                int delay = rnd.nextInt(500)*10;

                lastAndgle = angle;
                circleView.animate().rotation(angle).setDuration(delay).start();

                new SelectedThread(delay).start();
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
