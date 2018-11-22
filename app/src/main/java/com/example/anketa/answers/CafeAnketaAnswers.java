package com.example.anketa.answers;

import com.github.mikephil.charting.data.BarEntry;

import java.util.*;





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

            case 8:
                return Arrays.asList(
                        "Да",
                        "Нет"
                );

            case 9:
                return Arrays.asList(
                        "Мужчина",
                        "Женщина"
                );

            case 10:
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



    private static final Map<Integer, String> queryTitles = new HashMap<>();

    static {
        queryTitles.put(1,  "Вопрос 1");
        queryTitles.put(2,  "Вопрос 2_1");
        queryTitles.put(3,  "Вопрос 2_2");
        queryTitles.put(4,  "Вопрос 2_3");
        queryTitles.put(5,  "Вопрос 2_4");
        queryTitles.put(6,  "Вопрос 2_5");
        queryTitles.put(7,  "Вопрос 3");
        queryTitles.put(8,  "Вопрос 4");
        queryTitles.put(9,  "Вопрос 5");
        queryTitles.put(10, "Вопрос 6");
    }


    @Override
    public List<BarEntry> getAnswer(int queryNum, List<Anketa> data) {
        List<BarEntry> result = new ArrayList<>();
        switch (queryNum) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                Map<Float, Integer> query2Statistic = new HashMap<>();
                query2Statistic.put(0f, 0);
                query2Statistic.put(1f, 0);
                query2Statistic.put(2f, 0);
                query2Statistic.put(3f, 0);
                query2Statistic.put(4f, 0);
                query2Statistic.put(5f, 0);

                for (Anketa answerData : data) {
                    CafeAnketaAnswers query2 = (CafeAnketaAnswers) answerData;
                    float q2Key = -1f;
                    switch (queryNum) {
                        case 2:
                            q2Key = query2.q2_1;
                            break;
                        case 3:
                            q2Key = query2.q2_2;
                            break;
                        case 4:
                            q2Key = query2.q2_3;
                            break;
                        case 5:
                            q2Key = query2.q2_4;
                            break;
                        case 6:
                            q2Key = query2.q2_5;
                            break;
                        case 7:
                            q2Key = query2.q3_1;
                            break;
                    }

                    if (q2Key == -1f)
                        continue;

                    query2Statistic.put(q2Key, query2Statistic.get(q2Key) + 1);
                }

                int index = 0;
                for (float query2Key : query2Statistic.keySet()) {
                    result.add(new BarEntry(index, query2Statistic.get(query2Key), query2Key));
                    index++;
                }
                break;

            case 1:
            case 8:
            case 9:
            case 10:
                Map<String, Integer> queryStatistic = new HashMap<>();
                List<String> anwerVariant = getAnswerChoices(queryNum);
                for (String variant : anwerVariant) {
                    queryStatistic.put(variant, 0);
                }

                for (Anketa answerData : data) {
                    CafeAnketaAnswers query = (CafeAnketaAnswers) answerData;
                    String answer = null;

                    switch (queryNum) {
                        case 1:
                            answer = query.q1;
                            break;
                        case 8:
                            answer = query.q4;
                            break;
                        case 9:
                            answer = query.q5;
                            break;
                        case 10:
                            answer = query.q6;
                            break;
                    }

                    if (answer == null)
                        continue;

                    queryStatistic.put(answer, queryStatistic.get(answer) + 1);
                }

                int jitex = 0;
                for (String query1Key : queryStatistic.keySet()) {
                    result.add(new BarEntry(jitex, queryStatistic.get(query1Key), query1Key));
                    jitex++;
                }

                break;
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
