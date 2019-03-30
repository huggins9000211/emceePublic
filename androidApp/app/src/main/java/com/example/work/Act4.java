package com.example.work;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class  Act4 extends AppCompatActivity {



    String ph ;
    //pounds
    String chlorine;
    //tabs
    String salt;
    //bag 40 pounds
    String sodium ;
    //pounds
    String calcium;
    //pounds
    String conditioner;
    //pounds
    String shock;
    //gallons
    String acid ;
    //gallons
    String yellowtrine;
    //pounds
    String clarifier;
    //4 oz

    EditText et10;
    EditText et9;
    EditText et8;
    EditText et7;
    EditText et6;
    EditText et5;
    EditText et4;
    EditText et3;
    EditText et2;
    EditText et;

    Button nextButt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intentGet = getIntent();
        final int taskKey = intentGet.getIntExtra("intVariableName", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act4);

        et10 =(EditText)findViewById(R.id.et10);
        et9 =(EditText)findViewById(R.id.et9);
        et8 =(EditText)findViewById(R.id.et8);
        et7 =(EditText)findViewById(R.id.et7);
        et6 = (EditText)findViewById(R.id.et6);
        et5 = (EditText)findViewById(R.id.et5);
        et4 = (EditText)findViewById(R.id.et4);
        et3 = (EditText)findViewById(R.id.et3);
        et2 = (EditText)findViewById(R.id.et2);
        et = (EditText)findViewById(R.id.et);
        nextButt = (Button) findViewById(R.id.nextButt);
        nextButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                acid = et10.getText().toString();
                if(TextUtils.isEmpty(acid)) {
                    acid = "0";
                }
                shock = et9.getText().toString();
                if(TextUtils.isEmpty(shock)) {
                    shock = "0";
                }
                chlorine = et8.getText().toString();
                if(TextUtils.isEmpty(chlorine)) {
                    chlorine = "0";
                }
                salt = et7.getText().toString();
                if(TextUtils.isEmpty(salt)) {
                    salt = "0";
                }
                ph = et6.getText().toString();
                if(TextUtils.isEmpty(ph)) {
                    ph = "0";
                }
                yellowtrine = et5.getText().toString();
                if(TextUtils.isEmpty(yellowtrine)) {
                    yellowtrine = "0";
                }
                clarifier= et4.getText().toString();
                if(TextUtils.isEmpty(clarifier)) {
                    clarifier = "0";
                }
                sodium = et3.getText().toString();
                if(TextUtils.isEmpty(sodium)) {
                    sodium = "0";
                }
                calcium = et2.getText().toString();
                if(TextUtils.isEmpty(calcium)) {
                    calcium = "0";
                }
                conditioner = et.getText().toString();
                if(TextUtils.isEmpty(conditioner)) {
                    conditioner = "0";
                }

                if(taskKey == 0) {
                    Act2.weekly.put("conditioner", conditioner);
                    Act2.weekly.put("calcium", conditioner);
                    Act2.weekly.put("sodium", conditioner);
                    Act2.weekly.put("clarifier", conditioner);
                    Act2.weekly.put("yellowtrine", conditioner);
                    Act2.weekly.put("ph", conditioner);
                    Act2.weekly.put("salt", conditioner);
                    Act2.weekly.put("chlorine", conditioner);
                    Act2.weekly.put("shock", conditioner);
                    Act2.weekly.put("acid", acid);
                }
                else if (taskKey == 1){
                    Summer.summer.put("conditioner", conditioner);
                    Summer.summer.put("calcium", conditioner);
                    Summer.summer.put("sodium", conditioner);
                    Summer.summer.put("clarifier", conditioner);
                    Summer.summer.put("yellowtrine", conditioner);
                    Summer.summer.put("ph", conditioner);
                    Summer.summer.put("salt", conditioner);
                    Summer.summer.put("chlorine", conditioner);
                    Summer.summer.put("shock", conditioner);
                    Summer.summer.put("acid", acid);
                }



                Intent intent = new Intent(Act4.this, Notes.class);
                intent.putExtra("intVariableName", taskKey);
                startActivity(intent);


            }
        });

    }







}
