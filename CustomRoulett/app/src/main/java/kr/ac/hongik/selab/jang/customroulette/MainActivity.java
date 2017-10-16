package kr.ac.hongik.selab.jang.customroulette;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    final static int SEL_CONTENT_ADD = 0;
    final static int SEL_CONTENT_DEL = 1;

    Button mBtnAdd;
    EditText mEtName;
    MainView mMainView;
    LinearLayout mColorListLayout;
    Button mBtnStart, mBtnReset;
    TextView mTvResult;

    Vector<String> mTextList;
    Vector<Integer> mColorList;

    int mLastAngle;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==SEL_CONTENT_ADD){
                String str = getSelectedContent();
                if(str!=null) mTvResult.setText("선택된 항목: "+str);
            } else if(msg.what==SEL_CONTENT_DEL){
                mTvResult.setText("");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAdd = (Button)findViewById(R.id.btn_add);
        mEtName = (EditText)findViewById(R.id.et_name);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContent(mEtName.getText().toString());
            }
        });



        mTextList = new Vector<String>();
        mColorList = new Vector<Integer>();

        mColorListLayout = (LinearLayout)findViewById(R.id.colorListLayout) ;
        mMainView = (MainView)findViewById(R.id.customView1);
        mTvResult = (TextView)findViewById(R.id.tvResult);
        mBtnStart = (Button)findViewById(R.id.mainButton1);
        mBtnReset = (Button)findViewById(R.id.mainButton2);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTextList.size()<2 || mTextList==null){
                    return;
                }

                Random rand = new Random();
                int angle = rand.nextInt(360)*10;
                int delay = rand.nextInt(500)*10;

                mLastAngle = angle;
                mMainView.rotate(angle, delay);
                mBtnStart.setEnabled(false);

                Message.obtain(mHandler, SEL_CONTENT_DEL).sendToTarget();
                new SelectedContentShowThread(delay).start();
                Log.d("woosung1", "thread start");
            }
        });

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message.obtain(mHandler, SEL_CONTENT_DEL).sendToTarget();
                mMainView.initAngle();
                mBtnStart.setEnabled(true);
            }
        });
    }

    private void printColorList(Context context, LinearLayout rootLayout, Vector<String> textList, Vector<Integer> colorList){

        rootLayout.removeAllViews();
        if(textList.size()==0) return;

        int col = 4;
        int row = (textList.size()/col) + (textList.size()%col);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int i=0; i<row; i++){
            LinearLayout ll = new LinearLayout(context);
            ll.setLayoutParams(params);
            ll.setPadding(0, 5, 0, 5);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            for(int j=0; j<col; j++){
                if((col*i)+j >= textList.size()) break;

                ImageView iv = new ImageView(context);
                TextView tv = new TextView(context);

                iv.setMinimumWidth(30);
                iv.setMinimumHeight(30);
                iv.setBackgroundColor(colorList.get(((col*i)+j)%colorList.size()));

                tv.setPadding(10,0,10,0);
                tv.setText(textList.get((col*i)+j));
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeContent(((TextView)v).getText().toString());
                    }
                });

                ll.addView(iv);
                ll.addView(tv);
            }

            rootLayout.addView(ll);
        }
    }

    public void addContent(String name){
        mTextList.add(name);
        mColorList = new Vector<Integer>();
        for(int i=0;i<mTextList.size();i++) mColorList.add(ColorMaker.getColor());
        printColorList(this, mColorListLayout, mTextList, mColorList);
        mMainView.setList(mTextList, mColorList);
        Message.obtain(mHandler, SEL_CONTENT_DEL).sendToTarget();
        mBtnStart.setEnabled(true);
    }

    public void removeContent(String str){
        if(mTextList.size()<1 || mTextList==null) return;

        for(int i=0;i<mTextList.size();i++){
            if(mTextList.get(i).toString().equals(str)){
                mTextList.remove(i);
                break;
            }
        }

        mColorList = new Vector<Integer>();
        for(int i=0;i<mTextList.size();i++) mColorList.add(ColorMaker.getColor());
        printColorList(this, mColorListLayout, mTextList, mColorList);
        mMainView.setList(mTextList, mColorList);
        Message.obtain(mHandler, SEL_CONTENT_DEL).sendToTarget();
        mBtnStart.setEnabled(true);
    }

    public void clearContents(){
        if(mTextList!=null) mTextList.clear();
        if(mColorList!=null) mColorList.clear();
        printColorList(this, mColorListLayout, mTextList, mColorList);
        mMainView.setList(mTextList, mColorList);
    }

    public String getSelectedContent(){
        if(mTextList==null) return null;
        if(mTextList.size()<2) return null;

        int anglePerContent = 360/mTextList.size();
        int currentAngle = (mLastAngle%360)-90;
        if(currentAngle<0) currentAngle = 360+currentAngle;

        for(int i=0;i<mTextList.size();i++){
            if(currentAngle>=(i*anglePerContent) && currentAngle<=((i+1)*anglePerContent)){
                return mTextList.get(i).toString();
            }
        }

        return null;
    }

    class SelectedContentShowThread extends Thread {

        int mmDelay;

        public SelectedContentShowThread(int delay) {
            mmDelay = delay;
        }

        @Override
        public void run() {
            try {
                sleep(mmDelay);
                Message.obtain(mHandler, SEL_CONTENT_ADD).sendToTarget();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }
}
