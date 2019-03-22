package com.one.direction.nabehha.data.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//@Entity(indices = {@Index("tripId")},foreignKeys = @ForeignKey(entity = Trip.class,
//        parentColumns = "tripId",
//        childColumns = "tripId"))
//public class Note {
//    public Long getTripId() {
//        return tripId;
//    }
//
//    public void setTripId(Long tripId) {
//        this.tripId = tripId;
//    }
//
//    public Long getNoteId() {
//        return noteId;
//    }
//
//    public void setNoteId(Long noteId) {
//        this.noteId = noteId;
//    }
//
//    @ColumnInfo(name = "tripId")
//    private Long tripId;
//
//    @PrimaryKey(autoGenerate = true)
//    private Long noteId;
//
//    @NonNull
//    private String note;
//
//
//    @NonNull
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(@NonNull String note) {
//        this.note = note;
//    }

//}
