package com.example.work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TaskChoice extends AppCompatActivity {
    Button sumButt;
    Button weekButt;
    Button repairButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_choice);

        sumButt = findViewById(R.id.sumButt);
        weekButt = findViewById(R.id.weeklyButt);
        repairButt = findViewById(R.id.repairButt);

        sumButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskChoice.this, Summer.class);
                startActivity(intent);

            }
        });

        weekButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskChoice.this, Act2.class);
                startActivity(intent);

            }
        });

        repairButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskChoice.this, Repair.class);
                startActivity(intent);

            }
        });
    }
}
