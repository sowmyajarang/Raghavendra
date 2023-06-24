package com.raghavendra.raghavendra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Entrance extends AppCompatActivity {

    Button mentry,mbtnredirecttosearchpage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);

        mentry = findViewById(R.id.btnentry);
        mentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        mbtnredirecttosearchpage = findViewById(R.id.btnredirecttosearchpage);
        mbtnredirecttosearchpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
                       {
                startActivity(new Intent(getApplicationContext(), SearchPage.class));
            }
        });

    }

}


