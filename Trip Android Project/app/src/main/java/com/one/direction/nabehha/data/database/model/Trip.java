package com.one.direction.nabehha.data.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Trip {
    @PrimaryKey
    private Long tripId;
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
    private String time;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] tripImage;


//    ArrayList<Note> notes;

    private String status;

    @NonNull
    private String type;

    @Ignore
    public Trip() {
    }

    public Trip(Long tripId, @NonNull String tripName, @NonNull String startPointAddress, @NonNull String endPointAddress,
                long startPointLatitude, long startPointLongitude, long endPointLatitude, long endPointLongitude,
                @NonNull String date, @NonNull String time, String status, @NonNull String type) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startPointAddress = startPointAddress;
        this.endPointAddress = endPointAddress;
        this.startPointLatitude = startPointLatitude;
        this.startPointLongitude = startPointLongitude;
        this.endPointLatitude = endPointLatitude;
        this.endPointLongitude = endPointLongitude;
        this.date = date;
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
