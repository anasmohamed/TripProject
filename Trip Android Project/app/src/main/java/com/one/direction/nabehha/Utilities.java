package com.one.direction.nabehha;

import com.one.direction.nabehha.data.database.model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.one.direction.nabehha.AppConstants.BASE_GOOGLE_STATIC_MAP_URL;
import static com.one.direction.nabehha.AppConstants.GOOGLE_STATIC_MAP_KEY;

public class Utilities {

    static public String getGoogleMapImageForTrips(List<Trip> tripArrayList) {
        StringBuilder urlString = new StringBuilder(BASE_GOOGLE_STATIC_MAP_URL);
        for (int i = 1, size = tripArrayList.size(); i <= size; i++) {
            Trip trip = tripArrayList.get(i - 1);
            String hexaColor = Integer.toHexString((16777215 / size) * i);
            urlString.
                    append("&markers=color:0x").append(hexaColor).append("|")
                    .append(trip.getStartPointLatitude()).append(",").append(trip.getStartPointLongitude())
                    .append("&markers=color:0x").append(hexaColor).append("|").append(trip.getEndPointLatitude())
                    .append(",").append(trip.getStartPointLongitude())
                    .append("&path=color:0x").append(hexaColor)
                    .append("|weight:5|").append(trip.getStartPointLatitude()).append(",").append(trip.getStartPointLongitude())
                    .append("|").append(trip.getEndPointLatitude()).append(",").append(trip.getStartPointLongitude());
        }
        urlString.append("&key=").append(GOOGLE_STATIC_MAP_KEY);
        return urlString.toString();
    }

    static public String getGoogleMapImageForTrip(Trip trip) {
        StringBuilder urlString = new StringBuilder(BASE_GOOGLE_STATIC_MAP_URL);
        //random color : this 16777216 = 0xffffff +1
        String hexaColor = Integer.toHexString(new Random().nextInt(16777216));
        urlString.
                append("&markers=color:0x").append(hexaColor).append("|")
                .append(trip.getStartPointLatitude()).append(",").append(trip.getStartPointLongitude())
                .append("&markers=color:0x").append(hexaColor).append("|").append(trip.getEndPointLatitude())
                .append(",").append(trip.getStartPointLongitude())
                .append("&path=color:0x").append(hexaColor)
                .append("|weight:5|").append(trip.getStartPointLatitude()).append(",").append(trip.getStartPointLongitude())
                .append("|").append(trip.getEndPointLatitude()).append(",").append(trip.getStartPointLongitude());
//                .append("&maptype=satellite");

        urlString.append("&key=").append(GOOGLE_STATIC_MAP_KEY);
        return urlString.toString();
    }
}
