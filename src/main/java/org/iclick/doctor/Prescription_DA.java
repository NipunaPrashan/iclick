
package org.iclick.doctor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Pravinda Perera
 */
public class Prescription_DA {

    private static DbConnecter dbCon = DbConnecter.getConnection();
    private Statement st;
    private ResultSet rs, rs1;
    private static Prescription_DA singleton = null;

    /**
     * This class is a singleton class pattern - Singleton pattern
     *
     * @param dbCon
     */
    public synchronized static Prescription_DA getConnection(DbConnecter dbCon) {
        if (singleton == null) {
            singleton = new Prescription_DA(dbCon);
            return singleton;
        } else {
            return singleton;
        }
    }

    public synchronized static Prescription_DA getConnection() {
        if (singleton == null) {
            singleton = new Prescription_DA(dbCon);
            return singleton;
        } else {
            return singleton;
        }
    }

    private Prescription_DA(DbConnecter dbCon) {
        this.dbCon = dbCon;
    }

    public void savePrescrib(int patient_Id, String medicine[], String history[], String date) {
        int i = 0;
        StringBuilder tempstr = new StringBuilder();
        StringBuilder temphis = new StringBuilder();
        while (i < medicine.length) {
            if (i == medicine.length - 1) {
                tempstr.append(medicine[i]);
            } else {
                tempstr.append(medicine[i]);
                tempstr.append("\n");
            }
            i++;
        }
        i = 0;
        while (i < history.length) {
            if (i == history.length - 1) {
                temphis.append(history[i]);
            } else {
                temphis.append(history[i]);
                temphis.append("\n");
            }
            i++;
        }
        try {
            st = dbCon.getCon().createStatement();

            String query = "INSERT INTO `prescription` (`Prescription_Id`,`Patient_Id`, `Description`, `Date`, `History`) VALUES (''," + patient_Id + "', '" + tempstr + "', '" + date + "', '" + temphis + "')";

            st.executeUpdate(query);

            System.out.println("Newly Updated");

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void savePrescrib(int patient_Id,int doc_Id, String medicine[], String history[], String date, String time) {
        int i = 0;
        StringBuilder tempstr = new StringBuilder();
        StringBuilder temphis = new StringBuilder();
        while (i < medicine.length) {
            if (i == medicine.length - 1) {
                tempstr.append(medicine[i]);
            } else {
                tempstr.append(medicine[i]);
                tempstr.append("\n");
            }
            i++;
        }
        i = 0;
        while (i < history.length) {
            if (i == history.length - 1) {
                temphis.append(history[i]);
            } else {
                temphis.append(history[i]);
                temphis.append("\n");
            }
            i++;
        }
        try {
            st = dbCon.getCon().createStatement();

            String query = "INSERT INTO `prescription` (`Patient_Id`, `Doctor_id`,`Description`, `Date`, `History`, `Time`) VALUES (" + patient_Id + "," + doc_Id + ", '" + tempstr + "', '" + date + "', '" + temphis + "', '" + time + "')";

            st.executeUpdate(query);

            System.out.println("Newly Updated");
            JOptionPane.showMessageDialog(new JDialog(), "Updated Prescription");
          

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<String> getPrescribeMedicine(int patientId, String date) {
        ArrayList<String> health = new ArrayList<String>();
        try {
            st = dbCon.getCon().createStatement();

            String query = "SELECT Description, History FROM `prescription` WHERE Date = " + "'" + date + "'";

            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {

                String des = rs.getString("Description");
                String his = rs.getString("History");

                health.add(des);
                health.add(his);

//            System.out.println("History>>");
//            System.out.println(his);
//            System.out.println("Description>>");
//            System.out.println(des);
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }

        return health;
    }

    public ArrayList<String> getPrescribeDates(int pID, int dID) {
        ArrayList<String> dates = new ArrayList<String>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Date FROM `prescription` WHERE Patient_id = "+ pID + " AND Doctor_id = " + dID +" ";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                String date = rs.getString("Date");

                //System.out.println("Dates>>");
                dates.add(date);
            //System.out.println(date);

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return dates;
    }

    public ArrayList<String> getTodayMedi(int patientId, int did) {
        ArrayList<String> currentDetails = new ArrayList<String>();
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());

        try {

            st = dbCon.getCon().createStatement();
            String query = "SELECT Description FROM `prescription`where Patient_Id='" + patientId + "'AND Doctor_id='"+did+"' AND Date= '" + dateNow + "' ";
            rs = st.executeQuery(query);
            //System.out.println("Records from the database");
            while (rs.next()) {
                String des = rs.getString("Description");
                currentDetails.add(des);
            }

//            String query2 = "SELECT Description FROM `prescription`where Patient_Id='" + patientId + "' AND Date= '" + dateNow + "' ";
//            rs1 = st.executeQuery(query2);
//            //System.out.println("Records from the database");
//            while (rs1.next()) {
//                String PressId = rs1.getString("Prescription_Id");
//                currentDetails.add(PressId);
//
//            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return currentDetails;

    }

}
