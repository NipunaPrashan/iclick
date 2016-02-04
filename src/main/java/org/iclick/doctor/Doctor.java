package org.iclick.doctor;

import org.iclick.doctor.beans.User;
import org.iclick.doctor.dbaccess.DbConnectionManager;
import org.iclick.doctor.dbaccess.DoctorDataAccessManager;
import org.iclick.doctor.dbaccess.PatientDataAccessManager;

import java.util.ArrayList;

/**
 *
 * @author Prashan
 */
public class Doctor extends User {

    private DoctorDataAccessManager testd = DoctorDataAccessManager.getInstance(DbConnectionManager.getInstance());
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

    public Doctor(String name, int id, String paswrd, DoctorDataAccessManager testp) {
        super(name, id, paswrd);
        this.testd = testp;

    }

    public Doctor(String fname, String lname) {
        super(fname, lname, "");

    }

    public Doctor(int id, DoctorDataAccessManager testp, String date) {
        super(id);
        this.testd = testp;
        this.date = date;
    }

    public Doctor(int id) {
        super(id);
    }

    public Doctor() {
    }

    public Doctor(DoctorDataAccessManager testp, String date) {
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

    public Doctor(int pId, DoctorDataAccessManager testp) {
        super(pId);
        this.testd = testp;
    }

    public Doctor(String name, String paswrd, DoctorDataAccessManager testp) {
        super(name, paswrd);
        this.testd = testp;
    }

    public Doctor(String name, DoctorDataAccessManager testp) {
        super(name);
        this.testd = testp;
    }

    public void savedata(String first, String last, String hospital, String Qualification, String mobile1, String mobile2, int id, ArrayList<Integer> MID) {
        testd.UpdateDoctorInfo(first, last, hospital, Qualification, mobile1, mobile2, id, MID);
    }

    public ArrayList<String> getList(DoctorDataAccessManager testp, int id) {
        ArrayList<String> list;
        list = testp.availabity(id);
        return list;
    }

    public String getpassword(int id) {
        String pass;
        pass = testd.getDoctorPassword(id);
        return pass;
    }

    public ArrayList<String> getpatientlastappoinmentdetails(DoctorDataAccessManager testp, int pid, int did) {
        ArrayList<String> details;
        details = testp.lastappointment(pid, did);
        return details;
    }

    public int getavailablecount(DoctorDataAccessManager testp, int id) {
        int count = -1;
        count = testp.availabitycount(id);
        return count;
    }

    public ArrayList<String> getNaturalJoinList(PatientDataAccessManager testp, String date) {
        ArrayList<String> list;
        list = testp.getNaturalJoinList(date);
        return list;
    }

    public ArrayList<String> getupdatedList(PatientDataAccessManager testp, String date, int did) {
        ArrayList<String> list;
        list = testp.getupdatedPrescription(date, did);
        return list;
    }

    public void deleteChannel(int pId, PatientDataAccessManager testp, String date) {

        testp.deleteChannel(pId, date);

    }

    public ArrayList<String> getAllData(PatientDataAccessManager testp, int pId) {
        ArrayList<String> list;
        list = testp.getAllData(pId);
        return list;
    }

    public boolean checkChannel(PatientDataAccessManager testp, int pId, String Sdate) {
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
        testd.registerDoctorInfo(first, last, getHospital(), getQualification(),spec);

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
