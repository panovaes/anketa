package com.example.anketa.answers;
import android.database.Cursor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





public abstract class Anketa {
    public int id;
    public String email;


    public static Anketa of(String anketa_type, Cursor cursor) {

        Anketa result = null;


        switch (anketa_type) {
            case "CAFE":
                CafeAnketaAnswers cafe = new CafeAnketaAnswers();

                cafe.q1 = cursor.getString(cursor.getColumnIndex("q1"));
                cafe.q2_1 = cursor.getFloat(cursor.getColumnIndex("q2_1"));
                cafe.q2_2 = cursor.getFloat(cursor.getColumnIndex("q2_2"));
                cafe.q2_3 = cursor.getFloat(cursor.getColumnIndex("q2_3"));
                cafe.q2_4 = cursor.getFloat(cursor.getColumnIndex("q2_4"));
                cafe.q2_5 = cursor.getFloat(cursor.getColumnIndex("q2_5"));
                cafe.q3_1 = cursor.getFloat(cursor.getColumnIndex("q3_1"));
                cafe.q4 = cursor.getString(cursor.getColumnIndex("q4"));
                cafe.q5 = cursor.getString(cursor.getColumnIndex("q5"));
                cafe.q6 = cursor.getString(cursor.getColumnIndex("q6"));

                result = cafe;
                break;

            case "SITE":
                SiteAnketaAnswers site = new SiteAnketaAnswers();
                site.q1 = cursor.getString(cursor.getColumnIndex("q1"));
                site.q2 = cursor.getString(cursor.getColumnIndex("q2"));
                site.q3 = cursor.getString(cursor.getColumnIndex("q3"));
                site.q4 = cursor.getString(cursor.getColumnIndex("q4"));
                site.q5 = cursor.getString(cursor.getColumnIndex("q5"));
                site.q6 = cursor.getString(cursor.getColumnIndex("q6"));
                site.q7 = cursor.getString(cursor.getColumnIndex("q7"));
                site.q8 = cursor.getString(cursor.getColumnIndex("q8"));
                site.q9 = cursor.getString(cursor.getColumnIndex("q9"));
                site.q10 = cursor.getString(cursor.getColumnIndex("q10"));

                result = site;
                break;

            case "WORK":
                WorkAnketaAnswers work = new WorkAnketaAnswers();
                work.first = cursor.getString(cursor.getColumnIndex("q1"));
                work.second = cursor.getString(cursor.getColumnIndex("q2"));
                work.third = cursor.getString(cursor.getColumnIndex("q3"));
                work.fourth = cursor.getString(cursor.getColumnIndex("q4"));
                work.fifth = cursor.getString(cursor.getColumnIndex("q5"));
                work.sixth = cursor.getString(cursor.getColumnIndex("q6"));
                work.seventh = cursor.getString(cursor.getColumnIndex("q7"));
                work.eighth = cursor.getString(cursor.getColumnIndex("q8"));

                result = work;
                break;

            case "PET":
                PetAnketaAnswers pet = new PetAnketaAnswers();
                pet.q1 = cursor.getString(cursor.getColumnIndex("q1"));
                pet.q2_1 = cursor.getString(cursor.getColumnIndex("q2_1"));
                pet.q2_2 = cursor.getString(cursor.getColumnIndex("q2_2"));
                pet.q2_3 = cursor.getString(cursor.getColumnIndex("q2_3"));
                pet.q3 = cursor.getString(cursor.getColumnIndex("q3"));
                pet.q4 = cursor.getString(cursor.getColumnIndex("q4"));
                pet.q5 = cursor.getString(cursor.getColumnIndex("q5"));
                pet.q6 = cursor.getString(cursor.getColumnIndex("q6"));
                pet.q7 = cursor.getString(cursor.getColumnIndex("q7"));

                result = pet;
                break;
        }

        return result;
    }


    public abstract List<String> getAnswerChoices(int queryNum);

    public abstract List<Object> getAnswer(int queryNum);


    private static final Map<String, Anketa> EMPTY_ANKETS = new HashMap<>();

    static {
        EMPTY_ANKETS.put("CAFE", new CafeAnketaAnswers());
        EMPTY_ANKETS.put("SITE", new SiteAnketaAnswers());
        EMPTY_ANKETS.put("WORK", new WorkAnketaAnswers());
        EMPTY_ANKETS.put("PET", new PetAnketaAnswers());
    }

    public static List<String> getAnswers(String anketa_type, int queryNum) {
        return EMPTY_ANKETS.get(anketa_type).getAnswerChoices(queryNum);
    }

}
