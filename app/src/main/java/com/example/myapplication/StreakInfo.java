package com.example.myapplication;

import java.util.Date;

public class StreakInfo {
    private String SubjectName;
    private String Content;
    private String TimeInMinutes;
    private Date CurrentTime;

    // an empty constructor is required when using Firebase Realtime Database.
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

    public Date getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(Date CurrentTime) {
        this.CurrentTime = CurrentTime;
    }
}
