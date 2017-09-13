package com.example.soobi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        mSubButton = (Button)findViewById(R.id.substractButton);
        mMulButton = (Button)findViewById(R.id.multiplyButton);
        mDivButton = (Button)findViewById(R.id.divideButton);

        mFirstNumTextView = (TextView)findViewById(R.id.editText);
        mSecondNumTextView = (TextView)findViewById(R.id.editText2);
        mResultTextView = (TextView)findViewById(R.id.textView);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(mFirstNumTextView.getText().toString());
                int b = Integer.parseInt(mSecondNumTextView.getText().toString());
                int result = a+b;
                mResultTextView.setText(Integer.toString((result)));
            }
        });

        mSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(mFirstNumTextView.getText().toString());
                int b = Integer.parseInt(mSecondNumTextView.getText().toString());
                int result = a-b;
                mResultTextView.setText(Integer.toString((result)));
            }
        });

        mMulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(mFirstNumTextView.getText().toString());
                int b = Integer.parseInt(mSecondNumTextView.getText().toString());
                int result = a*b;
                mResultTextView.setText(Integer.toString((result)));
            }
        });

        mDivButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(mFirstNumTextView.getText().toString());
                int b = Integer.parseInt(mSecondNumTextView.getText().toString());
                int result = a/b;
                mResultTextView.setText(Integer.toString((result)));
            }
        });

    }

}
