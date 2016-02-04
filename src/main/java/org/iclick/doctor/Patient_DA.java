package org.iclick.doctor;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Pravinda Perera
 */
public class Patient_DA {

    private static DbConnecter dbCon = DbConnecter.getConnection();
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst = null;

    private static Patient_DA singleton = null;
    private int patientcount;
    private int count;

    private String firstname;
    private String lastname;
    private String MobileNo;
    private String DOB;
    private String year;
    private String month;
    private String date;
    private String Address;
    private String Efirst;
    private String ELast;
    private String EMobilNo;
    private String username;
    private String password;

    public synchronized static Patient_DA getConnection(DbConnecter dbCon) {
        if (singleton == null) {
            singleton = new Patient_DA(dbCon);
            return singleton;
        } else {
            return singleton;
        }
    }

    public synchronized static Patient_DA getConnection() {
        if (singleton == null) {
            singleton = new Patient_DA(dbCon);
            return singleton;
        } else {
            return singleton;
        }
    }

    private Patient_DA(DbConnecter dbCon) {
        this.dbCon = dbCon;
    }

    public ArrayList<String> getAllData(int pId) {
        ArrayList<String> details = new ArrayList<>();
        String sql = "SELECT * FROM `patient` WHERE Patient_Id =?";
        try {

            pst = dbCon.getCon().prepareStatement(sql);
            pst.setInt(1, pId);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("yako Syntax awlak");
            }
            while (rs.next()) {
                details.add(rs.getString("First_name"));  //0
                details.add(rs.getString("Last_name"));  //1
                details.add(rs.getString("Mobile_number"));         //2
                details.add(rs.getString("Date_of_birth"));   //3             
                details.add(rs.getString("Address"));     //4
                details.add(rs.getString("Emerg_First_name")); //5
                details.add(rs.getString("Emerg_Last_name"));  //6
                details.add(rs.getString("Emerg_contact_number"));  //7
                details.add(rs.getString("Gender"));  //8

            }

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return details;
    }

    public ArrayList<String> getAllData(String name) {
        String tname = '%' + name + '%';
        System.out.println(tname);
        ArrayList<String> details = new ArrayList<>();
        try {
            String query = "SELECT * FROM `patient` WHERE First_name LIKE ? ORDER BY First_name";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setString(1, tname);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            System.out.println("Records from the database");
            while (rs.next()) {
                details.add(rs.getString("First_name"));   //0
                details.add(rs.getString("Date_of_birth"));         //1
                details.add(rs.getString("Address"));     //2
                details.add(rs.getString("Patient_Id"));     //2
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }

    public int getPatientCount() {
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `patient`";
            rs = st.executeQuery(query);
            patientcount = 0;
            while (rs.next()) {
                patientcount++;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return patientcount;

    }

    public void saveData(int id, String name, String paswrd) {
        try {
            st = dbCon.getCon().createStatement();
            String query = "INSERT INTO `patient` (`Patient_Id`, `Pasword`, `UserName`, `Real_Name`, `First_Name`, `Last_Name`, `Gender`, `Mobile_No`, `DOB`, `Address`, `Emerg_First_Name`, `Emerg_Last_Name`, `Emerg_Gender`, `Emerg_Mobile_No`, `Emerg_Address`) VALUES (?,?,?, '', '', '', '', NULL, NULL, '', '', '', '', NULL, '')";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, paswrd);
            pst.setString(3, name);
            try {
                pst.executeUpdate();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            //st.executeUpdate(query);
            System.out.println("Updated");

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveData(String first, String last, String gender, String mobile, String BDate, String address, String user, String pass, String efirst, String elast, String emobile) {
        try {
            st = dbCon.getCon().createStatement();
            String query = "INSERT INTO `patient` (`Username`, `Password`,`First_name`,`Last_name`,`Gender`,`Mobile_number`,`Date_of_birth`,`Address`, `Emerg_First_name`, `Emerg_Last_name`, `Emerg_contact_number`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, pass);
            pst.setString(3, first);
            pst.setString(4, last);
            pst.setString(5, gender);
            pst.setString(6, mobile);
            pst.setString(7, BDate);
            pst.setString(8, address);
            pst.setString(9, efirst);
            pst.setString(10, elast);
            pst.setString(11, emobile);
            try {
                pst.executeUpdate();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            // st.executeUpdate(query);
            System.out.println("Updated");
            String query2 = "SELECT * FROM `patient`";
            rs = st.executeQuery(query2);
            patientcount = 0;
            while (rs.next()) {
                patientcount++;
            }
            JOptionPane.showMessageDialog(new JDialog(), "Patient Account Created! Id =" + patientcount);

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("vv");

        }
    }

    public void updateDataa(int PID, String first, String last, String gender, String mobile, String BDate, String address) {
        try {
            st = dbCon.getCon().createStatement();
            //String query = " `patients`  (`Patient_Id`,`Pasword`, `UserName`,`Real_Name`,`First_Name`,`Last_Name`,`Gender`,`Mobile_No`,`DOB`,`Address`) VALUES ('" + PID + "','" + pass + "', '" + user + "', '" + fullname + "', '" + first + "', '" + last + "', '" + gender + "', '" + mobile + "', '" + BDate + "', '" + address + "')";
           // String query = "UPDATE `patient` SET `First_name`=?,`Last_name`=?,`Gender`=?,`Mobile_number`=?,`Date_of_birth`=?,`Address`=? WHERE Patient_Id =?";
            String query = "UPDATE `patient` SET `First_name`='" + first + "',`Last_name`='" + last + "',`Gender`='" + gender + "',`Mobile_number`='" + mobile + "',`Date_of_birth`='" + BDate + "',`Address`='" + address + "' WHERE Patient_Id ='" + PID + "'";
//            pst = dbCon.getCon().prepareStatement(query);
//            pst.setString(1, first);
//            pst.setString(2, last);
//            pst.setString(3, gender);
//            pst.setString(4, mobile);
//            pst.setString(5, BDate);
//            pst.setString(6, address);
//            pst.setInt(7, PID);
//            try {
//                pst.executeUpdate();
//            } catch (MySQLSyntaxErrorException e) {
//                System.out.println("Yako Syntax awlak");
//            }

            st.executeUpdate(query);
            System.out.println("Updated");
            String query2 = "SELECT * FROM `patient`";
            rs = st.executeQuery(query2);
            patientcount = 0;
            while (rs.next()) {
                patientcount++;
            }
            JOptionPane.showMessageDialog(new JDialog(), "Patient Account Updated! Id =" + PID);

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public String getPatient_UserName(int pId) {
        String pName = new String();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT UserName FROM `patients` WHERE Patient_Id =?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            //rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                String name = rs.getString("UserName");
                pName = name;

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return pName;
    }

    public String getDOB(int pId) {
        String pDOB = new String();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Date_of_birth FROM `patient` WHERE Patient_Id =?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            // rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                pDOB = rs.getString("Date_of_birth");
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return pDOB;
    }

    public int channelcountbydate(String date, int did, String time) {
        int channelcount = 0;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `channelling` where Date = ? AND Doctor_Id =? AND Time =?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setString(1, date);
            pst.setInt(2, did);
            pst.setString(3, time);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            //  rs = st.executeQuery(query);
            System.out.println("Records from the database");

            while (rs.next()) {
                channelcount++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return channelcount;
    }

    public String getPatient_RealName(int pId) {
        String fName;
        String lName;
        String Name = "null";
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `patient` WHERE Patient_Id =?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            // rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                fName = rs.getString("First_name");
                lName = rs.getString("Last_name");
                Name = fName + " " + lName;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return Name;
    }

    public String getPatient_lastName(int pId) {
        String LName = new String();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `patients` WHERE Patient_Id =?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            //  rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                String name = rs.getString("Last_Name");
                LName = name;

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return LName;
    }

    public String getPatient_Address(int pId) {
        String pAddress = null;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Address FROM `patient` WHERE Patient_Id =?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            // rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                pAddress = rs.getString("Address");

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return pAddress;
    }

    public int getPatientMobile(int pId) {
        int pMob = -1;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Mobile_number FROM `patient` WHERE Patient_Id =?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            // rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                pMob = Integer.parseInt(rs.getString("Mobile_number"));
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return pMob;
    }

    public String getPatientGender(int pId) {
        String pGender = null;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Gender FROM `patient` WHERE Patient_Id =?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            // rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                pGender = rs.getString("Gender");
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return pGender;
    }

    public ArrayList<String> getChannelIDList(String date, int did, String time) {
        ArrayList<String> channel = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `channelling` WHERE Date =? AND Time =? AND Doctor_Id= ?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setString(1, date);
            pst.setString(2, time);
            pst.setInt(3, did);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            //  rs = st.executeQuery(query);
            System.out.println("Records from the database");
            int i = 0;
            while (rs.next()) {
                String patientId = rs.getString("Patient_Id");
                channel.add(patientId);
                //System.out.println("Channel Number "+channelNum+" Patient Id "+patientId);

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return channel;
    }

    public ArrayList<String> getNaturalJoinList(String date) {
        ArrayList<String> channel = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `channelling` NATURAL JOIN `patient` WHERE Date =? ORDER by Time";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setString(1, date);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            // rs = st.executeQuery(query);
            System.out.println("Records from the database");
            int i = 0;
            while (rs.next()) {

                String patientRealname = rs.getString("First_name"); //0
                channel.add(patientRealname);
                String patientDOB = rs.getString("Date_of_birth"); //1
                channel.add(patientDOB);
                String patientAddress = rs.getString("Address"); //2
                channel.add(patientAddress);
                String patientId = rs.getString("Patient_Id"); //3
                channel.add(patientId);

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return channel;
    }

    public ArrayList<String> getupdatedPrescription(String date, int did) {
        ArrayList<String> channel = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `prescription` WHERE Date =? AND Doctor_Id =? ORDER by Time DESC";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setString(1, date);
            pst.setInt(2, did);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }

            // rs = st.executeQuery(query);
            System.out.println("Records from the database");
            int i = 0;
            while (rs.next()) {
                String patientId = rs.getString("Patient_Id"); //1,3,5,7,9
                channel.add(patientId);

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return channel;
    }

    public int getChannelNumber(int pId, String date, int did, String time) {
        int channelNum = -1;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `channelling` WHERE Date =? AND Doctor_Id =? ORDER by Time";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setString(1, date);
            pst.setInt(2, did);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }

            //rs = st.executeQuery(query);
            System.out.println("Records from the database");
            count = 1;
            while (rs.next()) {

                if (Integer.parseInt(rs.getString("Patient_Id")) != pId) {
                    count++;
                }
                if (Integer.parseInt(rs.getString("Patient_Id")) == pId) {
                    channelNum = count;
                }
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return channelNum;
    }

    public void cancellastappointment(int pId, int did) {
        try {
            st = dbCon.getCon().createStatement();
            String query = "DELETE FROM `channelling` WHERE Patient_Id =? AND Doctor_Id =? Order by Date DESC, Time DESC Limit 1";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            pst.setInt(2, did);
            try {
                rs = pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }

            st.executeUpdate(query);
            System.out.println("Records from the database");
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }

    }

    public int channelDoc(int pId, String date, String time, int did) {
        int returnval = 0;
        int pcount = -1;
        int enetval = -1;

        try {
            st = dbCon.getCon().createStatement();
            String query1 = "Select * from `channelling` where Patient_Id =? AND Date = ? AND Doctor_Id =?";
            String query2 = "Select max(channel_Num) from `channelling` where Date = " + "'" + date + "' AND Time = " + "'" + time + "'AND Doctor_Id =" + did + "";
            pst = dbCon.getCon().prepareStatement(query1);
            pst.setInt(1, pId);
            pst.setString(2, date);
            pst.setInt(3, pId);
            try {
                pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            //rs = st.executeQuery(query1);

            if (rs.next()) {
                JOptionPane.showMessageDialog(new JDialog(), "This patient Already have an appointment on this day to the selected Doctor");
                returnval = -1;
            } else {

                rs = st.executeQuery(query2);

                while (rs.next()) {
                    pcount = rs.getInt("max(channel_Num)");
                    System.out.println("danata" + pcount);
                }
                enetval = pcount + 1;
                String query = "INSERT INTO `channelling` (`Patient_Id`,`channel_Num`, `Date`,`Time`,`Doctor_Id`) VALUES ('" + pId + "','" + enetval + "', '" + date + "','" + time + "','" + did + "')";
                st.executeUpdate(query);
                System.out.println("Inserted");
                returnval = pcount + 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnval;
    }

    public void deleteChannel(int pId, String date) {
        try {
            st = dbCon.getCon().createStatement();

            String query = "DELETE FROM `channelling` WHERE Patient_Id =? AND Date='?";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setInt(1, pId);
            pst.setString(2, date);

            try {
                pst.executeUpdate();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }
            // st.executeUpdate(query);
            System.out.println("Deleted");

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checktheChannel(int pId, String date) {
        boolean status = true;
        int count = 0;
        try {
            st = dbCon.getCon().createStatement();

            String query = "Select * FROM `channelling` WHERE Date = ? AND Patient_Id =? ";
            pst = dbCon.getCon().prepareStatement(query);
            pst.setString(1, date);
            pst.setInt(2, pId);
            try {
                pst.executeQuery();
            } catch (MySQLSyntaxErrorException e) {
                System.out.println("Yako Syntax awlak");
            }

            //rs = st.executeQuery(query);
            System.out.println("Records from the database");
            count = 0;
            while (rs.next()) {
                count++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count == 0) {
            status = false;
        }

        return status;
    }

    public ArrayList<String> patientList(String name,int did) {
        String tname = '%' + name + '%';
        System.out.println(tname);
        ArrayList<String> details = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `patient` WHERE Patient_Id in (Select Patient_Id from channelling where First_name LIKE '"+tname+"' AND Doctor_id ="+did+")";
//            pst = dbCon.getCon().prepareStatement(query);
//            pst.setString(1, tname);
//            pst.setInt(2, did);
//            try {
//                pst.executeQuery();
//            } catch (MySQLSyntaxErrorException e) {
//                System.out.println("Yako Syntax awlak");
//            }


            rs = st.executeQuery(query);

            System.out.println("Records from the database nn");
            while (rs.next()) {
                details.add(rs.getString("Patient_Id"));  //0
                details.add(rs.getString("First_name"));  //1
                details.add(rs.getString("Last_name"));  //2
                details.add(rs.getString("Gender"));  //3         
                details.add(rs.getString("Address"));     //4
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }

}
