package com.example.anketa;

import android.app.Application;
import android.content.Context;





public class ApplicationContext extends Application {

    private static Context context;


    public static Context getAppContext() {
        return context;
    }


    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
