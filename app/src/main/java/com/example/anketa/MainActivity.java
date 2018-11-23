package com.example.anketa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.anketa.db.DBHelper;
import com.example.anketa.report.AnketaReport;





public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Инициализация БД
         */
        DBHelper.create(this);
        AnketaReport.init(this);

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
         * Просмотр статистики Кафе
         */
        Button statisticCafe = findViewById(R.id.statisticCafe);
        statisticCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatistics("CAFE");
            }
        });


        /**
         * Просмотр статистики Поиска работы
         */
        Button statisticWork = findViewById(R.id.statisticWork);
        statisticWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatistics("WORK");
            }
        });


        /**
         * Просмотр статистики Веб сайта
         */
        Button statisticSite = findViewById(R.id.statisticSite);
        statisticSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatistics("SITE");
            }
        });


        /**
         * Просмотр статистики Питомцев
         */
        Button statisticPet = findViewById(R.id.statisticPet);
        statisticPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatistics("PET");
            }
        });


        /**
         * Отправка статистики
         */
        Button sendResult = findViewById(R.id.sendStatistic);
        sendResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String html = AnketaReport.createReport();
                    // TODO: Куда отправляем
                    AnketaReport.sendTo("Katyuha1506@yandex.ru", "Отчет", html);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Информация")
                            .setMessage("Отчет отправлен")
                            .setCancelable(false)
                            .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Ничего не делаем
                                }
                            });

                    dialog.show();
                } catch (Throwable e) {
                    Log.e("CCC", "onClick: ", e);
                }
            }
        });
    }


    private void showStatistics(String anketaType) {
        Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
        intent.putExtra("ANKETA_TYPE", anketaType);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        DBHelper.getInstance().close();
        super.onDestroy();
    }
}
