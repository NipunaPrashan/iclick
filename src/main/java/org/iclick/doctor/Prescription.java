/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iclick.doctor;

import java.util.ArrayList;

/**
 *
 * @author Pravinda Perera
 */
public class Prescription extends Card {

    private Prescription_DA presDA;
    private int pId;
    private String date;
    private String[] medicine;
    private String[] history;
    private String time;
    private int doc_Id;

    public Prescription(int crd_Id, String details, String date, String remarks, Prescription_DA presDA) {
        super(crd_Id, details, remarks);
        this.presDA = presDA;
        this.date = date;
        sendPrescribData(crd_Id, details, remarks, date);

    }

    public Prescription(int crd_Id, int doc_id, String details, String date, String remarks, Prescription_DA presDA, String time) {
        super(crd_Id, details, remarks);
        this.presDA = presDA;
        this.date = date;
        this.time = time;
        sendPrescribData(crd_Id,doc_id, details, remarks, date, time);

    }

    public Prescription(int patientId, Prescription_DA presDA) {

        this.pId = patientId;
        this.presDA = presDA;

    }

    public Prescription(Prescription_DA pressDA) {
        this.presDA = pressDA;

    }

    public Prescription(int id) {
        setCrd_Id(id);
    }

    public ArrayList<String> getDetails(int Patient_id, String date) {
        ArrayList<String> patientHealth;
        patientHealth = presDA.getPrescribeMedicine(Patient_id, date);

        return patientHealth;
    }

    /* public Prescription(Prescription_DA presDA){
     this.presDA = presDA;
     } */
    /* public Prescription(Prescription_DA pressDA){
     this.presDA = pressDA;
     }*/
    /* public void getAccess(Prescription_DA pressDA){
     this.presDA = pressDA;
     }*/
    public void sendPrescribData(int crd_Id, String details, String remarks, String date) {

        medicine = details.split("\\r?\\n");
        history = remarks.split("\\r?\\n");

        presDA.savePrescrib(crd_Id, medicine, history, date);

    }

    public void sendPrescribData(int crd_Id,int d_id, String details, String remarks, String date, String time) {

        medicine = details.split("\\r?\\n");
        history = remarks.split("\\r?\\n");

        presDA.savePrescrib(crd_Id,d_id, medicine, history, date, time);

    }

    public ArrayList<String> getMedicine(int patient_Id,int doctorid) {
        ArrayList<String> currentPDetails;
        currentPDetails = presDA.getTodayMedi(patient_Id,doctorid);
        return currentPDetails;
    }

    public Prescription_DA getPresDA() {
        return presDA;
    }

    public ArrayList<String> getDates(int id, int did) {
        ArrayList<String> chnlDates;
        chnlDates = presDA.getPrescribeDates(id,did);
        return chnlDates;
    }

}
