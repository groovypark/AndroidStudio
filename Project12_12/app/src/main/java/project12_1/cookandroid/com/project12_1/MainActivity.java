package project12_1.cookandroid.com.project12_1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText edtDay, edtName, edtAge, edtWeight, edtPulse, edtExercise;
    EditText edtDayResult, edtNameResult, edtAgeResult, edtWeightResult, edtPulseResult, edtExerciseResult;
    Button btnInit, btnInsert, btnSelect, btnUpdate, btnDelete;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        edtDay = (EditText) findViewById(R.id.edtDay);
        edtName = (EditText) findViewById(R.id.edtName);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtPulse = (EditText) findViewById(R.id.edtPulse);
        edtExercise = (EditText) findViewById(R.id.edtExercise);

        edtDayResult = (EditText) findViewById(R.id.edtDayResult);
        edtNameResult = (EditText) findViewById(R.id.edtNameResult);
        edtAgeResult = (EditText) findViewById(R.id.edtAgeResult);
        edtWeightResult = (EditText) findViewById(R.id.edtWeightResult);
        edtPulseResult = (EditText) findViewById(R.id.edtPulseResult);
        edtExerciseResult = (EditText) findViewById(R.id.edtExerciseResult);

        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnSelect = (Button)findViewById(R.id.btnSelect);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        myHelper = new myDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2); //인수는 아무거나 입력하면 됨.
                sqlDB.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO Health VALUES('"
                        +edtDay.getText().toString()+"','"
                        +edtName.getText().toString()+"',"
                        +edtAge.getText().toString()+","
                        +edtWeight.getText().toString()+","
                        +edtPulse.getText().toString()+","
                        +edtExercise.getText().toString()+");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력됨",
                        Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM Health;",null);

                String strDays = "날짜" + "\r\n" + "--------" + "\r\n";
                String strNames = "이름" + "\r\n" + "--------" + "\r\n";
                String strAges = "나이" + "\r\n" + "--------" + "\r\n";
                String strWeights = "몸무게" + "\r\n" + "--------" + "\r\n";
                String strPulses = "혈압" + "\r\n" + "--------" + "\r\n";
                String strExercises = "운동" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()){
                    strDays += cursor.getString(0) + "\r\n";
                    strNames += cursor.getString(1) + "\r\n";
                    strAges += cursor.getString(2) + "\r\n";
                    strWeights += cursor.getString(3) + "\r\n";
                    strPulses += cursor.getString(4) + "\r\n";
                    strExercises += cursor.getString(5) + "\r\n";
                }

                edtDayResult.setText(strDays);
                edtNameResult.setText(strNames);
                edtAgeResult.setText(strAges);
                edtWeightResult.setText(strWeights);
                edtPulseResult.setText(strPulses);
                edtExerciseResult.setText(strExercises);

//                edtNameResult.setText(strNames);
//                edtNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });


        //변경된 인원 WHERE 변경할 그룹 이름
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE Health SET " +
                        "userName = '"+edtName.getText().toString()+"', " +
                        "age = "+edtAge.getText().toString()+","+
                        "weight = "+edtWeight.getText().toString()+","+
                        "pulse = "+edtPulse.getText().toString()+","+
                        "exercise = "+edtExercise.getText().toString()+
                        " WHERE day='"+edtDay.getText().toString()+"';");
//              sqlDB.execSQL("UPDATE Health SET exercise = "+edtExercise.getText().toString()+" WHERE day='"+edtDay.getText().toString()+"';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"수정됨",
                        Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        //삭제할 그룹 이름
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM Health WHERE day= '"+edtDay.getText().toString()+"';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"삭제됨",
                        Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "Health", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Health (" +
                    " day char(15) PRIMARY KEY, userName char(15), age integer, " +
                    "weight integer, pulse integer, exercise integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Health");
            onCreate(db);
        }
    }
}