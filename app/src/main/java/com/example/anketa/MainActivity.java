package com.example.anketa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;





public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startWorkAnketa = findViewById(R.id.anketa_work);
        startWorkAnketa.setOnClickListener((e) -> startActivity(new Intent(MainActivity.this, WorkAnketa.class)));
    }
}
