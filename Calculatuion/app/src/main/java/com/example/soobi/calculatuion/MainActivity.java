package com.example.soobi.calculatuion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClicked(View v) {
        List<String> stringList = new ArrayList<String>();
        String text = ((EditText) findViewById(R.id.editText)).getText().toString();
        String number = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                number = number + text.charAt(i);
            } else {
                if (!number.isEmpty()) {
                    stringList.add(number);
                }
                number = "";
                stringList.add(text.charAt(i) + "");
            }
        }
        if (!number.isEmpty()){
            stringList.add(number);
        }

        int start = 0;
        int end = stringList.size()-1;

        for (int i=0; i<stringList.size(); i++) {
            if (stringList.get(i).equals("(")) {
                start = i;
            }
        }
        for (int i=start; i<stringList.size(); i++) {
            if(stringList.get(i).equals(")")) {
                end = i;
                break;
            }
        }

        int mul = -1;
        int dev = -1;
        int plus = -1;
        int minus = -1;

        for (int i=end; i>start; i--) {
            if(stringList.get(i).equals("+")){
                plus = i;
            }
            else if(stringList.get(i).equals("-")){
                minus = i;
            }
            else if(stringList.get(i).equals("*")){
                mul = i;
            }
            else if(stringList.get(i).equals("/")){
                dev = i;
            }
        }
        int top = -1;
        if (plus != -1) {
            top = plus;
        }
        if (minus != -1) {
            top = minus;
        }
        if (mul != -1) {
            top = mul;
        }
        if (dev != -1) {
            top = dev;
        }
        if (top == -1) {
            if ((stringList.get(start).equals("(")) && (stringList.get(end).endsWith(")"))) {
                stringList.set(start,"");
                stringList.set(end,"");
                text="";
                for (int i=0; i<stringList.size(); i++) {
                    if (!stringList.get(i).isEmpty()) {
                        text = text + stringList.get(i);
                    }
                }
                ((EditText) findViewById(R.id.editText)).setText(text);
                return;
            }
        }

        int num1 = Integer.parseInt(stringList.get(top-1).toString());
        int num2 = Integer.parseInt(stringList.get(top+1).toString());
        int result = 0;
        if (stringList.get(top).equals("+")) {
            result = num1 + num2;
        }
        if (stringList.get(top).equals("-")) {
            result = num1 - num2;
        }
        if (stringList.get(top).equals("*")) {
            result = num1 * num2;
        }
        if (stringList.get(top).equals("/")) {
            result = num1 / num2;
        }
        stringList.set(top-1,result+"");
        stringList.set(top,"");
        stringList.set(top+1,"");

        text = "";
        for (int i=0; i<stringList.size(); i++) {
            if(!stringList.get(i).isEmpty()) {
                text = text + stringList.get(i);
            }
        }
        ((EditText) findViewById(R.id.editText)).setText(text);
    }
}
