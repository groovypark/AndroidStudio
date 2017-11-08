package com.example.yoyo.puzzle_class1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

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

        //ImageManager 객체 생성(Context, Img, Row, Col, Blank_x, Blank_y)



        drawTableLayout();

    }

    private void drawTableLayout(){
        TableLayout tl = (TableLayout)findViewById(R.id.tableLayout);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //테이블 행 초기화



        //이미지 추가



    }
}
