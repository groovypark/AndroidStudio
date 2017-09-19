package com.example.soobi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn0 = (Button) findViewById(R.id.btn0);
        final Button btn1 = (Button) findViewById(R.id.btn1);
        final Button btn2 = (Button) findViewById(R.id.btn2);
        final Button btn3 = (Button) findViewById(R.id.btn3);
        final Button btn4 = (Button) findViewById(R.id.btn4);
        final Button btn5 = (Button) findViewById(R.id.btn5);
        final Button btn6 = (Button) findViewById(R.id.btn6);
        final Button btn7 = (Button) findViewById(R.id.btn7);
        final Button btn8 = (Button) findViewById(R.id.btn8);
        final Button btn9 = (Button) findViewById(R.id.btn9);
        final Button btnAdd = (Button) findViewById(R.id.btnAdd);
        final Button btnSub = (Button) findViewById(R.id.btnSub);
        final Button btnMul = (Button) findViewById(R.id.btnMul);
        final Button btnDiv = (Button) findViewById(R.id.btnDiv);
        final Button btnEqual = (Button) findViewById(R.id.btnEqual);

        final TextView textView = (TextView) findViewById(R.id.textView);

        final Map<String, String> calc = new HashMap<String, String>();
        calc.put("reset", "N");

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), ((Button) v).getText(), Toast.LENGTH_SHORT).show();

                String text = "";

                // 숫자라면 input
                if ( !((Button) v).getText().equals("+")
                        && !((Button) v).getText().equals("-")
                        && !((Button) v).getText().equals("/")
                        && !((Button) v).getText().equals("*")
                        && !((Button) v).getText().equals("=")) {

                    if ( calc.get("reset").equals("Y") ) {
                        calc.put("reset", "N");
                        calc.put("number1", "");
                        calc.put("operation", "");

                        text = (String) ((Button) v).getText();
                        textView.setText( Integer.parseInt(text) + "" );
                    }
                    else {
                        text = (String) textView.getText();
                        text += ((Button) v).getText();

                        textView.setText( Integer.parseInt(text) + "" );
                    }
                }

                // 연산자라면 연산자와 이전에 입력한 숫자 input
                if ( ((Button) v).getText().equals("+")
                        || ((Button) v).getText().equals("-")
                        || ((Button) v).getText().equals("/")
                        || ((Button) v).getText().equals("*")) {
                    calc.put("operation", (String) ((Button) v).getText());
                    calc.put("number1", (String) textView.getText());
                    textView.setText( "0" );
                }

                // = 이퀄 시 연산
                if ( ((Button) v).getText().equals("=") ) {
                    int number1 = Integer.parseInt(calc.get("number1"));
                    int number2 = Integer.parseInt((String) textView.getText());

                    int result = 0;

                    // 연산자에 따른 연산 처리
                    if ( calc.get("operation").equals("+") ) {
                        result = number1 + number2;
                    }
                    else if ( calc.get("operation").equals("-") ) {
                        result = number1 - number2;
                    }
                    else if ( calc.get("operation").equals("*") ) {
                        result = number1 * number2;
                    }
                    else if ( calc.get("operation").equals("/") ) {
                        double divResult = number1 / number2;
                        result = (int) divResult;
                    }

                    // 리셋 값 (결과 시)
                    calc.put("reset", "Y");

                    textView.setText( result + "" );
                }
            }
        };

        btn0.setOnClickListener(clickListener);
        btn1.setOnClickListener(clickListener);
        btn2.setOnClickListener(clickListener);
        btn3.setOnClickListener(clickListener);
        btn4.setOnClickListener(clickListener);
        btn5.setOnClickListener(clickListener);
        btn6.setOnClickListener(clickListener);
        btn7.setOnClickListener(clickListener);
        btn8.setOnClickListener(clickListener);
        btn9.setOnClickListener(clickListener);
        btnAdd.setOnClickListener(clickListener);
        btnSub.setOnClickListener(clickListener);
        btnMul.setOnClickListener(clickListener);
        btnDiv.setOnClickListener(clickListener);
        btnEqual.setOnClickListener(clickListener);
    }
}

