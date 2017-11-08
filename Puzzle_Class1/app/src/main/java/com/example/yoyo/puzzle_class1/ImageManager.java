package com.example.yoyo.puzzle_class1;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by yoyo on 2017-10-11.
 */

public class ImageManager {
    private Bitmap mOriginalImage = null;
    private Bitmap[][] mSlicedImage = null;

    public ImageManager(Context context, int drawable_id, int row, int col, int blank_x, int blank_y){
        // 이미지 -> 비트맵


        // 이미지 크기 조절, 나누기



    }

    private Bitmap createImage(Context context, int drawable_id){
        // 리턴 수정(이미지 -> 비트맵)
        return null;
    }

    private Bitmap resizeImage(Context context, Bitmap img){
        if(img == null) return null;

        //Context 객체를 이용해 윈도우 사이즈 확인




        //이미지 크기 조절하여 리턴(수정)
        return null;
    }

    private  Bitmap[][] sliceImage(Bitmap bmp, int row, int col, int blank_x, int blank_y){
        // 파라미터로 전달받은 row, col에 맞게 배열 생성(수정)
        Bitmap[][] retArrBmp = new Bitmap[0][0];

        // 이미지 row, col에 따라 나누기




        // 빈칸 (blank_x, blank_y) 투명으로 설정


        // 비트맵 배열 리턴(수정)
        return null;
    }

    public Bitmap getImage(int row, int col){
        // 테이블 레이어에 들어갈 비트맵(row, col) 리턴
        return mSlicedImage[row][col];
    }
}
