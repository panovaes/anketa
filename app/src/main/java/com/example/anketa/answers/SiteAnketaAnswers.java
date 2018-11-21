package com.example.anketa.answers;

import java.util.List;





/**
 * Ответы на анкеты сайта
 * */
public class SiteAnketaAnswers extends Anketa {
    public String q1;
    public String q2;
    public String q3;
    public String q4;
    public String q5;
    public String q6;
    public String q7;
    public String q8;
    public String q9;
    public String q10;


    @Override
    public List<String> getAnswerChoices(int queryNum) {
        return null;
    }


    @Override public List<Object> getAnswer(int queryNum) {
        return null;
    }
}
