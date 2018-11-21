package com.example.anketa.answers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;





/**
 * Ответы на анкету кафе
 * */
public class CafeAnketaAnswers extends Anketa {
    public String q1;
    public float q2_1;
    public float q2_2;
    public float q2_3;
    public float q2_4;
    public float q2_5;
    public float q3_1;
    public String q4;
    public String q5;
    public String q6;
    public String q7;



    @Override
    public List<String> getAnswerChoices(int queryNum) {
        switch (queryNum) {
            case 1:
                return Arrays.asList(
                        "Каждый день",
                        "Несколько раз в неделю",
                        "Один раз в неделю",
                        "Несколько раз в месяц",
                        "Один раз в месяц",
                        "Несколько раз в год",
                        "Никогда"
                );

            case 2:
                return Arrays.asList();

            case 3:
                return Arrays.asList(
                        "Да",
                        "Нет"
                );

            case 4:
                return Arrays.asList(
                        "Мужчина",
                        "Женщина"
                );

            case 5:
                return Arrays.asList(
                        "менее 20",
                        "21-30",
                        "31-40",
                        "41-50",
                        "51-60",
                        "60+"
                );
        }

        return Collections.emptyList();
    }


    @Override
    public List<Object> getAnswer(int queryNum) {
        switch (queryNum) {
            case 1:
                return Arrays.asList(q1);

            case 2:
                return Arrays.asList(q3_1);

            case 3:
                return Arrays.asList(q4);

            case 4:
                return Arrays.asList(q5);

            case 5:
                return Arrays.asList(q6);
        }

        return null;
    }

}
