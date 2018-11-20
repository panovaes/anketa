package com.example.anketa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.anketa.answers.SiteAnketaAnswers;
import com.example.anketa.db.DBHelper;

import java.util.regex.Pattern;





public class SiteAnketa extends Activity {
    private RadioGroup q1;
    private RadioGroup q2;
    private RadioGroup q3;
    private RadioGroup q4;
    private RadioGroup q5;
    private RadioGroup q6;
    private RadioGroup q7;
    private RadioGroup q8;
    private RadioGroup q9;
    private RadioGroup q10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_anketa);

        this.q1 = findViewById(R.id.q1);
        this.q2 = findViewById(R.id.q2);
        this.q3 = findViewById(R.id.q3);
        this.q4 = findViewById(R.id.q4);
        this.q5 = findViewById(R.id.q5);
        this.q6 = findViewById(R.id.q6);
        this.q7 = findViewById(R.id.q7);
        this.q8 = findViewById(R.id.q8);
        this.q9 = findViewById(R.id.q9);
        this.q10 = findViewById(R.id.q10);
    }


    public void submit(View view) {
        SiteAnketaAnswers otvet = new SiteAnketaAnswers();

        /**
         * Первый вопрос
         * */
        int q1RadioButtonID = q1.getCheckedRadioButtonId();
        // Не выбрано
        if (q1RadioButtonID == -1) {
            printError("Отсутствует ответ на первый вопрос");
            return;
        }

        otvet.q1 = getText(q1, q1RadioButtonID);


        /**
         * Второй вопрос
         * */
        int q2RadioButtonID = q2.getCheckedRadioButtonId();
        // Не выбрано
        if (q2RadioButtonID == -1) {
            printError("Отсутствует ответ на второй вопрос");
            return;
        }

        otvet.q2 = getText(q2, q2RadioButtonID);


        /**
         * Третий вопрос
         * */
        int q3RadioButtonID = q3.getCheckedRadioButtonId();
        // Не выбрано
        if (q3RadioButtonID == -1) {
            printError("Отсутствует ответ на третий вопрос");
            return;
        }

        otvet.q3 = getText(q3, q3RadioButtonID);


        /**
         * Четвертый вопрос
         * */
        int q4RadioButtonID = q4.getCheckedRadioButtonId();
        // Не выбрано
        if (q4RadioButtonID == -1) {
            printError("Отсутствует ответ на четвертый вопрос");
            return;
        }

        otvet.q4 = getText(q4, q4RadioButtonID);


        /**
         * Пятый вопрос
         * */
        int q5RadioButtonID = q5.getCheckedRadioButtonId();
        // Не выбрано
        if (q5RadioButtonID == -1) {
            printError("Отсутствует ответ на пятый вопрос");
            return;
        }

        otvet.q5 = getText(q5, q5RadioButtonID);


        /**
         * Шестой вопрос
         * */
        int q6RadioButtonID = q6.getCheckedRadioButtonId();
        // Не выбрано
        if (q6RadioButtonID == -1) {
            printError("Отсутствует ответ на шестой вопрос");
            return;
        }

        otvet.q6 = getText(q6, q6RadioButtonID);


        /**
         * Седьмой вопрос
         * */
        int q7RadioButtonID = q7.getCheckedRadioButtonId();
        // Не выбрано
        if (q7RadioButtonID == -1) {
            printError("Отсутствует ответ на седьмой вопрос");
            return;
        }

        otvet.q7 = getText(q7, q7RadioButtonID);


        /**
         * Восьмой вопрос
         * */
        int q8RadioButtonID = q8.getCheckedRadioButtonId();
        // Не выбрано
        if (q8RadioButtonID == -1) {
            printError("Отсутствует ответ на восьмой вопрос");
            return;
        }

        otvet.q8 = getText(q8, q8RadioButtonID);


        /**
         * Девятый вопрос
         * */
        int q9RadioButtonID = q9.getCheckedRadioButtonId();
        // Не выбрано
        if (q9RadioButtonID == -1) {
            printError("Отсутствует ответ на девятый вопрос");
            return;
        }

        otvet.q9 = getText(q9, q9RadioButtonID);


        /**
         * Десятый вопрос
         * */
        int q10RadioButtonID = q10.getCheckedRadioButtonId();
        // Не выбрано
        if (q10RadioButtonID == -1) {
            printError("Отсутствует ответ на десятый вопрос");
            return;
        }

        otvet.q10 = getText(q10, q10RadioButtonID);


        Pattern emailRegular = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        EditText emailEdit = findViewById(R.id.email);
        if (!emailRegular.matcher(emailEdit.getText().toString().trim()).matches()) {
            printError("Не указан e-mail адрес");
            return;
        }


        otvet.email = emailEdit.getText().toString().trim();


        DBHelper.getInstance().insertSiteAnketa(otvet);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Сообщение")
                .setMessage("Анкета сохранена")
                .setCancelable(false)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SiteAnketa.this.finish();
                    }
                });

        dialog.show();
    }


    public String getText(RadioGroup radioGroup, int radioId) {
        View radioButton = radioGroup.findViewById(radioId);
        int index = radioGroup.indexOfChild(radioButton);
        RadioButton button = (RadioButton) radioGroup.getChildAt(index);
        return button.getText().toString();
    }


    public void printError(String error) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Ничего не делаем
                    }
                });

        dialog.show();
    }
}
