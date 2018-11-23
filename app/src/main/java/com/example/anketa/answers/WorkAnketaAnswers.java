package com.example.anketa.answers;

import com.github.mikephil.charting.data.BarEntry;

import java.util.*;





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


    private static final Map<Integer, String> queryTitles = new HashMap<>();

    static {
        queryTitles.put(1,  "Вопрос 1");
        queryTitles.put(2,  "Вопрос 2");
        queryTitles.put(3,  "Вопрос 3");
        queryTitles.put(4,  "Вопрос 5");
        queryTitles.put(5,  "Вопрос 6");
        queryTitles.put(6,  "Вопрос 7");
    }


    private static final Map<Integer, List<String>> queryVariant = new HashMap<>();

    static {
        queryVariant.put(1, Arrays.asList("Менее 18-и", "18-24", "25-44", "45-64", "64 и выше"));
        queryVariant.put(2, Arrays.asList("Работающий/ая на полную ставку", "Работающий/ая неполный рабочий день", "Безработный/ая", "Частный предпринематель", "Студент", "Пенсионер"));
        queryVariant.put(3, Arrays.asList("Начальное образование", "Неполное среднее образование", "Среднеспециальное образование", "Высшее профессиональное образование", "Отсутствует"));
        queryVariant.put(4, Arrays.asList("Государственный сектор", "Частный сектор", "Сам/а для себя"));
        queryVariant.put(5, Arrays.asList("На улице, на природе", "Предпочитаю тихий офис", "В шумном месте, где много интересных людей", "В лаборатории", "В классе"));
        queryVariant.put(6, Arrays.asList("Людьми", "Вещами", "Информацией", "Деньгами"));
    }


    public List<String> getAnswerChoices(int queryNum) {
        return queryVariant.get(queryNum);
    }


    @Override
    public List<BarEntry> getAnswer(int queryNum, List<Anketa> data) {
        List<BarEntry> result = new ArrayList<>();


        Map<String, Integer> statistic = new HashMap<>();
        for (String answer : getAnswerChoices(queryNum)) {
            statistic.put(answer, 0);
        }

        int other = 0;
        for (Anketa anketa : data) {
            WorkAnketaAnswers answer = (WorkAnketaAnswers) anketa;
            switch (queryNum) {
                case 1:
                    increment(statistic, answer.first);
                    break;
                case 2:
                    increment(statistic, answer.second);
                    break;
                case 3:
                    increment(statistic, answer.third);
                    break;
                case 4:
                    increment(statistic, answer.fifth);
                    break;
                case 6:
                    increment(statistic, answer.seventh);
                    break;

                case 5:
                    if (statistic.containsKey(answer.sixth)) {
                        increment(statistic, answer.sixth);
                    } else {
                        other++;
                    }
                    break;
            }
        }

        for (int i = 0; i < getAnswerChoices(queryNum).size(); i++) {
            String key = getAnswerChoices(queryNum).get(i);
            result.add(new BarEntry(i, statistic.get(key), key));
        }

        if (queryNum == 5) {
            result.add(new BarEntry(getAnswerChoices(queryNum).size(), other, "В другом месте"));
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
