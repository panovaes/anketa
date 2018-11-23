package com.example.anketa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.anketa.answers.CafeAnketaAnswers;
import com.example.anketa.db.DBHelper;
import com.example.anketa.report.AnketaReport;

import java.util.regex.Pattern;





public class CafeAnketa extends Activity {
    private RadioGroup q1;
    private RadioGroup q4;
    private RadioGroup q5;
    private RadioGroup q6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_anketa);

        this.q1 = findViewById(R.id.q1);
        this.q4 = findViewById(R.id.q4);
        this.q5 = findViewById(R.id.q5);
        this.q6 = findViewById(R.id.q6);
    }


    public void submit(View view) {
        CafeAnketaAnswers otvet = new CafeAnketaAnswers();

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
        RatingBar q2_1 = findViewById(R.id.q2_1);
        RatingBar q2_2 = findViewById(R.id.q2_2);
        RatingBar q2_3 = findViewById(R.id.q2_3);
        RatingBar q2_4 = findViewById(R.id.q2_4);
        RatingBar q2_5 = findViewById(R.id.q2_5);
        otvet.q2_1 = q2_1.getRating();
        otvet.q2_2 = q2_2.getRating();
        otvet.q2_3 = q2_3.getRating();
        otvet.q2_4 = q2_4.getRating();
        otvet.q2_5 = q2_5.getRating();


        /**
         * Третий вопрос
         * */
        RatingBar q3_1 = findViewById(R.id.q3_1);
        otvet.q3_1 = q3_1.getRating();


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
        MultiAutoCompleteTextView q7 = findViewById(R.id.q7);
        String q7_otvet = q7.getText().toString().trim();
        if (q7_otvet.equals("")) {
            printError("Отсутствует ответ на седьмой вопрос");
            return;
        }
        otvet.q7 = q7_otvet;


        Pattern emailRegular = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        EditText emailEdit = findViewById(R.id.email);
        if (!emailRegular.matcher(emailEdit.getText().toString().trim()).matches()) {
            printError("Не указан e-mail адрес");
            return;
        }


        otvet.email = emailEdit.getText().toString().trim();

        DBHelper.getInstance().insertAnketa(otvet);

        AnketaReport.sendTo(otvet.email, "Благодарность", AnketaReport.createThank("Оценка кафе"));

        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Сообщение")
                .setMessage("Анкета сохранена")
                .setCancelable(false)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CafeAnketa.this.finish();
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
