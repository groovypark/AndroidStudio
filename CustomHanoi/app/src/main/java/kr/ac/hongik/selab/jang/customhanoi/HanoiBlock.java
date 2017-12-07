package kr.ac.hongik.selab.jang.customhanoi;

import android.graphics.Color;

public class HanoiBlock {
    private final static int COLOR_INIT = Color.BLUE;
    private final static int COLOR_SELECTED = Color.RED;

    private double mWidthRate;
    private int mColor;

    public HanoiBlock(double widthRate){
        if(widthRate>1.0) widthRate = 1.0;
        else if(widthRate<0) widthRate = 0.1;

        mWidthRate = widthRate;
        mColor = COLOR_INIT;
    }

    public void select(){
        mColor = COLOR_SELECTED;
    }

    public void deselect(){
        mColor = COLOR_INIT;
    }

    public boolean isSelected(){
        if(mColor == COLOR_SELECTED) return true;
        else return false;
    }

    public int getColor(){
        return mColor;
    }

    public double getWidthRate(){
        return mWidthRate;
    }
}
