package com.example.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;

public class Summer extends AppCompatActivity {

    public static HashMap<String, Object> summer = new HashMap<>();

    CheckBox cbGunite, cbVinyl, cbHotTub;
    String poolT;
    public static String visitNum;
    RadioGroup visit;
    RadioButton selectedVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summer1);

        Button nextButt;

        cbGunite = (CheckBox) findViewById(R.id.cbGunite);
        cbVinyl = (CheckBox) findViewById(R.id.cbVinyl);
        cbHotTub = (CheckBox) findViewById(R.id.cbHotTub);
        visit = findViewById(R.id.radioGroup);

        nextButt = (Button) findViewById(R.id.nextButt);
        nextButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbGunite.isChecked() && cbHotTub.isChecked() && cbVinyl.isChecked())
                {
                    poolT = "Guanine, Vinyl, Hot Tub";
                }
                else if(cbGunite.isChecked() && cbHotTub.isChecked())
                {
                    poolT = "Guanine, Hot Tub";
                }
                else if(cbGunite.isChecked() && cbVinyl.isChecked())
                {
                    poolT = "Guanine, Vinyl ";
                }
                else if(cbVinyl.isChecked() && cbHotTub.isChecked())
                {
                    poolT = "Vinyl, Hot Tub";
                }
                else if(cbGunite.isChecked() )
                {
                    poolT = "Guanine";
                }
                else if(cbVinyl.isChecked() )
                {
                    poolT = "Vinyl";
                }
                else if(cbHotTub.isChecked() )
                {
                    poolT = "Hot Tub";
                }
                else
                {
                    poolT = "Not Entered";
                }

                int selectedId = visit.getCheckedRadioButtonId();
                selectedVisit = (RadioButton) findViewById(selectedId);
                visitNum = selectedVisit.getText().toString();

                summer.put("pool type",poolT);
                summer.put("visit num", visitNum);

                Intent intent = new Intent(Summer.this, Act3.class);
                startActivity(intent);



            }
        });
    }
}