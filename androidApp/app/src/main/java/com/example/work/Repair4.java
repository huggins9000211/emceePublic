package com.example.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Repair4 extends AppCompatActivity {


    String elboes;
    String couplings;
    String male;
    String female;
    String poly;
    String ts;
    String street;
    String valves;
    String clamps;
    String unions;
    String nipples;

    EditText etElbows;
    EditText etCouplings;
    EditText etMale;
    EditText etFemale;
    EditText etPoly;
    EditText etTs;
    EditText etStreet;
    EditText etValves;
    EditText etClamps;
    EditText etUnions, etNipples;

    Button nextButt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intentGet = getIntent();
        final int taskKey = intentGet.getIntExtra("intVariableName", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair3);

        etElbows = (EditText) findViewById(R.id.elbows);
        etCouplings = (EditText) findViewById(R.id.couplings);
        etMale = (EditText) findViewById(R.id.male);
        etFemale = (EditText) findViewById(R.id.female);
        etPoly = (EditText) findViewById(R.id.poly);
        etTs = (EditText) findViewById(R.id.ts);
        etStreet = (EditText) findViewById(R.id.streetels);
        etValves = (EditText) findViewById(R.id.valves);
        etClamps = (EditText) findViewById(R.id.clamps);
        etUnions = (EditText) findViewById(R.id.unions);
        etNipples = findViewById(R.id.nipples);
        nextButt = (Button) findViewById(R.id.nextButt);
        nextButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                elboes = etElbows.getText().toString();
                if (TextUtils.isEmpty(elboes)) {
                    elboes = "0";
                }
                couplings = etCouplings.getText().toString();
                if (TextUtils.isEmpty(couplings)) {
                    couplings = "0";
                }
                male = etMale.getText().toString();
                if (TextUtils.isEmpty(male)) {
                    male = "0";
                }
                female = etFemale.getText().toString();
                if (TextUtils.isEmpty(female)) {
                    female = "0";
                }
                poly = etPoly.getText().toString();
                if (TextUtils.isEmpty(poly)) {
                    poly = "0";
                }
                ts = etTs.getText().toString();
                if (TextUtils.isEmpty(ts)) {
                    ts = "0";
                }
                street = etStreet.getText().toString();
                if (TextUtils.isEmpty(street)) {
                    street = "0";
                }
                valves = etValves.getText().toString();
                if (TextUtils.isEmpty(valves)) {
                    valves = "0";
                }
                clamps = etClamps.getText().toString();
                if (TextUtils.isEmpty(clamps)) {
                    clamps = "0";
                }
                unions = etUnions.getText().toString();
                if (TextUtils.isEmpty(unions)) {
                    unions = "0";
                }
                nipples = etNipples.getText().toString();
                if (TextUtils.isEmpty(nipples)) {
                    nipples = "0";
                }

                Repair.repair.put("Elbows 2 in", elboes);
                Repair.repair.put("Couplings 2 in", couplings);
                Repair.repair.put("Male Adaptor 2 in", male);
                Repair.repair.put("Female Adaptor 2 in", female);
                Repair.repair.put("Poly Adaptor 2 in", poly);
                Repair.repair.put("Ts 2 in", ts);
                Repair.repair.put("Street Els 2 in", street);
                Repair.repair.put("Valves 2 in", valves);
                Repair.repair.put("Clamps 2 in", clamps);
                Repair.repair.put("Unions 2 in", unions);
                Repair.repair.put("Nipples 2 in", nipples);
                Repair.repair.put("custName", MainActivity.custName);
                Repair.repair.put("name", MainActivity.empName );
                Repair.repair.put("adress", AdressTest.adress);


                Intent intent = new Intent(Repair4.this, Notes.class);
                intent.putExtra("intVariableName", 2);
                startActivity(intent);


            }
        });

    }
}