package com.example.anketa.answers;

import com.github.mikephil.charting.data.BarEntry;

import java.util.List;





/**
 * Ответы на анкету поиска работы
 * */
public class WorkAnketaAnswers extends Anketa{
    public String first;
    public String second;
    public String third;
    public String fourth;
    public String fifth;
    public String sixth;
    public String seventh;
    public String eighth;


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
