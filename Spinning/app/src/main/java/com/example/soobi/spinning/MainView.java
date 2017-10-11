package com.example.soobi.spinning;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class MainView extends View{
    int screenWidth;
    int screenHeight;
    int circleWidth;

    int[] weight = {1,3,2,4};
    String[] label = {"가","나","다","라"};
    int[] colorList = {Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.LTGRAY};

    int weightTot = 0;
    float unitAngle = 0;
    int lastangle = 0;

    public MainView(Context context) {
        super(context);
        intVariables(context);
    }

    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intVariables(context);
    }

    public MainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intVariables(context);
    }

    public MainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        intVariables(context);
    }

    private void intVariables(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        circleWidth = screenWidth/2;
        if(screenHeight < screenWidth) circleWidth = screenHeight/2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(circleWidth, circleWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i=0; i<weight.length; i++) {
            weightTot += weight[i];
        }

        unitAngle = 360/weightTot;

        Paint paint = new Paint();

       for(int i=0; i<weight.length; i++) {
           paint.setColor(colorList[i]);
           float angle = unitAngle*weight[i];
           canvas.drawArc(0,0,circleWidth,circleWidth,lastangle,angle,true,paint);

           paint.setColor(Color.BLACK);
           paint.setTextSize(50);
           canvas.save();
           canvas.rotate(lastangle+angle/2+paint.getTextSize()/10,circleWidth/2,circleWidth/2);
           paint.setTextAlign(Paint.Align.CENTER);
           canvas.drawText(label[i],circleWidth/5*4,circleWidth/2,paint);
           canvas.restore();

           lastangle += angle;
       }
    }
}