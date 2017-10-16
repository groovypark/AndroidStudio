package kr.ac.hongik.selab.jang.customroulette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.Vector;

public class MainView extends View {

    int mScreenWidth;
    int mScreenHeight;
    int mCircleWidth;
    Vector<Integer> mColorList;
    Vector<String> mTextList;

    public MainView(Context context){
        super(context);
        initVariables(context);
    }

    public MainView(Context context, AttributeSet attrs){
        super(context, attrs);
        initVariables(context);
    }

    public MainView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        initVariables(context);
    }

    private void initVariables(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        mCircleWidth = mScreenWidth/2;
        if(mScreenHeight < mScreenWidth) mCircleWidth = mScreenHeight/2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mCircleWidth,mCircleWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        if(mTextList==null || mTextList.size()==0){
            paint.setColor(Color.GRAY);
            canvas.drawCircle(mCircleWidth/2, mCircleWidth/2, mCircleWidth/2, paint);
            return;
        }

        int angle = 360/mTextList.size();
        int lastAngle = 0;

        for(int i=0; i<mTextList.size(); i++){
            paint.setColor(mColorList.get(i%mColorList.size()));
            canvas.drawArc(0, 0, mCircleWidth, mCircleWidth, lastAngle, angle, true, paint);

            lastAngle += angle;
        }
    }

    public void setList(Vector<String> textList, Vector<Integer> colorList){
        mTextList = textList;
        mColorList = colorList;
        postInvalidate();
        initAngle();
    }

    public void rotate(int angle, int speed){
        animate().rotation(-angle).setDuration(speed).start();
    }

    public void initAngle(){
        animate().rotation(0).setDuration(10).start();
    }
}
