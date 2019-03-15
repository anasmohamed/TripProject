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

    @NonNull
    private String date;
    @NonNull

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] tripImage;
    @NonNull

    private String time;
    @NonNull
    int userId;

    @NonNull
    public int getUserId() {
        return userId;
    }

    public void setUserId(@NonNull int userId) {
        this.userId = userId;
    }

    private String status;
    @NonNull

    private String type;
    @PrimaryKey(autoGenerate = true)
    private Long id;

    public Trip() {
    }

    public Trip(@NonNull String tripName, @NonNull String startPoint, @NonNull String endPoint, @NonNull String date, @NonNull String time, @NonNull String type, @NonNull String tripImage, int userId, @NonNull String status) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.date = date;
        this.tripImage = tripImage;
        this.userId = userId;
        this.time = time;
        this.status = status;
        this.type = type;
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
