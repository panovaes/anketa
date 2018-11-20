package com.example.anketa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.anketa.answers.WorkAnketaAnswers;
import com.example.anketa.db.DBHelper;

import java.util.ArrayList;
import java.util.regex.Pattern;





public class WorkAnketa extends Activity {

    private RadioGroup first_question;
    private RadioGroup second_question;
    private RadioGroup third_question;
    private ArrayList<CheckBox> fouth_question;
    private RadioGroup five_question;
    private RadioGroup six_question;
    private RadioGroup seven_question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_anketa);

        this.first_question = findViewById(R.id.first_question);
        this.second_question = findViewById(R.id.second_question);
        this.third_question = findViewById(R.id.third_question);

        fouth_question = new ArrayList<>();
        fouth_question.add((CheckBox) findViewById(R.id.fo_first));
        fouth_question.add((CheckBox) findViewById(R.id.fo_second));
        fouth_question.add((CheckBox) findViewById(R.id.fo_third));
        fouth_question.add((CheckBox) findViewById(R.id.fo_fourth));
        fouth_question.add((CheckBox) findViewById(R.id.fo_five));
        fouth_question.add((CheckBox) findViewById(R.id.fo_six));
        fouth_question.add((CheckBox) findViewById(R.id.fo_seven));
        fouth_question.add((CheckBox) findViewById(R.id.fo_eight));
        fouth_question.add((CheckBox) findViewById(R.id.fo_nine));

        this.five_question = findViewById(R.id.five_question);
        this.six_question = findViewById(R.id.six_question);

        this.six_question.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                EditText si_other = findViewById(R.id.si_other);
                switch (checkedId) {
                    case R.id.si_six:
                        si_other.setEnabled(true);
                        break;

                    default:
                        si_other.setEnabled(false);
                        break;
                }
            }
        });


        this.seven_question = findViewById(R.id.seven_question);
    }



    public void submit(View view) {
        WorkAnketaAnswers otvet = new WorkAnketaAnswers();

        int firstRadioButtonID = first_question.getCheckedRadioButtonId();
        // Не выбрано
        if (firstRadioButtonID == -1) {
            printError("Отсутствует ответ на первый вопрос");
            return;
        }

        otvet.first = getText(first_question, firstRadioButtonID);


        int secondRadioButtonId = second_question.getCheckedRadioButtonId();
        // Не выбрано
        if (secondRadioButtonId == -1) {
            printError("Отсутствует ответ на второй вопрос");
            return;
        }

        otvet.second = getText(second_question, secondRadioButtonId);


        int thirdRadioButtonId = third_question.getCheckedRadioButtonId();
        // Не выбрано
        if (thirdRadioButtonId == -1) {
            printError("Отсутствует ответ на третий вопрос");
            return;
        }

        otvet.third = getText(third_question, thirdRadioButtonId);


        String fouthOtvet = "";
        for (int i = 0; i < fouth_question.size(); i++) {
            CheckBox box = fouth_question.get(i);
            if (box.isChecked()) {
                if (!fouthOtvet.equals("")) {
                    fouthOtvet += ",";
                }

                fouthOtvet += box.getText().toString();
            }
        }
        otvet.fourth = fouthOtvet;


        int fiveRadioButtonId = five_question.getCheckedRadioButtonId();
        // Не выбрано
        if (fiveRadioButtonId == -1) {
            printError("Отсутствует ответ на пятый вопрос");
            return;
        }

        otvet.fifth = getText(five_question, fiveRadioButtonId);



        int sixRadioButtonId = six_question.getCheckedRadioButtonId();
        // Не выбрано
        if (sixRadioButtonId == -1) {
            printError("Отсутствует ответ на шестой вопрос");
            return;
        }

        String sixOtvet = "";
        if (sixRadioButtonId == R.id.si_six) {
            EditText si_other = findViewById(R.id.si_other);
            sixOtvet = si_other.getText().toString();
            if (sixOtvet.trim().equals("")) {
                printError("Отсутствует ответ на шестой вопрос");
                return;
            }
        } else {
            sixOtvet = getText(six_question, sixRadioButtonId);
        }

        otvet.sixth = sixOtvet;



        int sevenRadioButtonID = seven_question.getCheckedRadioButtonId();
        // Не выбрано
        if (sevenRadioButtonID == -1) {
            printError("Отсутствует ответ на седьмой вопрос");
            return;
        }

        otvet.seventh = getText(seven_question, sevenRadioButtonID);



        MultiAutoCompleteTextView eight_question = findViewById(R.id.eight_question);
        if (eight_question.getText().toString().trim().equals("")) {
            printError("Отсутствует ответ на восьмой вопрос");
            return;
        }
        otvet.eighth = eight_question.getText().toString().trim();


        Pattern emailRegular = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        EditText emailEdit = findViewById(R.id.email);
        if (!emailRegular.matcher(emailEdit.getText().toString().trim()).matches()) {
            printError("Не указан e-mail адрес");
            return;
        }


        otvet.email = emailEdit.getText().toString().trim();

        DBHelper.getInstance().insertWorkAnketa(otvet);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Сообщение")
                .setMessage("Анкета сохранена")
                .setCancelable(false)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WorkAnketa.this.finish();
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
