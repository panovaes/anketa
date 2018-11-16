package com.example.anketa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

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

        View firstRadioButton = first_question.findViewById(firstRadioButtonID);
        int firstId = first_question.indexOfChild(firstRadioButton);
        otvet.first = ((RadioButton) first_question.getChildAt(firstId)).getText().toString();


        int secondRadioButtonId = second_question.getCheckedRadioButtonId();
        // Не выбрано
        if (secondRadioButtonId == -1) {
            printError("Отсутствует ответ на второй вопрос");
            return;
        }

        View secondRadioButton = second_question.findViewById(secondRadioButtonId);
        int secondId = second_question.indexOfChild(secondRadioButton);
        otvet.second = ((RadioButton) second_question.getChildAt(secondId)).getText().toString();


        int thirdRadioButtonId = third_question.getCheckedRadioButtonId();
        // Не выбрано
        if (thirdRadioButtonId == -1) {
            printError("Отсутствует ответ на третий вопрос");
            return;
        }

        View thirdRadioButton = third_question.findViewById(thirdRadioButtonId);
        int thirdId = third_question.indexOfChild(thirdRadioButton);
        otvet.third = ((RadioButton) third_question.getChildAt(thirdId)).getText().toString();



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

        View fiveRadioButton = five_question.findViewById(fiveRadioButtonId);
        int fiveId = five_question.indexOfChild(fiveRadioButton);
        otvet.fifth = ((RadioButton) five_question.getChildAt(fiveId)).getText().toString();



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
            View sixRadioButton = six_question.findViewById(sixRadioButtonId);
            int sixId = six_question.indexOfChild(sixRadioButton);
            sixOtvet = ((RadioButton) six_question.getChildAt(sixId)).getText().toString();
        }

        otvet.sixth = sixOtvet;



        int sevenRadioButtonID = seven_question.getCheckedRadioButtonId();
        // Не выбрано
        if (sevenRadioButtonID == -1) {
            printError("Отсутствует ответ на седьмой вопрос");
            return;
        }

        View sevenRadioButton = seven_question.findViewById(sevenRadioButtonID);
        int sevenId = seven_question.indexOfChild(sevenRadioButton);
        otvet.seventh = ((RadioButton) seven_question.getChildAt(sevenId)).getText().toString();

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


        otvet.eighth = emailEdit.getText().toString().trim();
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
