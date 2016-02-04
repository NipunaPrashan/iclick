package org.iclick.doctor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Prashan
 */
public class Doctor extends User {

    private Doctor_DA testd = Doctor_DA.getConnection(DbConnecter.getConnection());
    private String date;
    private String first;
    private String last;
    private String hospital;
    private String qualification;
    private String spec;


    public String getHospital() {
        return hospital;
    }

    public String getQualification() {
        return qualification;
    }

    public Doctor(String name, int id, String paswrd, Doctor_DA testp) {
        super(name, id, paswrd);
        this.testd = testp;

    }

    public Doctor(String fname, String lname) {
        super(fname, lname, "");

    }

    public Doctor(int id, Doctor_DA testp, String date) {
        super(id);
        this.testd = testp;
        this.date = date;
    }

    public Doctor(int id) {
        super(id);
    }

    public Doctor() {
    }

    public Doctor(Doctor_DA testp, String date) {
        this.testd = testp;
        this.date = date;
        //  getList(testp,date);
    }

    public Doctor(String first, String last, String hos, String quali, String spc) {
        this.first = first;
        this.last = last;
        this.hospital = hos;
        this.qualification = quali;
        this.spec = spc;
    }

    public Doctor(int pId, Doctor_DA testp) {
        super(pId);
        this.testd = testp;
    }

    public Doctor(String name, String paswrd, Doctor_DA testp) {
        super(name, paswrd);
        this.testd = testp;
    }

    public Doctor(String name, Doctor_DA testp) {
        super(name);
        this.testd = testp;
    }

    public void savedata(String first, String last, String hospital, String Qualification, String mobile1, String mobile2, int id, ArrayList<Integer> MID) {
        testd.UpdateData(first, last, hospital, Qualification, mobile1, mobile2, id, MID);
    }

    public ArrayList<String> getList(Doctor_DA testp, int id) {
        ArrayList<String> list;
        list = testp.availabity(id);
        return list;
    }

    public String getpassword(int id) {
        String pass;
        pass = testd.getpassword(id);
        return pass;
    }

    public ArrayList<String> getpatientlastappoinmentdetails(Doctor_DA testp, int pid, int did) {
        ArrayList<String> details;
        details = testp.lastappointment(pid, did);
        return details;
    }

    public int getavailablecount(Doctor_DA testp, int id) {
        int count = -1;
        count = testp.availabitycount(id);
        return count;
    }

    public ArrayList<String> getNaturalJoinList(Patient_DA testp, String date) {
        ArrayList<String> list;
        list = testp.getNaturalJoinList(date);
        return list;
    }

    public ArrayList<String> getupdatedList(Patient_DA testp, String date, int did) {
        ArrayList<String> list;
        list = testp.getupdatedPrescription(date, did);
        return list;
    }

    public void deleteChannel(int pId, Patient_DA testp, String date) {

        testp.deleteChannel(pId, date);

    }

    public ArrayList<String> getAllData(Patient_DA testp, int pId) {
        ArrayList<String> list;
        list = testp.getAllData(pId);
        return list;
    }

    public boolean checkChannel(Patient_DA testp, int pId, String Sdate) {
        boolean status = true;
        status = testp.checktheChannel(pId, Sdate);
        return status;
    }

    public int getselecteddoctorid(String first, String last) {
        int id = -2;
        id = testd.getDoctorId(first, last);
        return id;
    }

    public void savedata() {
        testd.saveData(first, last, getHospital(), getQualification(),spec);

    }
    
    public ArrayList<String> getTodayDoctorList(String day) {
        ArrayList<String> list;
        list = testd.getTodaylist(day);
        return list;
    }
    
    public ArrayList<String> fillPatientTable(int did, String date) {
        ArrayList<String> list;
        list = testd.getTodaypatientlist(did,date);
        return list;
    }

}
