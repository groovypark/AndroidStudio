package com.example.soovin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mAddButton;
    Button mSubButton;
    Button mMulButton;
    Button mDivButton;

    TextView mFirstNumTextView;
    TextView mSecondNumTextView;
    TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = (Button)findViewById(R.id.addButton);
        mSubButton = (Button)findViewById(R.id.subtractButton);
        mMulButton = (Button)findViewById(R.id.multiplyButton);
        mDivButton = (Button)findViewById(R.id.divideButton);

        mFirstNumTextView = (TextView)findViewById(R.id.number1);
        mSecondNumTextView = (TextView)findViewById(R.id.number2);
        mResultTextView = (TextView)findViewById(R.id.result);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(mFirstNumTextView.getText().toString());
                int b = Integer.parseInt(mSecondNumTextView.getText().toString());
                int result = a+b;
                mResultTextView.setText(Integer.toString(result));
            }
        });

        mSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(mFirstNumTextView.getText().toString());
                int b = Integer.parseInt(mSecondNumTextView.getText().toString());
                int result = a-b;
                mResultTextView.setText(""+result);
            }
        });

        mMulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(mFirstNumTextView.getText().toString());
                int b = Integer.parseInt(mSecondNumTextView.getText().toString());
                int result = a*b;
                mResultTextView.setText(""+result);
            }
        });

        mDivButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(mFirstNumTextView.getText().toString());
                int b = Integer.parseInt(mSecondNumTextView.getText().toString());
                int result = a/b;
                mResultTextView.setText(""+result);
            }
        });
    }

    public int add(int a, int b){
        return a+b;
    }

    public int sub(int a, int b){
        return a-b;
    }

    public int mul(int a, int b){
        return a*b;
    }

    public int div(int a, int b){
        return a/b;
    }

}
