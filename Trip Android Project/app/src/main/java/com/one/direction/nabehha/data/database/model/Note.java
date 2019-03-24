//package com.one.direction.nabehha.data.database.model;
//
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.ForeignKey;
//import android.arch.persistence.room.Index;
//import android.arch.persistence.room.PrimaryKey;
//import android.support.annotation.NonNull;
//
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
//   }
//
//}

package com.one.direction.nabehha.data.database.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Note  implements Parcelable {

    private String Note;

    protected Note(Parcel in) {
        Note = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Note);
    }
}

