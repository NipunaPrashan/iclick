/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iclick.doctor;

/**
 *
 * @author Suchira
 */
public class Attendent extends User{

    public Attendent(String name, int id, String paswrd) {
        super(name, id, paswrd);
    }
    
    public void getPrescription(int patientId){
        //check last prescribed medicine
    }
    
    public void login(){
        // call login validation from another class
        // pass the attendent's password as a parameter
    }
}
