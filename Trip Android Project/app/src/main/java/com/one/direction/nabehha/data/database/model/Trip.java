package com.one.direction.nabehha.data.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@IgnoreExtraProperties
@Entity(primaryKeys = {"tripId"})
public class Trip implements Parcelable {


    protected Trip(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        notes = in.createStringArrayList();
        tripId = in.readString();
        tripName = in.readString();
        tripImageUrl = in.readString();
        startPointAddress = in.readString();
        endPointAddress = in.readString();
        startPointLatitude = in.readDouble();
        startPointLongitude = in.readDouble();
        endPointLatitude = in.readDouble();
        endPointLongitude = in.readDouble();
        date = in.readString();
        time = in.readString();
        status = in.readString();
        type = in.readString();
        tripImagebyte = in.createByteArray();
    }

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
    ArrayList<String> notes;

    @NonNull
    private String tripId;
    @NonNull
    @SerializedName("tripName")
    private String tripName;
    @NonNull
    @Ignore
    @SerializedName("tripImage")
    private String tripImageUrl;
    @NonNull
    @SerializedName("startPoint")
    private String startPointAddress;
    @NonNull
    @SerializedName("endPoint")
    private String endPointAddress;
    @NonNull
    private double startPointLatitude;
    @NonNull
    private double startPointLongitude;
    @NonNull
    private double endPointLatitude;
    @NonNull
    private double endPointLongitude;
    @NonNull
    private String date;
    @NonNull
    private String time;
    @NonNull
    private String status;
    @NonNull
    private String type;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] tripImagebyte;

    @Ignore
    public Trip() {
    }

    @Ignore
    public Trip(@NonNull String tripId) {
        this.tripId = tripId;
    }

    @Ignore
    public Trip(String tripId, @NonNull String tripName, @NonNull String startPointAddress, @NonNull String endPointAddress,
                double startPointLatitude, double startPointLongitude, double endPointLatitude, double endPointLongitude,
                @NonNull String date, @NonNull String time, String status, @NonNull String type,ArrayList<String> notes,String tripImageUrl) {
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
        this.notes=notes;
        this.tripImageUrl=tripImageUrl;
    }

    public Trip(String tripId, @NonNull String tripName, @NonNull String startPointAddress, @NonNull String endPointAddress,
                double startPointLatitude, double startPointLongitude, double endPointLatitude, double endPointLongitude,
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



    @NonNull
    public String getTripImageUrl() {
        return tripImageUrl;
    }

    public void setTripImageUrl(@NonNull String tripImageUrl) {
        this.tripImageUrl = tripImageUrl;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
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

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
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

    public double getStartPointLatitude() {
        return startPointLatitude;
    }

    public void setStartPointLatitude(double startPointLatitude) {
        this.startPointLatitude = startPointLatitude;
    }

    public double getStartPointLongitude() {
        return startPointLongitude;
    }

    public void setStartPointLongitude(double startPointLongitude) {
        this.startPointLongitude = startPointLongitude;
    }

    public double getEndPointLatitude() {
        return endPointLatitude;
    }

    public void setEndPointLatitude(double endPointLatitude) {
        this.endPointLatitude = endPointLatitude;
    }

    public double getEndPointLongitude() {
        return endPointLongitude;
    }

    public void setEndPointLongitude(double endPointLongitude) {
        this.endPointLongitude = endPointLongitude;
    }

    @NonNull
    public byte[] getTripImagebyte() {
        return tripImagebyte;
    }

    public void setTripImagebyte(@NonNull byte[] tripImagebyte) {
        this.tripImagebyte = tripImagebyte;
    }



    @Override
    public boolean equals(Object obj) {
        return this.tripId.equals(((Trip) obj).tripId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeStringList(notes);
        dest.writeString(tripId);
        dest.writeString(tripName);
        dest.writeString(tripImageUrl);
        dest.writeString(startPointAddress);
        dest.writeString(endPointAddress);
        dest.writeDouble(startPointLatitude);
        dest.writeDouble(startPointLongitude);
        dest.writeDouble(endPointLatitude);
        dest.writeDouble(endPointLongitude);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(status);
        dest.writeString(type);
        dest.writeByteArray(tripImagebyte);
    }
}
