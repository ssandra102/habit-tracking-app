package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userStreakInfo.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_NAME = "streakInfo";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SUBJECT_NAME = "subjectName";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_TIME_IN_MINUTES = "timeInMinutes";
    private static final String COLUMN_CURRENT_TIME = "currentTime";

    // Create table SQL query
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SUBJECT_NAME + " TEXT, " +
                    COLUMN_CONTENT + " TEXT, " +
                    COLUMN_TIME_IN_MINUTES + " TEXT, " +
                    COLUMN_CURRENT_TIME + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add data to the database
    public void addStreakInfo(String subjectName, String content, String timeInMinutes, String currentTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT_NAME, subjectName);
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_TIME_IN_MINUTES, timeInMinutes);
        values.put(COLUMN_CURRENT_TIME, currentTime);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<StreakInfo> getAllStreakInfo() {
        List<StreakInfo> streakInfoList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                StreakInfo streakInfo = new StreakInfo();
                streakInfo.setSubjectName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT_NAME)));
                streakInfo.setContent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT)));
                streakInfo.setTimeInMinutes(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_IN_MINUTES)));
                streakInfo.setCurrentTime(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CURRENT_TIME)));
//                streakInfo.setCurrentTime(null);
                streakInfoList.add(streakInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return streakInfoList;
    }

    public List<StreakInfo> getTodayStreakInfo() {
        List<StreakInfo> streakInfoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String todayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String dateString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CURRENT_TIME));
                try {
                    Date date = sdf.parse(dateString);
                    String recordDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date);
                    if (recordDate.equals(todayDate)) {
                        StreakInfo streakInfo = new StreakInfo();
//                        streakInfo.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                        streakInfo.setSubjectName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECT_NAME)));
                        streakInfo.setContent(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT)));
                        streakInfo.setTimeInMinutes(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_IN_MINUTES)));
                        streakInfo.setCurrentTime(String.valueOf(date));
                        streakInfoList.add(streakInfo);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return streakInfoList;
    }

}

