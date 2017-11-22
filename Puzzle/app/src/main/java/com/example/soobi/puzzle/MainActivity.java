package com.example.soobi.puzzle;

import android.graphics.Bitmap;
<<<<<<< HEAD
=======
import android.os.Handler;
import android.os.Message;
>>>>>>> 5a62b8be4e66c24083aa30a39ee39787a394f43e
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
//    public static int IMAGE_ROW = 1;
//    public static int IMAGE_COL = 1;
//    public static int IMAGE_BLANK_X = 0;
//    public static int IMAGE_BLANK_Y = 0;

    ImageManager mImageManager;
    Button mShuffleButton;
    RadioButton btn3;
    RadioButton btn7;

    //라디오버튼 선택되었을 때 행열설정

    //전역 변수 선언
    JigsqwView[][] mArrJigsawView = new JigsqwView[IMAGE_ROW][IMAGE_COL];

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==1){
                //obj: 선택된 퍼즐조각
                JigsqwView jv = (JigsqwView)msg.obj;

                int touched_i=-1;
                int touched_j=-1;
                int move_i=-1;
                int move_j=-1;

                //터치된 퍼즐 조각의 좌표 파악(touched j:행, touched i:열)
                for (int i = 0; i < IMAGE_ROW; i++) {
                    for (int j = 0; j < IMAGE_COL; j++) {
                        if (mArrJigsawView[i][j].getImageView()==jv.getImageView()){
                            touched_i=i;
                            touched_j=j;
                        }
                    }
                }

                //위가 비었으면
                if (touched_i>0 && mArrJigsawView[touched_i-1][touched_j].getBlankState()==JigsqwView.BLANK){
                    move_i = touched_i-1;
                    move_j = touched_j;
                    changeLocation(touched_i,touched_j,move_i,move_j);
                    drawTableLayout();
                }

                //아래가 비었으면
                if (touched_i<2 && mArrJigsawView[touched_i+1][touched_j].getBlankState()==JigsqwView.BLANK){
                    move_i = touched_i+1;
                    move_j = touched_j;
                    changeLocation(touched_i,touched_j,move_i,move_j);
                    drawTableLayout();
                }

                //왼쪽이 비었으면
                if (touched_j>0 && mArrJigsawView[touched_i][touched_j-1].getBlankState()==JigsqwView.BLANK){
                    move_i = touched_i;
                    move_j = touched_j-1;
                    changeLocation(touched_i,touched_j,move_i,move_j);
                    drawTableLayout();
                }

                //오른쪽이 비었으면
                if (touched_j<2 && mArrJigsawView[touched_i][touched_j+1].getBlankState()==JigsqwView.BLANK){
                    move_i = touched_i;
                    move_j = touched_j+1;
                    changeLocation(touched_i,touched_j,move_i,move_j);
                    drawTableLayout();
                }

            }
        }
    };

    private void createArrJigsawView(){
        mArrJigsawView = null;
        mArrJigsawView = new JigsqwView[IMAGE_ROW][IMAGE_COL];

        for (int i = 0; i < IMAGE_ROW; i++) {
            for (int j = 0; j < IMAGE_COL; j++) {
                //mImageManager >> JigsawView
                JigsqwView piece = new JigsqwView(this, mImageManager.getImage(i,j));
                //JigsawView의 Blamk 처리
                if (i==IMAGE_BLANK_X&&j==IMAGE_BLANK_Y) piece.setBlankState(true);
                //JigsawView >> 전역 변수 mArrJigsawView[][]
                mArrJigsawView[i][j] = piece;
                //Event Handler
                piece.setEventHandeler(mHandler);
            }
        }
    }

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

        btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMAGE_ROW = 3;
                IMAGE_COL = 3;
                IMAGE_BLANK_X = 2;
                IMAGE_BLANK_Y = 2;
                createArrJigsawView();

                drawTableLayout();
            }
        });

        btn7 = (Button)findViewById(R.id.button7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMAGE_ROW = 7;
                IMAGE_COL = 7;
                IMAGE_BLANK_X = 6;
                IMAGE_BLANK_Y = 6;
                createArrJigsawView();

                drawTableLayout();
            }
        });
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
//                ImageView img = new ImageView(this);
//                img.setImageBitmap(mImageManager.getImage(i,j));
//                tr.addView(img);
                //Bitmap >> JigsawView
                tr.addView(mArrJigsawView[i][j].getImageView());
                tr.getVirtualChildAt(j).setPadding(5,5,5,5);
            }
            tl.addView(tr);
        }
    }

    private void changeLocation(int touched_i, int touched_j, int move_i, int move_j) {
        //(touched_i,touched_j)좌표 퍼즐과 (move_i,move_j)좌표 퍼즐 자리 이동
        //ImageManager 클래스의 get/setImage()함수 사용
<<<<<<< HEAD
        Bitmap temp = mImageManager.getImage(move_i,move_j);
        mImageManager.setImage(mImageManager.getImage(touched_i,touched_j),move_i,move_j);
        mImageManager.setImage(temp, touched_i, touched_j);
=======
//        Bitmap temp = mImageManager.getImage(move_i,move_j);
//        mImageManager.setImage(mImageManager.getImage(touched_i,touched_j),move_i,move_j);
//        mImageManager.setImage(temp, touched_i, touched_j);

        //Bitmap >> JigsawView
        JigsqwView temp = mArrJigsawView[move_i][move_j];
        mArrJigsawView[move_i][move_j] = mArrJigsawView[touched_i][touched_j];
        mArrJigsawView[touched_i][touched_j] = temp;

>>>>>>> 5a62b8be4e66c24083aa30a39ee39787a394f43e
    }
}

