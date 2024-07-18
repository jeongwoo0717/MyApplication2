package com.example.myapplication2;

public class MedicationTime {
    private int id;
    private String date;
    private String morningMedTime;
    private String afternoonMedTime;
    private String eveningMedTime;

    // Constructor
    public MedicationTime(int id, String date, String morningMedTime, String afternoonMedTime, String eveningMedTime) {
        this.id = id;
        this.date = date;
        this.morningMedTime = morningMedTime;
        this.afternoonMedTime = afternoonMedTime;
        this.eveningMedTime = eveningMedTime;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMorningMedTime() {
        return morningMedTime;
    }

    public void setMorningMedTime(String morningMedTime) {
        this.morningMedTime = morningMedTime;
    }

    public String getAfternoonMedTime() {
        return afternoonMedTime;
    }

    public void setAfternoonMedTime(String afternoonMedTime) {
        this.afternoonMedTime = afternoonMedTime;
    }

    public String getEveningMedTime() {
        return eveningMedTime;
    }

    public void setEveningMedTime(String eveningMedTime) {
        this.eveningMedTime = eveningMedTime;
    }
}
