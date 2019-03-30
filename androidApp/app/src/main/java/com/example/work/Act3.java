package com.example.work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Act3 extends AppCompatActivity {
    Calendar calander;
    SimpleDateFormat simpleDateFormat;
    String date;

    String phLvl ;
    String chlorineLvl;
    String saltLvl;
    String sodiumLvl ;
    String calciumLvl;
    String conditionerLvl;

    EditText phIn;
    EditText chlorineIn;
    EditText saltIn;
    EditText sodiumIn;
    EditText calciumIn;
    EditText conditionerIn;

    Button nextButt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act3);

        phIn = (EditText)findViewById(R.id.et6);
        chlorineIn = (EditText)findViewById(R.id.et5);
        saltIn = (EditText)findViewById(R.id.et4);
        sodiumIn = (EditText)findViewById(R.id.et3);
        calciumIn = (EditText)findViewById(R.id.et2);
        conditionerIn = (EditText)findViewById(R.id.et);
        nextButt = (Button) findViewById(R.id.nextButt);
        nextButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                phLvl = phIn.getText().toString();
                if(TextUtils.isEmpty(phLvl)) {
                    phLvl = "0";
                }
                chlorineLvl = chlorineIn.getText().toString();
                if(TextUtils.isEmpty(chlorineLvl)) {
                    chlorineLvl = "0";
                }
                saltLvl = saltIn.getText().toString();
                if(TextUtils.isEmpty(saltLvl)) {
                    saltLvl = "0";
                }
                sodiumLvl = sodiumIn.getText().toString();
                if(TextUtils.isEmpty(sodiumLvl)) {
                    sodiumLvl = "0";
                }
                calciumLvl = calciumIn.getText().toString();

                conditionerLvl = conditionerIn.getText().toString();
                if(TextUtils.isEmpty(conditionerLvl)) {
                    conditionerLvl = "0";
                }

                Act2.weekly.put("phLvl", phLvl);
                Act2.weekly.put("chlorineLvl", chlorineLvl);
                Act2.weekly.put("saltLvl", saltLvl);
                Act2.weekly.put("sodiumLvl", sodiumLvl);
                Act2.weekly.put("calciumLvl", calciumLvl);
                Act2.weekly.put("conditionerLvl", conditionerLvl);
                Act2.weekly.put("custName", MainActivity.custName);
                Act2.weekly.put("name", MainActivity.empName );
                Act2.weekly.put("adress", AdressTest.adress);



                Intent intent = new Intent(Act3.this, Act4.class);
                intent.putExtra("intVariableName", 0);
                startActivity(intent);









            }
        });
    }
}
