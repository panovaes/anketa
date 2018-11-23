package com.example.anketa.report;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import com.example.anketa.db.DBHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;





public class AnketaReport {
    private static final SQLiteDatabase database = DBHelper.getInstance().getReadableDatabase();
    private static String htmlReportTemplate = "";
    private static String htmlThanksTemplate = "";


    public static void init(Activity activity) {
        htmlReportTemplate = readFile(activity, "report.html");
        htmlThanksTemplate = readFile(activity, "thanks.html");
    }


    private static String readFile(Activity activity, String fileName) {
        String result = "";

        try (InputStream stream = activity.getBaseContext().getAssets().open(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("utf-8")))) {

            String line = null;
            while ((line = reader.readLine()) != null) {
                result = result + '\n' + line;
            }

        } catch (IOException e) {
            Log.e("AnketaReport", "init: ", e);
        }

        return result;
    }


    public static String createReport() {
        Cursor workCusror = database.rawQuery("select count(*) from work", null);
        Cursor cafeCusror = database.rawQuery("select count(*) from cafe", null);
        Cursor siteCusror = database.rawQuery("select count(*) from site", null);
        Cursor petCusror = database.rawQuery("select count(*) from pet", null);

        workCusror.moveToFirst();
        cafeCusror.moveToFirst();
        siteCusror.moveToFirst();
        petCusror.moveToFirst();

        int workCount = workCusror.getInt(0);
        int cafeCount = cafeCusror.getInt(0);
        int siteCount = siteCusror.getInt(0);
        int petCount = petCusror.getInt(0);

        workCusror.close();
        cafeCusror.close();
        siteCusror.close();
        petCusror.close();


        String result = htmlReportTemplate;
        result = result.replace("${workCount}", String.valueOf(workCount));
        result = result.replace("${cafeCount}", String.valueOf(cafeCount));
        result = result.replace("${siteCount}", String.valueOf(siteCount));
        result = result.replace("${petCount}", String.valueOf(petCount));

        return result;
    }


    public static String createThank(String anketaTitle) {
        return htmlThanksTemplate.replace("${anketa_title}", anketaTitle);
    }



    // TODO: Авторизация в гугл почте
    private static final String username = "";
    private static final String password = "";
    private static final Properties props = new Properties();

    static {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }


    private static Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    public static void sendTo(String email, String subject, String html) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setContent(html, "text/html; charset=utf-8");
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);

            new Executing().execute(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    private static class Executing extends AsyncTask<Message, Void, Void> {

        @Override
        protected Void doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                Log.e("SEND EMAIL ERROR", "ERROR", e);
            }
            return null;
        }
    }

}
