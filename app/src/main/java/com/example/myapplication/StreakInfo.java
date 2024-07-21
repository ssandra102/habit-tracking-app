package com.example.myapplication;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StreakInfo {
    private String SubjectName;
    private String Content;
    private String TimeInMinutes;
    private String CurrentTime;

    public StreakInfo() {
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getTimeInMinutes() {
        return TimeInMinutes;
    }

    public void setTimeInMinutes(String TimeInMinutes) {
        this.TimeInMinutes = TimeInMinutes;
    }

    public String getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(String CurrentTime) {
        this.CurrentTime = CurrentTime;
    }

}
