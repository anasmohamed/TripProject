package com.one.direction.nabehha.data.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Trip {
    @NonNull

    private String tripName;
    @NonNull

    private String startPoint;
    @NonNull
    private String endPoint;
    private String notes;
    @NonNull
    private String date;
    @NonNull

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] tripImage;
    @NonNull

    private String time;
    @NonNull

    private String status;
    @NonNull

    private String type;
    @PrimaryKey(autoGenerate = true)
    private Long id;

    public Trip() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
