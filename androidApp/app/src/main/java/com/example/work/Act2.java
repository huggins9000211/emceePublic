package com.example.work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.HashMap;
import java.util.Map;

public class Act2 extends AppCompatActivity {

    public static HashMap<String, Object> weekly = new HashMap<>();

    String poolType;
    String services;

    CheckBox cbGunite, cbVinyl, cbHotTub, cbVacume, cbBackwash, cbBrush, cbSand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);


        Button nextButt;

        cbGunite = (CheckBox) findViewById(R.id.cbGunite);
        cbVinyl = (CheckBox) findViewById(R.id.cbVinyl);
        cbHotTub = (CheckBox) findViewById(R.id.cbHotTub);
        cbVacume = (CheckBox) findViewById(R.id.cbVacume);
        cbBackwash = (CheckBox) findViewById(R.id.cbBackwash);
        cbBrush = (CheckBox) findViewById(R.id.cbBrush);
        cbSand = (CheckBox) findViewById(R.id.cbSand);

        nextButt = (Button) findViewById(R.id.nextButt);
        nextButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbGunite.isChecked() && cbHotTub.isChecked() && cbVinyl.isChecked())
                {
                    poolType = "Guanine, Vinyl, Hot Tub";
                }
                else if(cbGunite.isChecked() && cbHotTub.isChecked())
                {
                    poolType = "Guanine, Hot Tub";
                }
                else if(cbGunite.isChecked() && cbVinyl.isChecked())
                {
                    poolType = "Guanine, Vinyl ";
                }
                else if(cbVinyl.isChecked() && cbHotTub.isChecked())
                {
                    poolType = "Vinyl, Hot Tub";
                }
                else if(cbGunite.isChecked() )
                {
                    poolType = "Guanine";
                }
                else if(cbVinyl.isChecked() )
                {
                    poolType = "Vinyl";
                }
                else if(cbHotTub.isChecked() )
                {
                    poolType = "Hot Tub";
                }
                else
                {
                    poolType = "Not Entered";
                }

                if (cbVacume.isChecked() && cbBackwash.isChecked() && cbBrush.isChecked() && cbSand.isChecked())
                {
                    services = "vacuum, backwash, brush, sand";
                }
                else if (cbVacume.isChecked() && cbBackwash.isChecked() && cbBrush.isChecked())
                {
                    services = "Vacuum, Backwash, Brush";
                }
                else if (cbVacume.isChecked() && cbBackwash.isChecked() && cbSand.isChecked())
                {
                    services = "Vacuum, Backwash ,Sand";
                }
                else if (cbSand.isChecked() && cbBackwash.isChecked() && cbBrush.isChecked())
                {
                    services = "Sand, Backwash, Brush";

                }
                else if (cbVacume.isChecked() && cbSand.isChecked() && cbBrush.isChecked())
                {
                    services = "Vacuum, Sand, Brush";
                }
                else if (cbVacume.isChecked() && cbBrush.isChecked())
                {
                    services = "Vacuum , Brush";
                }
                else if (cbBackwash.isChecked() && cbBrush.isChecked())
                {
                    services = "Backwash , Brush";
                }
                else if (cbVacume.isChecked() && cbBackwash.isChecked())
                {
                    services = "Vacuum , Backwash";

                }
                else if (cbBackwash.isChecked() && cbSand.isChecked())
                {
                    services = "Backwash , Sand";
                }
                else if (cbSand.isChecked() && cbBrush.isChecked())
                {
                    services = "Sand , Brush";
                }
                else if (cbVacume.isChecked() && cbSand.isChecked())
                {
                    services = "Vacuum , Sand";
                }
                else if (cbVacume.isChecked())
                {
                    services = "Vacuum";
                }
                else if (cbSand.isChecked())
                {
                    services = "Sand";
                }
                else if (cbBackwash.isChecked())
                {
                    services = "Backwash";
                }
                else if (cbBrush.isChecked())
                {
                    services = "Brush";
                }
                else
                {
                    services = "Not Entered";
                }


                weekly.put("poolType", poolType);
                weekly.put("services", services);





                Intent intent = new Intent(Act2.this, Act3.class);
                startActivity(intent);

            }
        });
    }
}