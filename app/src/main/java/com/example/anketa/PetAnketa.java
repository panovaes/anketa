package com.example.anketa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.regex.Pattern;





public class PetAnketa extends Activity {
    private RadioGroup q1;
    private RadioGroup q3;
    private ArrayList<CheckBox> q7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_anketa);

        this.q1 = findViewById(R.id.q1);
        this.q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                EditText other = findViewById(R.id.q1_other);
                other.setEnabled(checkedId == R.id.q1_2);
            }
        });


        this.q3 = findViewById(R.id.q3);

        this.q7 = new ArrayList<>();
        this.q7.add((CheckBox) findViewById(R.id.q7_1));
        this.q7.add((CheckBox) findViewById(R.id.q7_2));
        this.q7.add((CheckBox) findViewById(R.id.q7_3));
        this.q7.add((CheckBox) findViewById(R.id.q7_4));
        this.q7.add((CheckBox) findViewById(R.id.q7_5));
        this.q7.add((CheckBox) findViewById(R.id.q7_6));
    }


    public void submit(View view) {
        PetAnketaAnswers otvet = new PetAnketaAnswers();


        int firstRadioButtonID = q1.getCheckedRadioButtonId();
        // Не выбрано
        if (firstRadioButtonID == -1) {
            printError("Отсутствует ответ на первый вопрос");
            return;
        }


        String q1Otvet = "";
        if (firstRadioButtonID == R.id.q1_2) {
            EditText other = findViewById(R.id.q1_other);
            q1Otvet = other.getText().toString();
            if (q1Otvet.trim().equals("")) {
                printError("Отсутствует ответ на первый вопрос");
                return;
            }
        } else {
            q1Otvet = getText(q1, firstRadioButtonID);
        }
        otvet.q1 = q1Otvet;



        EditText q2_1 = findViewById(R.id.q2_1);
        EditText q2_2 = findViewById(R.id.q2_2);
        EditText q2_3 = findViewById(R.id.q2_3);

        String q2_1_otvet = q2_1.getText().toString();
        String q2_2_otvet = q2_2.getText().toString();
        String q2_3_otvet = q2_3.getText().toString();

        if (q2_1_otvet.trim().equals("")) {
            printError("Не указано 'Самое красивое' животное");
            return;
        }

        if (q2_2_otvet.trim().equals("")) {
            printError("Не указано 'Самое прелестное' животное");
            return;
        }

        if (q2_3_otvet.trim().equals("")) {
            printError("Не указано 'Самое гадкое' животное");
            return;
        }

        otvet.q2_1 = q2_1_otvet;
        otvet.q2_2 = q2_2_otvet;
        otvet.q2_3 = q2_3_otvet;



        int q3RadioButtonID = q3.getCheckedRadioButtonId();
        // Не выбрано
        if (q3RadioButtonID == -1) {
            printError("Отсутствует ответ на третий вопрос");
            return;
        }

        otvet.q3 = getText(q3, q3RadioButtonID);



        EditText q4 = findViewById(R.id.q4);
        String q4_otvet = q4.getText().toString().trim();
        if (q4_otvet.equals("")) {
            printError("Отсутствует ответ на четвертый вопрос");
            return;
        }
        otvet.q4 = q4_otvet;


        EditText q5 = findViewById(R.id.q5);
        String q5_otvet = q5.getText().toString().trim();
        if (q5_otvet.equals("")) {
            printError("Отсутствует ответ на пятый вопрос");
            return;
        }
        otvet.q5 = q5_otvet;


        EditText q6 = findViewById(R.id.q6);
        String q6_otvet = q6.getText().toString().trim();
        if (q6_otvet.equals("")) {
            printError("Отсутствует ответ на шестой вопрос");
            return;
        }
        otvet.q6 = q6_otvet;


        String q7_otvet = "";
        for (int i = 0; i < q7.size(); i++) {
            CheckBox box = q7.get(i);
            if (box.isChecked()) {
                if (!q7_otvet.equals("")) {
                    q7_otvet += ",";
                }

                q7_otvet += box.getText().toString();
            }
        }
        otvet.q7 = q7_otvet;


        Pattern emailRegular = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        EditText emailEdit = findViewById(R.id.email);
        if (!emailRegular.matcher(emailEdit.getText().toString().trim()).matches()) {
            printError("Не указан e-mail адрес");
            return;
        }


        otvet.email = emailEdit.getText().toString().trim();
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
