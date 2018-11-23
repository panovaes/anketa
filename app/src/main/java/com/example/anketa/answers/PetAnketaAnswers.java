package com.example.anketa.answers;

import com.github.mikephil.charting.data.BarEntry;

import java.util.*;





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


    private static final Map<Integer, String> queryTitles = new HashMap<>();

    static {
        queryTitles.put(1,  "Вопрос 1");
        queryTitles.put(2,  "Вопрос 3");
    }


    private static final Map<Integer, List<String>> queryVariant = new HashMap<>();

    static {
        queryVariant.put(1, Arrays.asList("Нет"));
        queryVariant.put(2, Arrays.asList("Собака", "Кошка", "Попугай", "Пони", "Хомяк"));
    }


    public List<String> getAnswerChoices(int queryNum) {
        return queryVariant.get(queryNum);
    }


    @Override
    public List<BarEntry> getAnswer(int queryNum, List<Anketa> data) {
        List<BarEntry> result = new ArrayList<>();

        switch (queryNum) {
            case 1:
                int noCount = 0;
                int otherCount = 0;

                for (Anketa anketa : data) {
                    PetAnketaAnswers answer = (PetAnketaAnswers) anketa;
                    if (answer.q1.equals("Нет"))
                        noCount++;
                    else otherCount++;
                }

                result.add(new BarEntry(0, noCount, "Нет"));
                result.add(new BarEntry(1, otherCount, "Да"));
                break;

            case 2:
                Map<String, Integer> statistic = new HashMap<>();
                for (String key : getAnswerChoices(queryNum)) {
                    statistic.put(key, 0);
                }

                for (Anketa anketa : data) {
                    PetAnketaAnswers answer = (PetAnketaAnswers) anketa;
                    increment(statistic, answer.q3);
                }

                for (int i = 0; i < getAnswerChoices(queryNum).size(); i++) {
                    String key = getAnswerChoices(queryNum).get(i);
                    result.add(new BarEntry(i, statistic.get(key), key));
                }
                break;
        }

        return result;
    }


    @Override public int getAnswerCount() {
        return queryTitles.size();
    }


    @Override
    public String getQueryTitle(int queryNum) {
        return queryTitles.get(queryNum);
    }
}
