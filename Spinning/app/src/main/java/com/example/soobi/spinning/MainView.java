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

        Paint paint = new Paint();

        paint.setColor(Color.CYAN);
        canvas.drawArc(0,0,circleWidth,circleWidth,0,45,true,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawArc(0,0,circleWidth,circleWidth,45,90,true,paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(0,0,circleWidth,circleWidth,135,125,true,paint);
        paint.setColor(Color.LTGRAY);
        canvas.drawArc(0,0,circleWidth,circleWidth,260,100,true,paint);
    }
}
