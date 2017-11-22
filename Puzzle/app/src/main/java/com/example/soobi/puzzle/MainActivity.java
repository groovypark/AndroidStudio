package com.example.soobi.puzzle;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final static int IMAGE_ROW = 3;
    public final static int IMAGE_COL = 3;
    public final static int IMAGE_BLANK_X = 2;
    public final static int IMAGE_BLANK_Y = 2;

    ImageManager mImageManager;
    Button mShuffleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Shuffle Button Event 생성
        mShuffleButton = (Button)findViewById(R.id.button);
        mShuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Random 함수 사용하여 퍼즐 조각 위치 변경
                Random ran = new Random();

                for (int i = 0; i < 10; i++) {
                    //changeLocation()함수로 자리 이동 10번 반복
                    changeLocation(ran.nextInt(IMAGE_COL),ran.nextInt(IMAGE_ROW),ran.nextInt(IMAGE_COL),ran.nextInt(IMAGE_ROW));
                }
                //후 다시 테이블 그리기((drawTableLayout())
                drawTableLayout();
            }
        });


        //Imagemanager 객체 생성(Context, Img, Row, Col, Blank_x, Blank_y)

        mImageManager = new ImageManager(this, R.drawable.background_525x525, IMAGE_ROW, IMAGE_COL, IMAGE_BLANK_X, IMAGE_BLANK_Y);

        drawTableLayout();
    }

    private void drawTableLayout() {
        TableLayout tl = (TableLayout)findViewById(R.id.tableLayout);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //테이블 행 초기화
        for (int i = 0; i < tl.getChildCount(); i++) {
            TableRow tr = (TableRow)tl.getChildAt(i);
            tr.removeAllViews();
        }
        tl.removeAllViewsInLayout();

        //이미지 추가
        for (int i = 0; i < IMAGE_ROW; i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(params);
            for (int j = 0; j < IMAGE_COL; j++) {
                ImageView img = new ImageView(this);
                img.setImageBitmap(mImageManager.getImage(i,j));
                tr.addView(img);
                tr.getVirtualChildAt(j).setPadding(5,5,5,5);
            }
            tl.addView(tr);
        }
    }

    private void changeLocation(int touched_i, int touched_j, int move_i, int move_j) {
        //(touched_i,touched_j)좌표 퍼즐과 (move_i,move_j)좌표 퍼즐 자리 이동
        //ImageManager 클래스의 get/setImage()함수 사용
        Bitmap temp = mImageManager.getImage(move_i,move_j);
        mImageManager.setImage(mImageManager.getImage(touched_i,touched_j),move_i,move_j);
        mImageManager.setImage(temp, touched_i, touched_j);
    }
}

