package com.example.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Summer2 extends AppCompatActivity {

    EditText phLvlButt, chlorieneLvlButt;
    String clarity;
    String chlorienelvl;
    String phlvl;
    RadioGroup clarityG;
    RadioButton selectedClarity;
    Button nextButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summer2);

        phLvlButt = findViewById(R.id.phlvl);
        chlorieneLvlButt = findViewById(R.id.chlorinelvl);
        clarityG = findViewById(R.id.radioGroup);

        nextButt = findViewById(R.id.nextButt);
        nextButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = clarityG.getCheckedRadioButtonId();
                selectedClarity = (RadioButton) findViewById(selectedId);
                clarity = selectedClarity.getText().toString();

                chlorienelvl = chlorieneLvlButt.getText().toString();
                if(TextUtils.isEmpty(chlorienelvl)) {
                    chlorienelvl = "0";
                }
                phlvl = phLvlButt.getText().toString();
                if(TextUtils.isEmpty(phlvl)) {
                    phlvl = "0";
                }

                Summer.summer.put("Pool clarity",clarity);
                Summer.summer.put("Chlorine lvl", chlorienelvl);
                Summer.summer.put("pH lvl", phlvl);

                Intent intent = new Intent(Summer2.this, Summer3.class);
                startActivity(intent);


            }
        });
    }
}