package org.iclick.doctor;

import com.sun.org.apache.xpath.internal.operations.String;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Prashan
 */
public class Doctor_DA {

    private static DbConnecter dbCon = DbConnecter.getConnection();
    private Statement st;
    private ResultSet rs;
    private static Doctor_DA singleton = null;

    private int doctorcount;
    private String firstname;
    private String lastname;

    public synchronized static Doctor_DA getConnection(DbConnecter dbCon) {
        if (singleton == null) {
            singleton = new Doctor_DA(dbCon);
            return singleton;
        } else {
            return singleton;
        }
    }

    public synchronized static Doctor_DA getConnection() {
        if (singleton == null) {
            singleton = new Doctor_DA(dbCon);
            return singleton;
        } else {
            return singleton;
        }
    }

    private Doctor_DA(DbConnecter dbCon) {
        this.dbCon = dbCon;
    }

    /**
     * Prescription data should be access through the Prescription_DA...
     *
     * @param
     */
    public ArrayList<String> getnames() {
        ArrayList<String> details = new ArrayList<String>();

        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT First_name, Last_name FROM `doctor`";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                firstname = rs.getString("First_name");
                lastname = rs.getString("Last_name");
                details.add("Dr. " + firstname + " " + lastname);

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }

    public void UpdateData(String first, String last, String hos, String qual, String m1, String m2, int did, ArrayList<Integer> MID) {
        ArrayList<String> no = new ArrayList<String>();
        no.add(m1);
        no.add(m2);
        try {
            st = dbCon.getCon().createStatement();
            String query = "UPDATE `doctor` SET `First_name`='" + first + "',`Last_name`='" + last + "',`Hospital`='" + hos + "',`Qualifications= '" + qual + "' WHERE Doctor_Id = " + did + "";
            st.executeUpdate(query);
            System.out.println("Updated");
            int count = MID.size();
            for (int i = 0; i < count; i++) {
                String query2 = "UPDATE 'doctor_mobile_numbers' SET `mobile_no`='" + no.get(i) + "' WHERE Doctor_Id =" + did + " AND `m_Id`= " + MID.get(i) + "";
                rs = st.executeQuery(query2);
            }
            if (count == 0) {
                String query2 = " INSERT INTO `doctor_mobile_numbers` (`Doctor_Id`,`mobile_no`) VALUES (" + did + ", " + m1 + ")";
                rs = st.executeQuery(query2);
                String query3 = " INSERT INTO `doctor_mobile_numbers` (`Doctor_Id`,`mobile_no`) VALUES (" + did + ", " + m2 + ")";
                rs = st.executeQuery(query3);
            }
            if (count == 1) {
                String query2 = " INSERT INTO `doctor_mobile_numbers` (`Doctor_Id`,`mobile_no`) VALUES (" + did + ", " + m2 + ")";
                rs = st.executeQuery(query2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("machaan");

        }
    }

    public void saveData(String first, String last, String hos, String qual, String spec) {
        Random rand = new Random();
        int randomNum = rand.nextInt((999 - 0) + 1) + 0;
        String username = last + randomNum;
        String type = "Doctor";
        String pass = Encrypt.cryptWithMD5("test123");

        int userid = -1;
        try {
            st = dbCon.getCon().createStatement();
            String query = "INSERT INTO `user_login` (`User_type`,`Username`,`Password`) VALUES ('" + type + "', '" + username + "','" + pass + "')";
            st.executeUpdate(query);
            userid = getmxUserId();
            String query2 = "INSERT INTO `doctor` (`User_Id`,`First_name`,`Last_name`,`Hospital`,`Qualifications`,`Specialized_Area`) VALUES (" + userid + ", '" + first + "', '" + last + "', '" + hos + "', '" + qual + "','" + spec + "')";
            st.execute(query2);
            JOptionPane.showMessageDialog(new JDialog(), "Doctor Account Created!\n Username ='" + username + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("machaan");

        }
    }

    public ArrayList<String> getData(int did) {
        ArrayList<String> details = new ArrayList<String>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "Select * From `doctor` WHERE Doctor_Id =" + did;
            rs = st.executeQuery(query);
            while (rs.next()) {
                details.add(rs.getString("First_name"));
                details.add(rs.getString("Last_name"));
                details.add(rs.getString("Hospital"));
                details.add(rs.getString("Qualifications"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("vv");

        }
        return details;
    }

    public String getpassword(int did) {
        String details = "null";
        try {
            st = dbCon.getCon().createStatement();
            String query = "Select Password FROM `user_login` where User_Id = (Select User_Id From `doctor` WHERE Doctor_Id =" + did + ")";
            rs = st.executeQuery(query);
            while (rs.next()) {
                details = rs.getString("Password");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("vv");

        }
        return details;
    }

    public void updatepassword(int uid, String pass) {
        String details = "null";
        try {
            st = dbCon.getCon().createStatement();
            String query = "UPDATE `user_login` SET Password ='" + pass + "' where User_id =" + uid;
            st.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("vv");

        }
        JOptionPane.showMessageDialog(new JDialog(), "Password Updated");
    }

    public int useridfordid(int did) {
        int userid = -1;
        try {
            st = dbCon.getCon().createStatement();
            String query = "Select User_Id From `doctor` WHERE Doctor_Id =" + did;
            rs = st.executeQuery(query);
            while (rs.next()) {
                userid = rs.getInt("User_Id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("vv");

        }
        return userid;
    }

    public ArrayList<Integer> getmobileId(int did) {
        ArrayList<Integer> mIDs = new ArrayList<Integer>();
        try {
            st = dbCon.getCon().createStatement();
            String query2 = "SELECT * FROM `doctor_mobile_numbers` WHERE Doctor_Id = " + did;
            rs = st.executeQuery(query2);
            while (rs.next()) {
                mIDs.add(rs.getInt("m_Id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("vv");

        }
        return mIDs;
    }

    public ArrayList<Integer> getmobileNo(int did) {
        ArrayList<Integer> Mobilno = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query2 = "SELECT * FROM `doctor_mobile_numbers` WHERE Doctor_Id =" + did;
            rs = st.executeQuery(query2);
            while (rs.next()) {
                Mobilno.add(rs.getInt("mobile_no"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Patient_DA.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("vv");

        }
        return Mobilno;
    }

    public ArrayList<String> getnamestospecificarea(String area) {
        ArrayList<String> details = new ArrayList<>();

        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT First_name, Last_name FROM `doctor` where Specialized_Area = " + "'" + area + "'";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                firstname = rs.getString("First_name");
                lastname = rs.getString("Last_name");
                details.add("Dr. " + firstname + " " + lastname);

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }

    public ArrayList<String> getdates(int id) {
        ArrayList<String> dates = new ArrayList<>();

        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Time FROM `doctor_availablity` where Doctor_ID = " + id + " group by Time ";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                dates.add(rs.getString("Time"));
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return dates;
    }

    public ArrayList<String> gettimes(int id, String day) {
        ArrayList<String> dates = new ArrayList<>();

        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Time FROM `doctor_availablity` where Doctor_ID = " + id + " AND Day = " + "'" + day + "'";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                dates.add(rs.getString("Time"));
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        System.out.println("awaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return dates;
    }

    public int getselecteddDoctorCount(String area) {
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `doctor` where Specialized_Area = " + "'" + area + "'";
            rs = st.executeQuery(query);
            doctorcount = 0;
            while (rs.next()) {
                doctorcount++;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return doctorcount;

    }

    public int getDoctorCount() {
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `doctor`";
            rs = st.executeQuery(query);
            doctorcount = 0;
            while (rs.next()) {
                doctorcount++;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return doctorcount;

    }

    public int getDoctorId(String firstname, String lastname) {
        int id = -1;
        System.out.println("mekata awa");
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Doctor_Id FROM `doctor` where First_name = " + "'" + firstname + "' AND Last_name= " + "'" + lastname + "'";
            rs = st.executeQuery(query);

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("Doctor_Id"));
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        System.out.println(id);
        return id;

    }

    public ArrayList<String> getdatatospecificarea(String area) {
        ArrayList<String> details = new ArrayList<>();

        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT First_name,Last_name,Specialized_Area,Hospital,Day,Time FROM doctor INNER JOIN doctor_availablity ON doctor.Doctor_Id=doctor_availablity.Doctor_ID where Specialized_Area = '" + area + "'";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                firstname = rs.getString("First_name");
                lastname = rs.getString("Last_name");
                details.add("Dr. " + firstname + " " + lastname);
                details.add(rs.getString("Specialized_Area"));
                details.add(rs.getString("Hospital"));
                details.add(rs.getString("Day"));
                details.add(rs.getString("Time"));

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }

    public ArrayList<String> getdatatospecificname(String name) {
        ArrayList<String> details = new ArrayList<>();

        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT First_name,Last_name,Specialized_Area,Hospital,Day,Time FROM doctor INNER JOIN doctor_availablity ON doctor.Doctor_Id=doctor_availablity.Doctor_ID where Last_name LIKE '%" + name + "%'";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                firstname = rs.getString("First_name");
                lastname = rs.getString("Last_name");
                details.add("Dr. " + firstname + " " + lastname);
                details.add(rs.getString("Specialized_Area"));
                details.add(rs.getString("Hospital"));
                details.add(rs.getString("Day"));
                details.add(rs.getString("Time"));

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }

    public ArrayList<String> getdatatospecificnameandarea(String area, String name) {
        ArrayList<String> details = new ArrayList<>();

        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT First_name,Last_name,Specialized_Area,Hospital,Day,Time FROM doctor INNER JOIN doctor_availablity ON doctor.Doctor_Id=doctor_availablity.Doctor_ID where Last_name LIKE '%" + name + "%' AND Specialized_Area = '" + area + "'";
            rs = st.executeQuery(query);
            System.out.println("Records from the database");
            while (rs.next()) {
                firstname = rs.getString("First_name");
                lastname = rs.getString("Last_name");
                details.add("Dr. " + firstname + " " + lastname);
                details.add(rs.getString("Specialized_Area"));
                details.add(rs.getString("Hospital"));
                details.add(rs.getString("Day"));
                details.add(rs.getString("Time"));

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }

    public int getmxUserId() {
        int id = -1;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT max(User_Id) FROM `user_login`";
            rs = st.executeQuery(query);

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("max(User_Id)"));
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return id;

    }

    public String getDoctorName(int id) {
        String name = null;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `doctor` where Doctor_Id = " + id;
            rs = st.executeQuery(query);

            while (rs.next()) {
                String fname = rs.getString("First_name");
                String lname = rs.getString("Last_name");
                name = fname + " " + lname;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return name;

    }

    public String getDoctorHospital(int id) {
        String hosname = null;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `doctor` where Doctor_Id = " + id;
            rs = st.executeQuery(query);

            while (rs.next()) {
                hosname = rs.getString("Hospital");

            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return hosname;

    }

    public ArrayList<String> lastappointment(int pid, int did) {
        ArrayList<String> available = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Date, Time FROM `channelling` where Doctor_Id = " + did + " AND Patient_Id = " + pid + " Order by Date DESC, Time DESC Limit 1";
            rs = st.executeQuery(query);

            while (rs.next()) {
                available.add(rs.getString("Date"));
                available.add(rs.getString("Time"));
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return available;

    }

    public ArrayList<String> availabity(int id) {
        ArrayList<String> available = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Day, Time FROM `doctor_availablity` where Doctor_ID = " + id;
            rs = st.executeQuery(query);

            while (rs.next()) {
                available.add(rs.getString("Day"));
                available.add(rs.getString("Time"));
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return available;

    }

    public int availabitycount(int id) {
        int count = -1;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Day, Time FROM `doctor_availablity` where Doctor_ID = " + id;
            rs = st.executeQuery(query);
            count = 0;
            while (rs.next()) {
                count++;
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return count;

    }

    public int getdoctorid(int userid) {
        int docid = -1;
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT Doctor_Id FROM `doctor` where User_Id = " + userid;

            rs = st.executeQuery(query);

            while (rs.next()) {
                docid = rs.getInt("Doctor_Id");
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        System.out.println("user Id" + docid);
        return docid;

    }
    

    public ArrayList<String> getTodaylist(String day) {
        ArrayList<String> details = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `doctor` WHERE Doctor_Id in (SELECT Doctor_Id FROM `doctor_availablity` WHERE Day = '"+day+"')";
            rs = st.executeQuery(query);

            System.out.println("Records from the database nn");
            while (rs.next()) {
                details.add(rs.getString("Doctor_Id"));  //0
                details.add(rs.getString("First_name"));  //1
                details.add(rs.getString("Last_name"));  //2
                details.add(rs.getString("Specialized_Area"));//3
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }
    
    public ArrayList<String> getTodaypatientlist(int did, String date) {
        ArrayList<String> details = new ArrayList<>();
        try {
            st = dbCon.getCon().createStatement();
            String query = "SELECT * FROM `patient` WHERE Patient_Id in (SELECT Patient_Id FROM `channelling` WHERE Doctor_Id = "+did+" AND Date='"+date+"')";
            rs = st.executeQuery(query);

            System.out.println("Records from the database nn");
            while (rs.next()) {
                details.add(rs.getString("Patient_Id"));  //0
                details.add(rs.getString("First_name"));  //1
                details.add(rs.getString("Last_name"));  //2
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
        return details;
    }
}
