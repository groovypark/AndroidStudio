package com.example.soobi.puzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class JigsqwView {
    public final static int BLANK = 1;
    public final static int NONE_BLANK =2;

    private ImageView mImageView;
    private int mBlankState;

    public JigsqwView(Context context, Bitmap bitmap){
        mImageView = new ImageView(context);
        mImageView.setImageBitmap(bitmap);
        mBlankState = NONE_BLANK;
    }

    public void setBlankState(boolean b){
        //매개변수 b의 true/false에 따라 퍼즐의 BLANK/NONE_BLANK 제어
        if (b==true) {
            mBlankState = BLANK;
        } else if (b == false) {
            mBlankState = NONE_BLANK;
        }
    }

    public int getBlankState(){
        //ImageView의 mBlankState 리턴
        return mBlankState;
    }

    public ImageView getImageView(){
        //ImageView 리턴
        return mImageView;
    }

    public void setEventHandeler(final Handler h){
        final Handler handler = h;
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message.obtain(handler,1,JigsqwView.this).sendToTarget();
            }
        });
    }

}
