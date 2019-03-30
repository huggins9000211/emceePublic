package com.example.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.HashMap;

public class Repair2 extends AppCompatActivity {

    public static HashMap<String, Object> repair = new HashMap<>();

    EditText etFlex, etPvc;
    String pvc, pvcFlex;
    Button nextButt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair2);

        etFlex = findViewById(R.id.flex);
        etPvc = findViewById(R.id.pvc);
        nextButt = findViewById(R.id.nextButt);

        nextButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pvcFlex = etPvc.getText().toString();
                if(TextUtils.isEmpty(pvcFlex)) {
                    pvcFlex = "Nothing Enterd";
                }
                pvc = etFlex.getText().toString();
                if(TextUtils.isEmpty(pvc)) {
                    pvc = "Nothing Enterd";
                }

                repair.put("PVC flex parts", pvcFlex);
                repair.put("PVC parts", pvc);

                Intent intent = new Intent(Repair2.this, Repair3.class);
                startActivity(intent);
            }
        });
    }
}
