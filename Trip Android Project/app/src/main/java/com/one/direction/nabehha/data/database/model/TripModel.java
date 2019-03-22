package com.one.direction.nabehha.data.database.model;

public class TripModel {

    private String date;
    private String endPoint;
    private Integer id;
    private Object notes;
    private String startPoint;
    private String status;
    private String time;
    private String tripImage;
    private String tripName;
    private String type;
    private Integer userId;

    public TripModel() {
    }

    public TripModel(String date, String endPoint, Integer id, Object notes, String startPoint, String status, String time, String tripImage, String tripName, String type, Integer userId) {
        super();
        this.date = date;
        this.endPoint = endPoint;
        this.id = id;
        this.notes = notes;
        this.startPoint = startPoint;
        this.status = status;
        this.time = time;
        this.tripImage = tripImage;
        this.tripName = tripName;
        this.type = type;
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTripImage() {
        return tripImage;
    }

    public void setTripImage(String tripImage) {
        this.tripImage = tripImage;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
