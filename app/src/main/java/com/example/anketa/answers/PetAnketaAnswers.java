package com.example.anketa.answers;

import com.github.mikephil.charting.data.BarEntry;

import java.util.List;





/**
 * Ответы на анкету питомцев
 * */
public class PetAnketaAnswers extends Anketa {
    public String q1;
    public String q2_1;
    public String q2_2;
    public String q2_3;
    public String q3;
    public String q4;
    public String q5;
    public String q6;
    public String q7;


    public List<String> getAnswerChoices(int queryNum) {
        return null;
    }


    public List<Object> getAnswer(int queryNum) {
        return null;
    }


    @Override public List<BarEntry> getAnswer(int queryNum, List<Anketa> data) {
        return null;
    }


    @Override public int getAnswerCount() {
        return 0;
    }


    @Override public String getQueryTitle(int queryNum) {
        return null;
    }
}
