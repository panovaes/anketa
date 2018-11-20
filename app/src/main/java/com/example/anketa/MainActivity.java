package com.example.anketa;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.anketa.db.DBHelper;

import java.util.Arrays;





public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Поиск работы
         * */
        Button startWorkAnketa = findViewById(R.id.anketa_work);
        startWorkAnketa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WorkAnketa.class));
            }
        });


        /**
         * Петомнцы
         * */
        Button startPetAnketa = findViewById(R.id.anketa_pet);
        startPetAnketa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PetAnketa.class));
            }
        });


        /**
         * Оценка сайта
         * */
        Button startSiteAnketa = findViewById(R.id.anketa_site);
        startSiteAnketa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SiteAnketa.class));
            }
        });


        /**
         * Оценка кафе
         * */
        Button startCafeAnketa = findViewById(R.id.anketa_cafe);
        startCafeAnketa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CafeAnketa.class));
            }
        });


        /**
         * Инициализация БД
         */
        DBHelper db = DBHelper.create(this);
    }


    @Override
    protected void onDestroy() {
        DBHelper.getInstance().close();
        super.onDestroy();
    }
}
