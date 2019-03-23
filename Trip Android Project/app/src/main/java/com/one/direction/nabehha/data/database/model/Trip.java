package com.one.direction.nabehha.data.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity
public class Trip implements Parcelable {

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Ignore
    private Integer userId;
    @Ignore
    ArrayList<Note> notes;
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
    @NonNull
    private String status;
    @NonNull
    private String type;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] tripImage;

    @Ignore
    public Trip() {
    }

//    @NonNull
//    public ArrayList<Note> getNotes() {
//        return notes;
//    }
//
//    public void setNotes(ArrayList<Note> notes) {
//        this.notes = notes;
//    }
//

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

    protected Trip(Parcel in) {
        if (in.readByte() == 0) {
            tripId = null;
        } else {
            tripId = in.readLong();
        }
        tripName = in.readString();
        startPointAddress = in.readString();
        endPointAddress = in.readString();
        startPointLatitude = in.readLong();
        startPointLongitude = in.readLong();
        endPointLatitude = in.readLong();
        endPointLongitude = in.readLong();
        date = in.readString();
        time = in.readString();
        tripImage = in.createByteArray();
        status = in.readString();
        type = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (tripId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(tripId);
        }
        dest.writeString(tripName);
        dest.writeString(startPointAddress);
        dest.writeString(endPointAddress);
        dest.writeLong(startPointLatitude);
        dest.writeLong(startPointLongitude);
        dest.writeLong(endPointLatitude);
        dest.writeLong(endPointLongitude);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeByteArray(tripImage);
        dest.writeString(status);
        dest.writeString(type);
    }
}
