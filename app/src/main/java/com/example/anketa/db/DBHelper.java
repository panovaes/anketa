package com.example.anketa.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.anketa.answers.CafeAnketaAnswers;
import com.example.anketa.answers.PetAnketaAnswers;
import com.example.anketa.answers.SiteAnketaAnswers;
import com.example.anketa.answers.WorkAnketaAnswers;





public class DBHelper extends SQLiteOpenHelper {
    private static final String cafeDb;
    private static final String petDb;
    private static final String siteDb;
    private static final String workDb;
    private static DBHelper instance;

    static {
        StringBuilder cafeDbQuery = new StringBuilder();
        cafeDbQuery
                .append("create table if not exists cafe (")
                .append("id INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("q1 TEXT NOT NULL,")
                .append("q2_1 REAL NOT NULL,")
                .append("q2_2 REAL NOT NULL,")
                .append("q2_3 REAL NOT NULL,")
                .append("q2_4 REAL NOT NULL,")
                .append("q2_5 REAL NOT NULL,")
                .append("q3_1 REAL NOT NULL,")
                .append("q4 TEXT NOT NULL,")
                .append("q5 TEXT NOT NULL,")
                .append("q6 TEXT NOT NULL,")
                .append("q7 TEXT NOT NULL,")
                .append("email TEXT NOT NULL")
                .append(");")
        ;

        cafeDb = cafeDbQuery.toString();


        StringBuilder petDbQuery = new StringBuilder();
        petDbQuery
                .append("create table if not exists pet (")
                .append("id INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("q1 TEXT NOT NULL,")
                .append("q2_1 TEXT NOT NULL,")
                .append("q2_2 TEXT NOT NULL,")
                .append("q2_3 TEXT NOT NULL,")
                .append("q3 TEXT NOT NULL,")
                .append("q4 TEXT NOT NULL,")
                .append("q5 TEXT NOT NULL,")
                .append("q6 TEXT NOT NULL,")
                .append("q7 TEXT NOT NULL,")
                .append("email TEXT NOT NULL")
                .append(");")
        ;

        petDb = petDbQuery.toString();


        StringBuilder siteDbQuery = new StringBuilder();
        siteDbQuery
                .append("create table if not exists site (")
                .append("id INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("q1 TEXT NOT NULL,")
                .append("q2 TEXT NOT NULL,")
                .append("q3 TEXT NOT NULL,")
                .append("q4 TEXT NOT NULL,")
                .append("q5 TEXT NOT NULL,")
                .append("q6 TEXT NOT NULL,")
                .append("q7 TEXT NOT NULL,")
                .append("q8 TEXT NOT NULL,")
                .append("q9 TEXT NOT NULL,")
                .append("q10 TEXT NOT NULL,")
                .append("email TEXT NOT NULL")
                .append(");")
        ;

        siteDb = siteDbQuery.toString();


        StringBuilder workDbQuery = new StringBuilder();
        workDbQuery
                .append("create table if not exists work (")
                .append("id INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("q1 TEXT NOT NULL,")
                .append("q2 TEXT NOT NULL,")
                .append("q3 TEXT NOT NULL,")
                .append("q4 TEXT NOT NULL,")
                .append("q5 TEXT NOT NULL,")
                .append("q6 TEXT NOT NULL,")
                .append("q7 TEXT NOT NULL,")
                .append("q8 TEXT NOT NULL,")
                .append("email TEXT NOT NULL")
                .append(");")
        ;

        workDb = workDbQuery.toString();
    }


    private DBHelper(Context context) {
        super(context, "ankets", null, 1);
        this.getWritableDatabase().execSQL(cafeDb);
        this.getWritableDatabase().execSQL(petDb);
        this.getWritableDatabase().execSQL(siteDb);
        this.getWritableDatabase().execSQL(workDb);
    }


    public static DBHelper create(Context context) {
        instance = new DBHelper(context);
        return instance;
    }


    public static DBHelper getInstance() {
        return instance;
    }


    public void insertAnketa(CafeAnketaAnswers answers) {
        ContentValues data = new ContentValues();
        data.put("q1", answers.q1);
        data.put("q2_1", answers.q2_1);
        data.put("q2_2", answers.q2_2);
        data.put("q2_3", answers.q2_3);
        data.put("q2_4", answers.q2_4);
        data.put("q2_5", answers.q2_5);
        data.put("q3_1", answers.q3_1);
        data.put("q4", answers.q4);
        data.put("q5", answers.q5);
        data.put("q6", answers.q6);
        data.put("q7", answers.q7);
        data.put("email", answers.email);

        getWritableDatabase().insert("cafe", null, data);
    }


    public void insertAnketa(PetAnketaAnswers answers) {
        ContentValues data = new ContentValues();
        data.put("q1", answers.q1);
        data.put("q2_1", answers.q2_1);
        data.put("q2_2", answers.q2_2);
        data.put("q2_3", answers.q2_3);
        data.put("q3", answers.q3);
        data.put("q4", answers.q4);
        data.put("q5", answers.q5);
        data.put("q6", answers.q6);
        data.put("q7", answers.q7);
        data.put("email", answers.email);

        getWritableDatabase().insert("pet", null, data);
    }


    public void insertAnketa(SiteAnketaAnswers answers) {
        ContentValues data = new ContentValues();
        data.put("q1", answers.q1);
        data.put("q2", answers.q2);
        data.put("q3", answers.q3);
        data.put("q4", answers.q4);
        data.put("q5", answers.q5);
        data.put("q6", answers.q6);
        data.put("q7", answers.q7);
        data.put("q8", answers.q8);
        data.put("q9", answers.q9);
        data.put("q10", answers.q10);
        data.put("email", answers.email);

        getWritableDatabase().insert("site", null, data);
    }


    public void insertAnketa(WorkAnketaAnswers answers) {
        ContentValues data = new ContentValues();
        data.put("q1", answers.first);
        data.put("q2", answers.second);
        data.put("q3", answers.third);
        data.put("q4", answers.fourth);
        data.put("q5", answers.fifth);
        data.put("q6", answers.sixth);
        data.put("q7", answers.seventh);
        data.put("q8", answers.eighth);
        data.put("email", answers.email);

        getWritableDatabase().insert("work", null, data);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
