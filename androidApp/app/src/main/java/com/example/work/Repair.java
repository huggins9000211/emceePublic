package com.example.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.HashMap;

public class Repair extends AppCompatActivity {

    public static HashMap<String, Object> repair = new HashMap<>();

    EditText etRepair, etProblem;
    String strRepair, strProblem;
    Button nextButt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair1);

        etRepair = findViewById(R.id.repair);
        etProblem = findViewById(R.id.problem);
        nextButt = findViewById(R.id.nextButt);

        nextButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                strProblem = etProblem.getText().toString();
                if(TextUtils.isEmpty(strProblem)) {
                    strProblem = "Nothing Enterd ";
                }
                strRepair = etRepair.getText().toString();
                if(TextUtils.isEmpty(strRepair)) {
                    strRepair = "Nothing Enterd";
                }

                repair.put("Problem", strProblem);
                repair.put("Repairs", strRepair);

                Intent intent = new Intent(Repair.this, Repair2.class);
                startActivity(intent);
            }
        });
    }
}
