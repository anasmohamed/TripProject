package com.one.direction.nabehha.data.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Trip {
    Long userId;
    @PrimaryKey(autoGenerate = true)
    private Long tripId;
    @NonNull
    private String tripName;

    @NonNull
    private String startPoint;

    @NonNull
    private String endPoint;

    @NonNull
    private String date;

    @NonNull
    private String time;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] tripImage;


//    ArrayList<Note> notes;

    private String status;

    @NonNull
    private String type;

    public Trip() {
    }

    public Trip(@NonNull String tripName, @NonNull String startPoint, @NonNull String endPoint, @NonNull String date, @NonNull String time, @NonNull String type, String tripImage, Long userId, @NonNull String status) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.date = date;
//        this.notes = notes;
        this.userId = userId;
        this.time = time;
        this.status = status;
        this.type = type;
    }

//    @NonNull
//    public ArrayList<Note> getNotes() {
//        return notes;
//    }

//    public void setNotes(ArrayList<Note> notes) {
//        this.notes = notes;
//    }

    @NonNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Long userId) {
        this.userId = userId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    public byte[] getTripImage() {
        return tripImage;
    }

    public void setTripImage(@NonNull byte[] tripImage) {
        this.tripImage = tripImage;
    }
}
