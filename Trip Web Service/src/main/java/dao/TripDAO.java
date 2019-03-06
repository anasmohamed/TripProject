/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Trip;

/**
 *
 * @author Tahoon
 */
public class TripDAO {

    DataBaseConnectionHandler connection;

    public boolean addTrip(Trip trip) {
        try {
            connection = DataBaseConnectionHandler.getInstance();

            PreparedStatement pst = connection.getConnection().prepareStatement("insert into TRIP ("
                    + "TRIP_ID,"
                    + "TRIP_NAME,"
                    + "START_POINT,"
                    + "END_POINT,"
                    + "TRIP_DATE,"
                    + "TRIP_TIME,"
                    + "TRIP_TYPE,"
                    + "TRIP_IMAGE,"
                    + "USER_ID,"
                    + "TRIP_STATUS)"
                    + "Values (?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, trip.getTrip_id());
            pst.setString(2, trip.getTripName());
            pst.setInt(3, trip.getStartPoint());
            pst.setInt(4, trip.getEndPoint());
            pst.setString(5, trip.getDate());
            pst.setString(6, trip.getTime());
            pst.setString(7, trip.getType());
            pst.setString(8, trip.getTripImage());
            pst.setInt(9, trip.getUserID());
            pst.setString(10, trip.getStatus());
            int executeUpdate = pst.executeUpdate();

            pst.close();
            connection.close();
            if (executeUpdate > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TripDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    
   //delete trip from "TRIP" by trip id 
    public boolean deleteFromTrip(int tripId) {
        connection = DataBaseConnectionHandler.getInstance();
        PreparedStatement pst;
        try {
            pst = connection.getConnection().prepareStatement("delete From Trip where Trip_id=?");
            pst.setInt(1, tripId);
            int executeUpdate = pst.executeUpdate();
            pst.close();
            connection.close();
            if (executeUpdate > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TripDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // delete user Tripsss by user id 
    public boolean deleteUserTrip(int userId) {
        connection = DataBaseConnectionHandler.getInstance();
        PreparedStatement pst;

        try {
            pst = connection.getConnection().prepareStatement("delete From TRIP where user_id=?");

            pst.setInt(1, userId);
            int executeUpdate = pst.executeUpdate();
            pst.close();
            connection.close();
            if (executeUpdate > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TripDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    //update Status To "Done or End" 
     public boolean updateStatus(String status, int userId, int tripId) {

        connection = DataBaseConnectionHandler.getInstance();
        PreparedStatement pst;
        try {
            pst = connection.getConnection().prepareStatement("update TRIP set TRIP_STATUS=? where user_id=? and trip_id=?");
            pst.setString(1, status);
            pst.setInt(2, userId);
            pst.setInt(3, tripId);

            int executeUpdate = pst.executeUpdate();
            pst.close();
            connection.close();
            if (executeUpdate > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TripDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
