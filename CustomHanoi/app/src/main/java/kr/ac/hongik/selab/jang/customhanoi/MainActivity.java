package kr.ac.hongik.selab.jang.customhanoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.Vector;

import static kr.ac.hongik.selab.jang.customhanoi.R.id.text;

public class MainActivity extends AppCompatActivity {

    MainView mMainView;

    Vector<HanoiBlock> m1stPillar; //첫번째 기둥
    Vector<HanoiBlock> m2ndPillar; //두번째 기둥
    Vector<HanoiBlock> m3thPillar; //세번째 기둥
    Vector<HanoiBlock> mSelectedPillar; //사용자가 선택한 기둥

    int count; //이동 횟수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainView = new MainView(this);
        setContentView(mMainView);

        //세 개의 기둥 생성
        m1stPillar = new Vector<HanoiBlock>();
        m2ndPillar = new Vector<HanoiBlock>();
        m3thPillar = new Vector<HanoiBlock>();

        // 첫번째 기둥에 4개의 블록 삽입
        for(int i=0;i<4;i++){
            // **HanoiBlock 생성자의 인자에는 뭘 넣는가??**
            // 블록의 가로 길이 비율을 인자로 넣는다. 최대 길이(100%)는 받침대의 width이다. (ex> 1.0=100%, 0.5=50% ...)
            HanoiBlock hb = new HanoiBlock(1.0-(i*0.2));
            m1stPillar.add(hb);
        }

        //뷰에 기둥 정보 저장
        mMainView.setPillars(m1stPillar, m2ndPillar, m3thPillar);
    }

    private void select(Vector<HanoiBlock> pillar){

        //기존에 선택된 기둥이 없고, 터치한 기둥에 블록이 없다면, 메서드 종료
        if(mSelectedPillar==null && pillar.isEmpty()){
            return;
        }

        if(mSelectedPillar==null){ //기존에 선택된 기둥이 없다면
            //방금 터치한 기둥의 마지막 블록을 선택
            HanoiBlock hb = pillar.lastElement();
            hb.select();
            //현재 선택된 기둥으로 저장
            mSelectedPillar = pillar;
        } else if(mSelectedPillar==pillar) { //기존에 선택된 기둥과 터치한 기둥이 같다면$
            // 선택된 기둥의 마지막 블록을 선택해제
            HanoiBlock hb = mSelectedPillar.lastElement();
            hb.deselect();
            //기존의 선택된 기둥 해제
            mSelectedPillar = null;
        } else { //이 외의 경우라면
            //기존에 선택한 기둥의 마지막 블록을 터치한 기둥으로 옮김
            HanoiBlock hb = mSelectedPillar.lastElement();

            //선택된 기둥의 블록보다 현재 터치한 기둥의 블록이 크다면
            if(pillar.isEmpty() || hb.getWidthRate()<pillar.lastElement().getWidthRate()) {
                //터치한 기둥에 블록 생성
                pillar.add(hb);
                //선택되었던 기둥에서 마지막 블록 제거
                mSelectedPillar.remove(mSelectedPillar.lastElement());
                //toast message
                count++;
                Toast.makeText(getApplicationContext(), "이동횟수 "+count, Toast.LENGTH_SHORT).show();
            }

            hb.deselect();
            mSelectedPillar = null;
        }

        //뷰에 기둥 정보 저장
        mMainView.setPillars(m1stPillar, m2ndPillar, m3thPillar);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            float x = event.getX();
            float y = event.getY();
            int screenWidth = mMainView.getScreenWidth();

            if(x < screenWidth/3){ //화면의 좌측을 터치했다면
                select(m1stPillar);
            } else if(x < screenWidth/3*2){ //화면의 중앙을 터치했다면
                select(m2ndPillar);
            } else { //화면의 우측을 터치했다면
                select(m3thPillar);
            }

            return true;
        }

        return false;
    }
}
