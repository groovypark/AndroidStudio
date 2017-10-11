package com.example.soobi.puzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ImageManager {
    private Bitmap mOriginallmage = null;
    private Bitmap[][] mSlicedImage = null;

    public ImageManager(Context context, int drawable_id, int row, int col, int blank_x, int blanck_y) {
        //이미지->비트맵
        mOriginallmage = createImage(context, drawable_id);

        //이미지 크기 조절, 나누기
        if (mOriginallmage!=null) {
            mOriginallmage = resizeImage(context, mOriginallmage);
            mSlicedImage = sliceImage(mOriginallmage, row, col, blank_x, blanck_y);
        }
    }

    private Bitmap createImage(Context context, int drawable_id){
        //리턴 수정(이미지->비트맵)
        return BitmapFactory.decodeResource(context.getResources(), drawable_id);
    }

    private Bitmap resizeImage(Context context, Bitmap img) {
        if (img==null) return null;

        //Context 객체를 이용해 윈도우 사이즈 확인
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        int imgSize = screenWidth;
        if (screenHeight < screenWidth) imgSize = screenHeight;

        //이미지 크기 조절하여 리턴(수정)
        return Bitmap.createScaledBitmap(img, imgSize, imgSize, true);
    }

    private Bitmap[][]sliceImage(Bitmap bmp, int row, int col, int blank_x, int blanck_y) {
        //파라미터로 전달받은 row,col에 맞게 배열 생성(수정)
        Bitmap[][]retArrBmp=new Bitmap[row][col];

        //이미지 row,col에 따라 나누기
        int imgWidth = bmp.getWidth()/col;
        int imgHeight = bmp.getHeight()/row;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int startWidth = imgWidth*j;
                int startHeight = imgHeight*i;
                retArrBmp[i][j] = Bitmap.createBitmap(mOriginallmage, startWidth, startHeight, imgWidth, imgHeight);
            }
        }

        //빈칸(blank_x, blank_y) 투명으로 설정
        retArrBmp[blanck_y][blank_x] = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);

        //비트맵 배열 리턴(수정)
        return retArrBmp;
    }

    public Bitmap getImage(int row, int col) {
        //테이블 레이어에 들어갈 비트맵(row,col)리턴
        Bitmap retBmp = null;

        try {
            retBmp = mSlicedImage[row][col];
        } catch (Exception e){
            e.printStackTrace();
        }
        return retBmp;
    }
}
