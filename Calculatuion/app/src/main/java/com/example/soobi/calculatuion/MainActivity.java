package com.example.soobi.calculatuion;

import java.util.Stack;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    int num = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.Button1)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button2)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button3)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button4)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button5)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button6)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button7)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button8)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button9)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.Button0)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.plus)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.minus)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.mul)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.div)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.result)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.open)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.close)).setOnClickListener(numClick);
        ((Button) findViewById(R.id.del)).setOnClickListener(numClick);

        ((Button) findViewById(R.id.clear)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText et = ((EditText) findViewById(R.id.EditText));
                et.setText("0");
            }
        });
    }

    private View.OnClickListener numClick = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String id = null;
            String s = "";
            EditText et = ((EditText) findViewById(R.id.EditText));
            if (et.getText().toString().charAt(0) == '0') et.setText("");

            switch (v.getId()) {
                case R.id.Button0:
                    id = "0";
                    break;
                case R.id.Button1:
                    id = "1";
                    break;
                case R.id.Button2:
                    id = "2";
                    break;
                case R.id.Button3:
                    id = "3";
                    break;
                case R.id.Button4:
                    id = "4";
                    break;
                case R.id.Button5:
                    id = "5";
                    break;
                case R.id.Button6:
                    id = "6";
                    break;
                case R.id.Button7:
                    id = "7";
                    break;
                case R.id.Button8:
                    id = "8";
                    break;
                case R.id.Button9:
                    id = "9";
                    break;
                case R.id.plus:
                    id = "+";
                    break;
                case R.id.minus:
                    id = "-";
                    break;
                case R.id.mul:
                    id = "*";
                    break;
                case R.id.div:
                    id = "/";
                    break;
                case R.id.result:
                    s = et.getText() + " = " + Calc(et.getText().toString());
                    et.setText(s);
                    return;
                case R.id.open:
                    id = "(";
                    break;
                case R.id.close:
                    id = ")";
                    break;
                case R.id.del:
                    s = et.getText().toString().substring(0, et.length()-1);
                    et.setText(s);
                    return;
                default:
                    break;
            }

            s = et.getText() + id;
            et.setText(s);
        }
    };

    private String Calc(String str) {
        if (str.indexOf('(') != -1) {
            int fs = str.indexOf('(');
            int ls = str.lastIndexOf(')');
            String s = Calc(str.substring(fs + 1, ls));
            str = str.substring(0, fs) + s + str.substring(ls + 1, str.length());
        }

        int cnt = 0;
        Stack<Integer> Stk_Num = new Stack<Integer>();
        StringTokenizer ST_Num = new StringTokenizer(str, "+-/* ");
        StringTokenizer ST_Oper = new StringTokenizer(str, "1234567890 ");

        Stk_Num.push(Integer.parseInt(ST_Num.nextToken()));
        while (ST_Num.hasMoreTokens()) {
            char oper = ST_Oper.nextToken().charAt(0);
            String num = ST_Num.nextToken();
            int a;

            if (oper == '*') {
                a = Stk_Num.pop();
                a *= Integer.parseInt(num);
                Stk_Num.push(a);
            } else if (oper == '/') {
                a = Stk_Num.pop();
                a /= Integer.parseInt(num);
                Stk_Num.push(a);
            } else if (oper == '+') {
                Stk_Num.push(Integer.parseInt(num));
            } else if (oper == '-') {
                Stk_Num.push(-1 * (Integer.parseInt(num)));
            }
        }

        while (!Stk_Num.isEmpty()) {
            cnt += Stk_Num.pop();
        }

        return Integer.toString(cnt);
    }
}