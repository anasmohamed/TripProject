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
    private String startPointAddress;
    @NonNull
    private String endPointAddress;

    @NonNull
    private long startPointLatitude;
    @NonNull
    private long startPointLongitude;
    @NonNull
    private long endPointLatitude;
    @NonNull
    private long endPointLongitude;

    @NonNull
    private String date;
    @NonNull

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] tripImage;
    @NonNull

    private String time;
    @NonNull
    Long userId;

    @NonNull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@NonNull Long userId) {
        this.userId = userId;
    }

    private String status;
    @NonNull

    private String type;
    @PrimaryKey(autoGenerate = true)
    private Long id;

    public Trip() {
    }

//    public Trip(@NonNull String tripName, @NonNull String startPoint, @NonNull String endPoint, @NonNull String date, @NonNull String time, @NonNull String type, @NonNull String tripImage, Long userId, @NonNull String status) {
//        this.tripName = tripName;
//        this.startPoint = startPoint;
//        this.endPoint = endPoint;
//        this.date = date;
////        this.tripImage = tripImage;
//        this.userId = userId;
//        this.time = time;
//        this.status = status;
//        this.type = type;
//    }


    public Trip(@NonNull String tripName, @NonNull String startPointAddress, @NonNull String endPointAddress, long startPointLatitude, long startPointLongitude, long endPointLatitude, long endPointLongitude, @NonNull String date, @NonNull String time, @NonNull Long userId, String status, @NonNull String type, Long id) {
        this.tripName = tripName;
        this.startPointAddress = startPointAddress;
        this.endPointAddress = endPointAddress;
        this.startPointLatitude = startPointLatitude;
        this.startPointLongitude = startPointLongitude;
        this.endPointLatitude = endPointLatitude;
        this.endPointLongitude = endPointLongitude;
        this.date = date;
        this.time = time;
        this.userId = userId;
        this.status = status;
        this.type = type;
        this.id = id;
    }

    public Trip(@NonNull String tripName, @NonNull String startPointAddress, @NonNull String endPointAddress, long startPointLatitude, long startPointLongitude, long endPointLatitude, long endPointLongitude, @NonNull String date, @NonNull String time, @NonNull Long userId, String status, @NonNull String type) {
        this.tripName = tripName;
        this.startPointAddress = startPointAddress;
        this.endPointAddress = endPointAddress;
        this.startPointLatitude = startPointLatitude;
        this.startPointLongitude = startPointLongitude;
        this.endPointLatitude = endPointLatitude;
        this.endPointLongitude = endPointLongitude;
        this.date = date;
        this.time = time;
        this.userId = userId;
        this.status = status;
        this.type = type;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    @NonNull
    public String getStartPointAddress() {
        return startPointAddress;
    }

    public void setStartPointAddress(@NonNull String startPointAddress) {
        this.startPointAddress = startPointAddress;
    }

    @NonNull
    public String getEndPointAddress() {
        return endPointAddress;
    }

    public void setEndPointAddress(@NonNull String endPointAddress) {
        this.endPointAddress = endPointAddress;
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

    public long getStartPointLatitude() {
        return startPointLatitude;
    }

    public void setStartPointLatitude(long startPointLatitude) {
        this.startPointLatitude = startPointLatitude;
    }

    public long getStartPointLongitude() {
        return startPointLongitude;
    }

    public void setStartPointLongitude(long startPointLongitude) {
        this.startPointLongitude = startPointLongitude;
    }

    public long getEndPointLatitude() {
        return endPointLatitude;
    }

    public void setEndPointLatitude(long endPointLatitude) {
        this.endPointLatitude = endPointLatitude;
    }

    public long getEndPointLongitude() {
        return endPointLongitude;
    }

    public void setEndPointLongitude(long endPointLongitude) {
        this.endPointLongitude = endPointLongitude;
    }

    @NonNull
    public byte[] getTripImage() {
        return tripImage;
    }

    public void setTripImage(@NonNull byte[] tripImage) {
        this.tripImage = tripImage;
    }
}
