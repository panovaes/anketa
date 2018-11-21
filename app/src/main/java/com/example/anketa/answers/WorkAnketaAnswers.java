package com.example.anketa.answers;

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


    @Override
    public List<String> getAnswerChoices(int queryNum) {
        return null;
    }


    @Override public List<Object> getAnswer(int queryNum) {
        return null;
    }
}
