package com.example.anketa.answers;

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


    @Override
    public List<String> getAnswerChoices(int queryNum) {
        return null;
    }


    @Override public List<Object> getAnswer(int queryNum) {
        return null;
    }
}
