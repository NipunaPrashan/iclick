package org.iclick.doctor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Pravinda Perera
 */
public class Patient extends User {

    private Prescription pres;
    private Patient_DA testp = Patient_DA.getConnection(DbConnecter.getConnection());
    private String date;
    private int assingeddoctor;

    public Patient(String name, int id, String paswrd, Patient_DA testp) {
        super(name, id, paswrd);
        this.testp = testp;

    }

    public Patient(int id, Patient_DA testp, String date) {
        super(id);
        this.testp = testp;
        this.date = date;
    }
    
    public Patient(int id, Patient_DA testp, String date, int did) {
        super(id);
        this.testp = testp;
        this.date = date;
        this.assingeddoctor = did;
    }

    public Patient(int id) {
        super(id);
    }

    public Patient(Patient_DA testp, String date) {
        this.testp = testp;
        this.date = date;
        //  getList(testp,date);
    }

    public Patient(int pId, Patient_DA testp) {
        super(pId);
        this.testp = testp;
    }

    public Patient(String name, String paswrd, Patient_DA testp) {
        super(name, paswrd);
        this.testp = testp;
    }

    public Patient(String name, Patient_DA testp) {
        super(name);
        this.testp = testp;
    }

    public Patient(String first, String last, String gender, String mobile,String BDate, String address, String user, String pass, Patient_DA testp) {
        super(first, last, gender, mobile, BDate, address, user, pass);
        this.testp = testp;
    }
    
    public Patient(String first, String last, String gender, String mobile,String BDate, String address, String user, String pass, String efirst, String elast, String emobile, Patient_DA testp) {
        super(first, last, gender, mobile, BDate, address, user, pass, efirst, elast, emobile);
        this.testp = testp;
    }
    
    public Patient(String first, String last, String gender, String mobile,String BDate, String address, String efirst, String elast, String emobile, Patient_DA testp) {
        super(first, last, gender, mobile, BDate, address,efirst, elast, emobile);
        this.testp = testp;
    }
    
    /* public Patient(Prescription pres){
     this.pres = pres;
     sendData();
     }*/

    public void sendData() {
        testp.saveData(this.getFirst(), this.getLast(), this.getGender(), this.getMobile(), this.getBDate(), this.getAddress(), this.getUser(), this.getPass(), this.getEfirst(),this.getElast(), this.getEmobile());
    }

    public void updatecurrentData(int PID) {
        testp.updateDataa(PID, this.getFirst(), this.getLast(), this.getGender(), this.getMobile(), this.getBDate(), this.getAddress());
    }

    public String getPatientName(int pId) {
        String patientName;
        patientName = testp.getPatient_UserName(pId);
        return patientName;
    }

    public String getPatientRealName(int pId) {
        String patientRealName;
        patientRealName = testp.getPatient_RealName(pId);
        return patientRealName;
    }

    public String getPatientAddress(int pId) {
        return testp.getPatient_Address(pId);
    }

    public int getPatientMobileNo(int pId) {
        return testp.getPatientMobile(pId);
    }

    public String getPatientGender(int pId) {
        String patientName;
        patientName = testp.getPatientGender(pId);
        return patientName;
    }

//    public void updateData() {
//        testp.getData(this);
//    }

    public void setPres(Prescription pres) {
        this.pres = pres;
    }

    public ArrayList<String> getIDList(Patient_DA testp, String date, int did, String time) {
        ArrayList<String> list;
        list = testp.getChannelIDList(date,did,time);
        return list;
    }
    
    public ArrayList<String> getNaturalJoinList(Patient_DA testp, String date) {
        ArrayList<String> list;
        list = testp.getNaturalJoinList(date);
        return list;
    }

    public ArrayList<String> getupdatedList(Patient_DA testp, String date, int did) {
        ArrayList<String> list;
        list = testp.getupdatedPrescription(date,did);
        return list;
    }

    public int channel(int pId, Patient_DA testp, String date, int did, String time) {

        return testp.channelDoc(pId, date, time, did);
    }
    
    public void cancellastchannel(int pId, Patient_DA testp, int did) {

        testp.cancellastappointment(pId,did);
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
    
    public String getDOB(int pId) {
        String DOB=null;
        DOB = testp.getDOB(pId);
        return DOB;
    }

    public ArrayList<String> searchbyname(String name) {
        ArrayList<String> list;
        list = testp.getAllData(name);
        
        return list;
    }

}
