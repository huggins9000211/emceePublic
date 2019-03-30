package com.example.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Summer3 extends AppCompatActivity {

    EditText baskets, flaps, c, d, e, pads, bumpers;
    String basketsStr, flapsStr, cStr, dStr, eStr, padsStr, bumpersStr;
    Button nextButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summer3);

        baskets = findViewById(R.id.baskets);
        flaps = findViewById(R.id.flaps);
        c = findViewById(R.id.etC);
        d = findViewById(R.id.etD);
        e = findViewById(R.id.etE);
        pads = findViewById(R.id.pads);
        bumpers = findViewById(R.id.bumpers);

        nextButt = findViewById(R.id.nextButt);
        nextButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                basketsStr = baskets.getText().toString();
                if(TextUtils.isEmpty(basketsStr)) {
                    basketsStr = "0";
                }
                flapsStr = flaps.getText().toString();
                if(TextUtils.isEmpty(basketsStr)) {
                    basketsStr = "0";
                }
                cStr = c.getText().toString();
                if(TextUtils.isEmpty(cStr)) {
                    cStr = "0";
                }
                dStr = d.getText().toString();
                if(TextUtils.isEmpty(dStr)) {
                    dStr = "0";
                }
                eStr = e.getText().toString();
                if(TextUtils.isEmpty(eStr)) {
                    eStr = "0";
                }
                padsStr = pads.getText().toString();
                if(TextUtils.isEmpty(padsStr)) {
                    padsStr = "0";
                }
                bumpersStr = bumpers.getText().toString();
                if(TextUtils.isEmpty(bumpersStr)) {
                    bumpersStr = "0";
                }

                Summer.summer.put("Skimmer baskets",basketsStr);
                Summer.summer.put("Skimmer flaps", flapsStr);
                Summer.summer.put("Eyeballs c", cStr);
                Summer.summer.put("Eyeballs d", dStr);
                Summer.summer.put("Eyeballs e", eStr);
                Summer.summer.put("Fulcrum pads", padsStr);
                Summer.summer.put("Ladder bumpers", padsStr);
                Summer.summer.put("Customer name", MainActivity.custName);
                Summer.summer.put("name", MainActivity.empName );
                Summer.summer.put("adress", AdressTest.adress);

                Intent intent = new Intent(Summer3.this, Act4.class);
                intent.putExtra("intVariableName", 1);
                startActivity(intent);
            }
        });
    }
}
