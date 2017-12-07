package kr.ac.hongik.selab.jang.customhanoi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.Vector;

public class MainView extends View {

    private final static int COLOR_PILLAR = Color.DKGRAY;

    Paint mPaint;
    int mScreenWidth; //화면 가로길이
    int mScreenHeight; //화면 세로길이
    int mPillarWidth; //기둥 width
    int mPillarHeight; //기둥 height
    int mPedestalWidth; //받침대 width
    int mPedestalHeight; //받침대 height
    int mPedestalDistance; //받침대 간격
    int mBlockHeight; //블록의 높이

    Vector<HanoiBlock> m1stPillar; //첫번째 기둥
    Vector<HanoiBlock> m2ndPillar; //두번째 기둥
    Vector<HanoiBlock> m3thPillar; //세번째 기둥

    public MainView(Context context) {
        super(context);
        mPaint = new Paint();

        //화면 크기 저장
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        //받침대 크기 설정(화면 크기 대비로)
        mPedestalWidth = mScreenWidth/4;
        mPedestalHeight = mScreenWidth/20;

        //받침대 간격 설정(화면 크기 대비로)
        mPedestalDistance = mPedestalWidth/2;

        //기둥 크기 설정(화면 크기 대비로)
        mPillarWidth = mScreenWidth/20;
        mPillarHeight = mPedestalHeight*8;

        //블록 높이 설정(화면 크기 대비로)
        mBlockHeight = mPedestalHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //기둥 세개 그리기
        drawPillars(canvas);

        //첫번째 받침대와 블록들 그리기
        int pedestalLeftStart = 0;
        drawPedestal(pedestalLeftStart, canvas);
        if(m1stPillar!=null) drawBlocks(m1stPillar, pedestalLeftStart, canvas);

        //두번째 받침대와 블록들 그리기
        pedestalLeftStart = pedestalLeftStart+mPedestalWidth+mPedestalDistance;
        drawPedestal(pedestalLeftStart, canvas);
        if(m2ndPillar!=null) drawBlocks(m2ndPillar, pedestalLeftStart, canvas);

        //세번째 받침대와 블록들 그리기
        pedestalLeftStart = pedestalLeftStart+mPedestalWidth+mPedestalDistance;
        drawPedestal(pedestalLeftStart, canvas);
        if(m3thPillar!=null) drawBlocks(m3thPillar, pedestalLeftStart, canvas);
    }

    private void drawPillars(Canvas canvas){
        mPaint.setColor(COLOR_PILLAR);
        for(int i=0;i<3;i++){
            int pillarLeftStart = ((mPedestalWidth+mPedestalDistance)*i) + (mPedestalWidth/2) - (mPillarWidth/2);
            canvas.drawRect(pillarLeftStart, mScreenHeight-mPillarHeight, pillarLeftStart+mPillarWidth, mScreenHeight, mPaint);
        }
    }

    private void drawPedestal(int pedestalLeftStart, Canvas canvas){
        mPaint.setColor(COLOR_PILLAR);
        canvas.drawRect(pedestalLeftStart, mScreenHeight-mPedestalHeight, pedestalLeftStart+mPedestalWidth, mScreenHeight, mPaint);
    }

    private void drawBlocks(Vector<HanoiBlock> currentBlocks, int pedestalLeftStart, Canvas canvas){
        //블록들을 밑에서부터 차곡차곡 그린다.
        for(int i=0;i<currentBlocks.size();i++) {
            mPaint.setColor(currentBlocks.get(i).getColor());
            int blockWidth = (int)(mPedestalWidth*currentBlocks.get(i).getWidthRate());
            int blockTopStart = mScreenHeight-mPedestalHeight-(mBlockHeight*(i+1));
            int blockLeftStart = pedestalLeftStart+((mPedestalWidth-blockWidth)/2);
            canvas.drawRect(blockLeftStart, blockTopStart, blockLeftStart + blockWidth, blockTopStart + mBlockHeight, mPaint);
        }
    }

    public int getScreenWidth(){
        return mScreenWidth;
    }

    public void setPillars(Vector<HanoiBlock> pillar1, Vector<HanoiBlock> pillar2, Vector<HanoiBlock> pillar3){
        m1stPillar = pillar1;
        m2ndPillar = pillar2;
        m3thPillar = pillar3;
        postInvalidate();
    }
}
