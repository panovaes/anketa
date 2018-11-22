package com.example.anketa.answers;

import com.example.anketa.SiteAnketa;
import com.github.mikephil.charting.data.BarEntry;

import java.util.*;





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


    private static final Map<Integer, String> queryTitles = new HashMap<>();

    static {
        queryTitles.put(1,  "Вопрос 1");
        queryTitles.put(2,  "Вопрос 2");
        queryTitles.put(3,  "Вопрос 3");
        queryTitles.put(4,  "Вопрос 4");
        queryTitles.put(5,  "Вопрос 5");
        queryTitles.put(6,  "Вопрос 6");
        queryTitles.put(7,  "Вопрос 7");
        queryTitles.put(8,  "Вопрос 8");
        queryTitles.put(9,  "Вопрос 9");
        queryTitles.put(10, "Вопрос 10");
    }

    private static final Map<Integer, List<String>> queryVarian = new HashMap<>();

    static {
        queryVarian.put(1, Arrays.asList("Очень просто", "Скорее просто", "Нормально", "Скорее сложно", "Очень сложно"));
        queryVarian.put(2, Arrays.asList("Очень просто", "Скорее просто", "Нормально", "Скорее сложно", "Очень сложно"));
        queryVarian.put(3, Arrays.asList("Полностью понятна", "Понятна", "Скорее понятна", "Скорее непонятна", "Непонятна"));
        queryVarian.put(4, Arrays.asList("Очень хороший", "Хороший", "Нормальный", "Плохой", "Мне он совсем не нравится"));
        queryVarian.put(5, Arrays.asList("Актуальное", "Скорее актуальное", "Не слишком актуальное", "Совсем не актуальное"));
        queryVarian.put(6, Arrays.asList("Очень просто", "Скорее просто", "Нормально", "Скорее сложно", "Очень сложно"));
        queryVarian.put(7, Arrays.asList("Очень просто", "Скорее просто", "Нормально", "Скорее сложно", "Очень сложно"));
        queryVarian.put(8, Arrays.asList("Безусловно", "Очень", "Нормально", "Я не считаю его слишком надежным", "Я совсем не доверяю ему"));
        queryVarian.put(9, Arrays.asList("Очень доволен/льна", "Доволен/льна", "Скорее доволен/льна", "В среднем доволен/льна", "Скорее недоволен/льна", "Недоволен/льна", "Очень недоволен/льна"));
        queryVarian.put(10, Arrays.asList("Несомненно да", "Вероятно да", "Я не знаю", "Вероятно нет", "Несомненно нет"));
    }

    public List<String> getAnswerChoices(int queryNum) {
        return queryVarian.get(queryNum);
    }


    private void increment(Map<String, Integer> data, String key) {
        if (!data.containsKey(key))
            return;

        data.put(key, data.get(key) + 1);
    }


    @Override
    public List<BarEntry> getAnswer(int queryNum, List<Anketa> data) {
        List<BarEntry> result = new ArrayList<>();

        Map<String, Integer> statistic = new HashMap<>();
        for (String answer : getAnswerChoices(queryNum)) {
            statistic.put(answer, 0);
        }


        for (Anketa anketa : data) {
            SiteAnketaAnswers answer = (SiteAnketaAnswers) anketa;
            switch (queryNum) {
                case 1:
                    increment(statistic, answer.q1);
                    break;
                case 2:
                    increment(statistic, answer.q2);
                    break;
                case 3:
                    increment(statistic, answer.q3);
                    break;
                case 4:
                    increment(statistic, answer.q4);
                    break;
                case 5:
                    increment(statistic, answer.q5);
                    break;
                case 6:
                    increment(statistic, answer.q6);
                    break;
                case 7:
                    increment(statistic, answer.q7);
                    break;
                case 8:
                    increment(statistic, answer.q8);
                    break;
                case 9:
                    increment(statistic, answer.q9);
                    break;
                case 10:
                    increment(statistic, answer.q10);
                    break;
            }
        }


        for (int i = 0; i < getAnswerChoices(queryNum).size(); i++) {
            String key = getAnswerChoices(queryNum).get(i);
            result.add(new BarEntry(i, statistic.get(key), key));
        }

        return result;
    }


    @Override
    public int getAnswerCount() {
        return queryTitles.size();
    }


    @Override
    public String getQueryTitle(int queryNum) {
        return queryTitles.get(queryNum);
    }
}
