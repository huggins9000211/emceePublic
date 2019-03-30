package com.example.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Notes extends AppCompatActivity {

    String notes;
    EditText etNotes;
    Button submit;

    Calendar calander;
    SimpleDateFormat simpleDateFormat;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);

        calander =  Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = simpleDateFormat.format(calander.getTime());

        Intent intentGet = getIntent();
        final int taskKey = intentGet.getIntExtra("intVariableName", 0);

        etNotes = findViewById(R.id.notes);
        submit = findViewById(R.id.nextButt);

        submit = (Button) findViewById(R.id.nextButt);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                notes = etNotes.getText().toString();
                if(TextUtils.isEmpty(notes)) {
                    notes = "none";
                }

                if (taskKey == 0) {
                    Act2.weekly.put("date", date);
                    Act2.weekly.put("notes", notes);
                    FirestoreEntry unneeded = new FirestoreEntry(Act2.weekly, date);
                } else if (taskKey == 1) {
                    Summer.summer.put("date", date);
                    Summer.summer.put("notes", notes);
                    SummerEntry unneeded = new SummerEntry(Summer.summer,
                            date, MainActivity.custName);
                } else if (taskKey == 2) {
                    Repair.repair.put("date", date);
                    Repair.repair.put("notes", notes);
                    RepairEntry unneeded = new RepairEntry(Repair.repair,
                            date, MainActivity.custName);
                }
                Intent intent = new Intent(Notes.this, act5 .class);
                startActivity(intent);
            }
        });

    }
}