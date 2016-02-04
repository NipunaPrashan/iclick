/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iclick.doctor.dbaccess;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PRAVINDA PERERA
 */
public class MessageDataAccessManager {

    private DbConnectionManager dbCon = DbConnectionManager.getInstance();
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst = null;
    private static MessageDataAccessManager singleton = null;
    private Calendar currentDate = Calendar.getInstance(); //Get the current date
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
    private SimpleDateFormat timer = new SimpleDateFormat("HH:mm:ss"); //format it as per your requirement
    private String dateNow = formatter.format(currentDate.getTime());
    private String timeNow = timer.format(currentDate.getTime());

    public synchronized static MessageDataAccessManager getConnection(DbConnectionManager dbCon) {
        if (singleton == null) {
            singleton = new MessageDataAccessManager(dbCon);
            return singleton;
        } else {
            return singleton;
        }
    }

    MessageDataAccessManager(DbConnectionManager dbCon) {
        this.dbCon = dbCon;
    }

    public ArrayList<String>[] nameNidList(int did) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();
        ArrayList[] array = {id, names};
        String tempId;
        String tempName = null;

        try {
            st = dbCon.getInstance().createStatement();
            String query = "SELECT Patient_Id FROM `messages` WHERE `Reply_Status` = 0 AND Doctor_Id = ?";
            pst = dbCon.getInstance().prepareStatement(query);
            pst.setInt(1, did);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("yako Syntax awlak");
            }
            System.out.println("Records from the database");
            while (rs.next()) {
                tempId = rs.getString("Patient_Id");
                if (id.contains(tempId) == false) {
                    id.add(tempId);
                    System.out.println(tempId);
                }
            }

            for (int i = 0; i < id.size(); i++) {
                tempId = id.get(i);
                query = "SELECT First_name FROM `patient` WHERE Patient_Id = " + Integer.parseInt(tempId);
                rs = st.executeQuery(query);
                while (rs.next()) {
                    tempName = rs.getString("First_name");
                }
                if (tempName != null) {
                    names.add(tempName);
                } else {
                    System.out.println("Name error!!!");
                }
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        // return channel;

        return array;
    }

    public ArrayList<String>[] getAllRecievedMessages(int id) {
        ArrayList<String> message = new ArrayList<>();
        ArrayList<String> reply = new ArrayList<>();
        ArrayList[] array = {message, reply};

        try {
            st = dbCon.getInstance().createStatement();
            String query = "SELECT * FROM `messages` WHERE `messages`.`Status` = 1 and Patient_Id = " + id;
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                message.add(rs.getString("Message_By_Patient"));
                reply.add(rs.getString("Message_By_Doctor"));
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }

        return array;
    }
    public String getMessageHeader(int mid) {
        String header = null;
        try {
            st = dbCon.getInstance().createStatement();
            String query = "SELECT Message_Header FROM `messages` WHERE Message_Id = " + mid;
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                header = rs.getString("Message_Header");
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return header;
    }

    public ArrayList[] getRecievedMessages(int id, int did) {
        ArrayList<String> message = new ArrayList<>();
        ArrayList<String> messageNo = new ArrayList<>();
        ArrayList[] array = {messageNo, message};
        try {
            st = dbCon.getInstance().createStatement();
            String query = "SELECT * FROM `messages` WHERE `Reply_Status` = 0 AND Patient_Id = " + id +" AND Doctor_Id = "+did+"";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                message.add(rs.getString("Message_by_patient"));
                messageNo.add(rs.getString("Message_Id"));
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }

        return array;
    }

    //messages to be send by the Doctor
    public ArrayList<Integer> getSendersIDs() {
        ArrayList<Integer> id = new ArrayList<>();

        try {
            st = dbCon.getInstance().createStatement();
            String query = "SELECT Patient_Id FROM `messages` WHERE `messages`.`Reply_Status` = 0 AND `messages`.`Status` = 1";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                id.add(Integer.parseInt(rs.getString("Patient_Id")));
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }

        return id;
    }

    public void reply(int messageId, String message) {
        String dateandtime = dateNow+ " "+timeNow;
        try {
            st = dbCon.getInstance().createStatement();
            String query = "UPDATE messages SET Message_by_doctor = '" + message + "', Reply_Status = 1, Reply_Date_Time = '" + dateandtime + "' WHERE Message_Id = " + messageId;
            st.executeUpdate(query);
            System.out.println("Updated");

        } catch (SQLException ex) {
            Logger.getLogger(PatientDataAccessManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(int patientId, String message, int did) {
        try {

            st = dbCon.getInstance().createStatement();
            String query = "INSERT INTO `messages` (`Doctor_Id`, `Patient_Id`, `Message_by_patient`,`Message_by_doctor`, `Date`,`Time`, `Reply_status`) VALUES ('" + did + "', '" + patientId + "', '', '" + message + "', '" + dateNow + "', '" + timeNow + "','0')";
            st.executeUpdate(query);
            System.out.println("Inserted");

        } catch (SQLException ex) {
            Logger.getLogger(PatientDataAccessManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
