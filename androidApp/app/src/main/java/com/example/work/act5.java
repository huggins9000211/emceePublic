package com.example.work;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class act5 extends AppCompatActivity{
    Button endButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act5);

        endButt = (Button) findViewById(R.id.endButt);

        endButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act5.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
